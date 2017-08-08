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
        dataTable : null,
        sumCost : '',
        sumIncome : '',
        periods : ["1","2"]
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
                "ajax": {
                    "url": self.getUrlParam(),
                    "dataSrc": "investDTOs"
                },
                "columns": [
                    { "data": "projectName" },
                    { "data": "projectTypeDe" },
                    { "data": "mainChannel" },
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
                "columnDefs": [
                    {
                        "render": function ( data, type, row ) {
                            return data +' ('+ row['mainChannel']+')';
                        },
                        "targets": 0
                    },
                    { "visible": false,  "targets": [2] }
                ],
                "footerCallback": function ( row, data, start, end, display ) {
                    var api = this.api();
                    // Total over all pages
                    var totalCost = api.column(5).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var pageTotalCost = api.column(5, { page: 'current'} ).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var totalIncome = api.column(6).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var pageTotalIncome = api.column(6, { page: 'current'} ).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );

                    // Update footer
                    $(api.column(5).footer() ).html(
                        $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display(pageTotalCost) +
                            '</br>总共('+ $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display( totalCost) +')'
                    );

                    $(api.column(6).footer() ).html(
                        $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display(pageTotalIncome) +
                        '</br>总共('+ $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display( totalIncome) +')'
                    );

                },
            });

            $("#button-area").html('<p><button type="button" class="btn btn-primary">新增</button>'+
            '<button type="button" class="btn btn-success">修改</button>'+
            '<button type="button" class="btn btn-danger">删除</button></p>');

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
            )
        },
        getUrlParam : function () {
            var paramArr = this.periods.reduce(function (a, b) {
                return (a === '') ? b : a + ',' + b;
            }, '' );

            return basePath+"/invest/list?periods="+paramArr;
        }
    }
});
