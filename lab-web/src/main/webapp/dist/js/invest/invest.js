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

function showOperModal(flag){
    vueObj.operType = flag;
    if(flag != 'remove') {
        $("#formModal").modal("show");
    }else{
        vueObj.submitConfirm();
    }
}

var vueObj = new Vue({
    el: '#app',
    components: {
        vSelect: VueStrap.select,
        vDatepicker : VueStrap.datepicker,
        vModal : VueStrap.modal,
    },
    data: {
        gridColumns : [
            { "data": "id" },
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
        rowSel : {},
        appForm : {
            id : '',
            projectType : '',
            projectName : '',
            mainChannel : '',
            subChannel : '',
            beginDate : '',
            endDate : '',
            cost : 0,
            income : 0
        },
        sumCost : '',
        sumIncome : '',
        periods : ["1","2"],
        confirmMsg : '',
        operType : '',
        projectTypeDict : [],
        mainChannelTypeDict : [],
        subChannelTypeDict : [],
        retMsg : '',
    },
    created: function(){
        this.initData();
    },
    mounted: function(){
        this.initRecordTable();
    },
    methods: {
        initData: function(){
            var dictUrl = basePath+"/dict";
            var self = this;

            $.getJSON( dictUrl , { "groupName" : 'PROJECT_TYPE' }, function (json) {
                self.projectTypeDict = json;
            });
            $.getJSON( dictUrl , { "groupName" : 'MAIN_CHANNEL' }, function (json) {
                self.mainChannelTypeDict = json;
            });
            $.getJSON( dictUrl , { "groupName" : 'SUB_CHANNEL' }, function (json) {
                self.subChannelTypeDict = json;
            });
        },
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
                "columns": self.gridColumns,
                "columnDefs": [
                    {
                        "render": function ( data, type, row ) {
                            return data +' ('+ row['subChannelDe']+')';
                        },
                        "targets": [1]
                    },
                    { "visible": false,  "targets": [0,2,4,5,6,7] }
                ],
                "footerCallback": function ( row, data, start, end, display ) {
                    var api = this.api();
                    // Total over all pages
                    var totalCost = api.column(10).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var pageTotalCost = api.column(10, { page: 'current'} ).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var totalIncome = api.column(11).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );
                    var pageTotalIncome = api.column(11, { page: 'current'} ).data().reduce( function (a, b) {
                        return toNumber(a) + toNumber(b);
                    }, 0 );

                    // Update footer
                    $(api.column(10).footer() ).html(
                        $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display(pageTotalCost) +
                            '</br>总共('+ $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display( totalCost) +')'
                    );

                    $(api.column(11).footer() ).html(
                        $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display(pageTotalIncome) +
                        '</br>总共('+ $.fn.dataTable.render.number( ',', '.', 2, '￥' ).display( totalIncome) +')'
                    );

                },
            });

            $("#button-area").html('<p><button id="btnAdd" type="button" class="btn btn-primary "'+
                                        'onclick="showOperModal(\'add\')">新增</button>'+
                                        '<button id="btnEdit" type="button" class="btn btn-success" '+
                                        'disabled="disabled" onclick="showOperModal(\'edit\')">修改</button>'+
                                        '<button id="btnRemove" type="button" class="btn btn-danger" '+
                                        'disabled="disabled" onclick="showOperModal(\'remove\')">删除</button></p>');

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
                self.refreshModalData();
                $("#btnEdit").removeAttr("disabled");
                $("#btnRemove").removeAttr("disabled");
            }).on('deselect', function () {
                //self.rowSel = null;
                //self.refreshModalData();
                $("#btnEdit").attr("disabled","disabled");
                $("#btnRemove").attr("disabled","disabled");
            });

        },
        getUrlParam : function () {
            var paramArr = this.periods.reduce(function (a, b) {
                return (a === '') ? b : a + ',' + b;
            }, '' );

            return basePath+"/invest/list?periods="+paramArr;
        },
        getRowSelObject : function(rowData) {
            var columnName;
            for(col in this.gridColumns){
                columnName = this.gridColumns[col].data;
                this.rowSel = this.rowSel || {};
                this.rowSel[columnName] = rowData.pluck(columnName)[0] || '';
            }
        },
        refreshModalData : function(){

            this.appForm.id = this.rowSel.id || '';
            this.appForm.projectType = this.rowSel.projectType || '';
            this.appForm.projectName = this.rowSel.projectName || '';
            this.appForm.mainChannel = this.rowSel.mainChannel || '';
            this.appForm.subChannel = this.rowSel.subChannel || '';
            this.appForm.beginDate = this.rowSel.beginDate || '';
            this.appForm.endDate = this.rowSel.endDate || '';
            this.appForm.cost =  this.rowSel.cost || '';
            this.appForm.income =  this.rowSel.income || '';
        },
        submitConfirm : function() {
            if (this.operType === 'add') {
                this.confirmMsg = '是否新增该条记录';
            } else if (this.operType === 'edit') {
                this.confirmMsg = '是否修改该条记录';
            } else if (this.operType === 'remove') {
                this.confirmMsg = '是否删除该条记录';
            } else {
                alert('operation not supported');
            }
            $("#confirmModal").modal('show');
        },
        submit : function(){
            var self = this;
            var id = this.appForm.id;

            if(this.operType === 'add'){
                var paramStr = JSON.stringify(formatValues(this.appForm)); //透传
                this.$http.post(basePath+'/invest/',  paramStr).then(
                    function (response) {
                        if(response &&  response.body && response.body.respCode){
                            self.showRetModal(response.body.respCode, response.body.respMsg);
                        }else{
                            self.showRetModal('-1','返回异常，response:'+ JSON.parse(response));
                        }
                    },
                    function (response) {
                        self.showRetModal('-1','请求失败:'+response.statusText);
                        console.info(response.body);
                    });
            }else if(this.operType === 'edit'){
                var paramStr = JSON.stringify(formatValues(this.appForm)); //透传
                this.$http.put(basePath+'/invest/'+ id,  paramStr).then(
                    function (response) {
                        if(response &&  response.body && response.body.respCode){
                            self.showRetModal(response.body.respCode, response.body.respMsg);
                        }else{
                            self.showRetModal('-1','返回异常，response:'+ JSON.parse(response));
                        }
                    },
                    function (response) {
                        self.showRetModal('-1','请求失败:'+response.statusText);
                        console.info(response.body);
                    });
            }else if(this.operType === 'remove'){
                this.$http.delete(basePath+'/invest/'+ id).then(
                    function (response) {
                        if(response &&  response.body && response.body.respCode){
                            self.showRetModal(response.body.respCode, response.body.respMsg);
                        }else{
                            self.showRetModal('-1','返回异常，response:'+ JSON.parse(response));
                        }
                    },
                    function (response) {
                        self.showRetModal('-1','请求失败:'+response.statusText);
                        console.info(response.body);
                    });
            }else{
                alert('operation not supported');
            }

        },
        showRetModal : function(retcode, msg){
            this.retMsg = msg;
            $("#confirmModal").modal('hide');
            $("#resultModal").modal('show');
            if(retcode == '0'){
                $("#formModal").modal('hide');
                this.rowSel = {};  //操作成功后清除选中项
            }
        },
        hideRetModal : function () {
            $("#resultModal").modal('hide');
            this.refreshModalData();
            this.dataTable.ajax.url(this.getUrlParam()).load();
        }
    }
});
