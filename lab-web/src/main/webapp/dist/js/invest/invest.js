
var vueObj = new Vue({
    el: '#app',
    data: {
        dataTable : null,
        sumCost : '',
        sumIncome : ''
    },
    //created: created,
    mounted: function(){
        this.initRecordTable();
    },
    methods: {
        initRecordTable: function(){
            var self = this;
            this.dataTable = $('#dataTable').DataTable({
                //"lengthChange": true,//是否允许用户改变表格每页显示的记录数
                // "scrollX": "100%",//表格的宽度
                // "scrollY": "200px",//表格的高度
                "ajax": {
                    "url": basePath+"/invest/list",
                    "dataSrc": "investDTOs"
                },
                "columns": [
                    { "data": "projectName" },
                    { "data": "projectType" },
                    { "data": "mainChannel" },
                    { "data": "beginDate" },
                    { "data": "endDate" },
                    { "data": "cost" },
                    { "data": "income" }
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
                        pageTotalCost +' 总共('+ totalCost +')'
                    );

                    $(api.column(6).footer() ).html(
                        pageTotalIncome +' 总共('+ totalIncome +')'
                    );
                },
                "language": {
                    "search": "查找",
                    "infoEmpty": "没有数据",
                    "zeroRecords": "没有查找到满足条件的数据",
                    "info": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "lengthMenu": "每页显示 _MENU_ 条记录",
                    "infoFiltered": "(从 _MAX_ 条数据中检索)",
                    "paginate": {
                        "first": "首页",
                        "previous": "前一页",
                        "next": "后一页",
                        "last": "尾页"
                    }
                },
            });
        }
    }
});
