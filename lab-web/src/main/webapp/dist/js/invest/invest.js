$.extend( true, $.fn.dataTable.defaults, {
    //"searching": false,
    //"ordering": false,
    //"paging": false,
    dom:  "<'row'<'#button-area.col-sm-5'><'#expire-tag.col-sm-3'><'col-sm-4'f>>" +
          "<'row'<'col-sm-12'tr>>" +
          "<'row'<'col-sm-4'l><'col-sm-4'i><'col-sm-4'p>>",
    "renderer": 'bootstrap',
    "language": {
        //"lengthChange": true,//是否允许用户改变表格每页显示的记录数
        // "scrollX": "100%",//表格的宽度
        // "scrollY": "200px",//表格的高度
        "search": "查找",
        "infoEmpty": "没有数据",
        "emptyTable": "表格为空",
        "zeroRecords": "没有查找到满足条件的数据",
        "info": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
        "lengthMenu": "每页显示 _MENU_ 条记录",
        "infoFiltered": "(从 _MAX_ 条数据中检索)",
        "paginate": {
            "first": "首页",
            "previous": "前一页",
            "next": "后一页",
            "last": "尾页"
        },
        "decimal": ".",
        "thousands": ","
    },
} );


var vueObj = new Vue({
    el: '#app',
    data: {
        COLUMN_META : [
            { "data": "projectName" },
            { "data": "projectType" },
            { "data": "projectTypeDe" },
            { "data": "mainChannel" },
            { "data": "mainChannelDe" },
            { "data": "subChannel" },
            { "data": "subChannelDe" },
            { "data": "beginDate" },
            { "data": "endDate" },
            { "data": "cost" ,
                "className" : "text-right",
                "render" : $.fn.dataTable.render.number( ',', '.', 2, '￥' )
            },
            { "data": "income",
                "className" : "text-right",
                "render" : $.fn.dataTable.render.number( ',', '.', 2, '￥' )
            },
            { "data": "annualYield" ,
                "className" : "text-right",
                "render" : $.fn.dataTable.render.number( ',', '.', 2, '', '%')
            }
        ],
        dataTable : null,
        rowSel : null,
        appForm : {
            projectType : '',
            projectName : ''
        },
        sumCost : '',
        sumIncome : '',
        periods : ["1","2"],
        confirmMsg : '',
        operType : ''
    },
    //created: created,
    mounted: function(){
        this.initRecordTable();
    },
    methods: {
        initRecordTable: function(){
            var self = this;

            this.dataTable = $('#dataTable').DataTable({
                //"dom" :  '<"top"f><rt><"bottom"ip>',
                "select" : {
                    "items" : "row",
                    "style" : "single",
                    "blurable" : true,
                    "info" : false
                },
                "ajax": {
                    "url": self.getUrlParam(),
                    "dataSrc": "investDTOs"
                },
                "columns": self.COLUMN_META,
                "columnDefs": [
                    {
                        "render": function ( data, type, row ) {
                            return data +' ('+ row['subChannelDe']+')';
                        },
                        "targets": 0
                    },
                    { "visible": false,  "targets": [1,3,4,5,6] }
                ],
                "footerCallback": function ( row, data, start, end, display ) {
                    var api = this.api();
                    // Total over all pages
                    var totalCost = api.column(9).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var pageTotalCost = api.column(9, { page: 'current'} ).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var totalIncome = api.column(10).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var pageTotalIncome = api.column(10, { page: 'current'} ).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );

                    // Update footer
                    $(api.column(9).footer() ).html(
                        $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display(pageTotalCost) +
                            '</br>总共('+ $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display( totalCost) +')'
                    );

                    $(api.column(10).footer() ).html(
                        $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display(pageTotalIncome) +
                        '</br>总共('+ $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display( totalIncome) +')'
                    );

                },
            });

            $("#button-area").html('<p><button id="btnAdd" type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal">新增</button>'+
            '<button id="btnEdit" type="button" class="btn btn-success" data-toggle="modal" disabled="disabled" data-target="#formModal">修改</button>'+
            '<button id="btnRemove" type="button" class="btn btn-danger" data-toggle="modal" disabled="disabled" data-target="#confirmModal">删除</button></p>');

            $("#expire-tag").html('<label class="checkbox-inline">' +
                '<input name="periods" type="checkbox" value="1" checked>往期</label>' +
                '<label class="checkbox-inline">' +
                '<input name="periods" type="checkbox" value="2" checked>当期</label>');

            $("input[name='periods']").change(function () {
                    self.periods = [];
                    $("input[name='periods']:checked").each(function (i, n) {
                        self.periods.push(n.value);
                    });
                    self.dataTable.ajax.url(self.getUrlParam()).load();
                }
            );

            this.dataTable.on( 'select', function ( e, dt, type, indexes ) {
                var $rowObj = self.dataTable.rows( indexes ).data();
                self.getRowSelObject($rowObj);

                self.appForm.projectType = self.rowSel.projectType;
                self.appForm.projectName = self.rowSel.projectName;

                $("#btnEdit").removeAttr("disabled");
                $("#btnRemove").removeAttr("disabled");
            }).on('deselect', function () {
                self.rowSel = null;
                $("#btnEdit").attr("disabled","disabled");
                $("#btnRemove").attr("disabled","disabled");
            });

            this.bindBtnEvents();
        },
        getUrlParam : function () {
            var paramArr = this.periods.reduce(function (a, b) {
                return (a === '') ? b : a + ',' + b;
            }, '' );

            return basePath+"/invest/list?periods="+paramArr;
        },
        getRowSelObject : function(rowData) {
            var columnName;
            for(col in this.COLUMN_META){
                columnName = this.COLUMN_META[col].data;
                this.rowSel = this.rowSel || {};
                this.rowSel[columnName] = rowData.pluck(columnName)[0] || '';
            }
        },
        bindBtnEvents : function() {
            var self = this;
            $("#btnAdd").on('click',function(){
                self.operType = 'add';
                self.confirmMsg = '是否确认提交';
            });
            $("#btnEdit").on('click',function(){
                self.operType = 'edit';
                self.confirmMsg = '是否确认提交';
            });
            $("#btnRemove").on('click',function(){
                self.operType = 'remove';
                self.confirmMsg = '是否确认删除该条记录';
            });

        },
        submit : function(){
            alert(this.operType);
        }
    }
});
