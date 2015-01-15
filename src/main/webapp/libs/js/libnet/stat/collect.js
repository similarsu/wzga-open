//数据表格
var g;
var collectTypeCols =[
    {display: '人像', width: 70 , name:'photo'},
    {display: '指纹', width: 70 , name:'finger'},
    {display: 'DNA', width: 70 , name:'dna'},
    {display: '虚拟身份', width: 70 , name:'virtual'},
    {display: '体貌特征', width: 70 , name:'trait'},
    {display: '随身物品', width: 70 , name:'belonging'}
];

var secondCols =[
    {display: '单位', width: 340,name:'deptName'},
    {display: '人数', width: 60 ,name:'person'},
	{display: '采录类别（人数）', width: 480 , columns: collectTypeCols}
];

function openHtml() {
	var deptCode = $("#deptCode").val();
	if (!deptCode) {
		deptCode = "-1";
	}
	var diag = new top.Dialog();
	diag.Title = "单位选择";
	diag.URL = prefix + "/ajax/dept/" + deptCode + "/init";
	diag.Width = 450;
	diag.OKEvent = function() {
        diag.innerFrame.contentWindow.sureHandler(undefined, undefined, undefined, 1);
	};
	diag.show();
}

function initComplete(){
	 g = $("#personCollectGrid").quiGrid({
        columns: [
            { display: '各地信息入所、路面采集情况统计', width: 880 , columns: secondCols}
        ],
        data: listData,
        height: '100%',
        width:'100%',
        usePager:false,
        isScroll:true,
        multihead:true
    });

    //渲染页面控件
    jQuery.renderPageComponent([
        "#queryTable input",
        "#mode",
        "#btn-1"
    ]);

    $("#queryForm").submit(function() {
        // set collect source
        var ele = $("#queryTable input[type='checkbox']:checked");
        if (ele && 1 < ele.length) {
            return true;
        }

        $("#colType").val(ele.val());
        return true;
    });
}


