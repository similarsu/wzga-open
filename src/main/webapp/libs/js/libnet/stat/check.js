/*var testData={"rows":[
    {"deptName":"鹿城区分局","pTotal":2,"pm":1,"pc":1,"cTotal":3,"cm":2,"cc":1,"five":1,"four":2,"three":1,"two":1,"one":1},
    {"deptName":"瓯海区分局","pTotal":4,"pm":1,"pc":3,"cTotal":2,"cm":1,"cc":1,"five":1,"four":1,"three":0,"two":1,"one":1},
    {"deptName":"乐清市公安局","pTotal":2,"pm":1,"pc":1,"cTotal":2,"cm":1,"cc":1,"five":2,"four":1,"three":0,"two":1,"one":1},
    {"deptName":"瑞安市公安局","pTotal":2,"pm":1,"pc":1,"cTotal":3,"cm":2,"cc":1,"five":1,"four":1,"three":1,"two":1,"one":1},
    {"deptName":"龙湾区分局","pTotal":6,"pm":5,"pc":1,"cTotal":1,"cm":1,"cc":1,"five":1,"four":0,"three":0,"two":1,"one":1},
    {"deptName":"永嘉县公安局","pTotal":1,"pm":2,"pc":1,"cTotal":4,"cm":3,"cc":1,"five":3,"four":1,"three":1,"two":1,"one":1},
    {"deptName":"平阳县公安局","pTotal":2,"pm":1,"pc":1,"cTotal":1,"cm":1,"cc":1,"five":1,"four":1,"three":1,"two":1,"one":1},
    {"deptName":"平阳县公安局","pTotal":2,"pm":1,"pc":1,"cTotal":2,"cm":1,"cc":1,"five":1,"four":1,"three":1,"two":1,"one":1},
    {"deptName":"开发区分局","pTotal":2,"pm":1,"pc":1,"cTotal":2,"cm":1,"cc":1,"five":1,"four":1,"three":0,"two":1,"one":1},
    {"deptName":"文成县公安局","pTotal":2,"pm":1,"pc":1,"cTotal":2,"cm":1,"cc":1,"five":1,"four":1,"three":1,"two":1,"one":1},
    {"deptName":"泰顺县公安局","pTotal":2,"pm":1,"pc":1,"cTotal":2,"cm":1,"cc":1,"five":1,"four":1,"three":1,"two":0,"one":1},
    {"deptName":"洞头县公安局","pTotal":2,"pm":1,"pc":1,"cTotal":2,"cm":1,"cc":1,"five":1,"four":1,"three":1,"two":1,"one":1},
    {"deptName":"温州市公安局","pTotal":2,"pm":1,"pc":1,"cTotal":2,"cm":1,"cc":1,"five":1,"four":1,"three":0,"two":1,"one":1},
    {"deptName":"全市","pTotal":21,"pm":18,"pc":16,"cTotal":26,"cm":18,"cc":13,"five":16,"four":15,"three":10,"two":10,"one":13}
    ]};*/
//数据表格
var g;

var terminalCols_p =[
    {display: '合计', width: 70 , name:'totalPeCnt'},
    {display: '移动', width: 70 , name:'moPeCnt'},
    {display: '电脑', width: 70 , name:'pcPeCnt'}
];

var terminalCols_c =[
    {display: '合计', width: 70 , name:'totalCnt'},
    {display: '移动', width: 70 , name:'moCnt'},
    {display: '电脑', width: 70 , name:'pcCnt'}
];

var starCols =[
    {display: '五星', width: 70 , name:'five'},
    {display: '四星', width: 70 , name:'four'},
    {display: '三星', width: 70 , name:'three'},
    {display: '二星', width: 70 , name:'two'},
    {display: '一星', width: 70 , name:'one'}
];

var thirdCols =[
    {display: '单位', width: 322 ,name:'deptName'},
	{display: '核查人数', width: 210 , columns: terminalCols_p},
	{display: '核查条数', width: 210 , columns: terminalCols_c},
	{display: '核查星级条数', width: 350, columns: starCols}
];
var secondCols =[
	{ display: '各地人员核查情况统计', width: 1100 , columns: thirdCols}
];

function openHtml() {
	var deptCode = $("#deptCode").val();
	if (deptCode == "") {
		deptCode = "-1";
	}
	var diag = new top.Dialog();
	diag.Title = "单位选择";
	diag.URL = ctx+"/ajax/dept/" + deptCode + "/init";
	diag.Width = 450;
	diag.Height = 180;
	diag.OKEvent = function() {
		var deptName = $("#deptCode option:selected",
				diag.innerFrame.contentWindow.document).text();
		var deptCode = $("#deptCode option:selected",
				diag.innerFrame.contentWindow.document).val();
		if (deptCode != -1) {
			$("#deptName").val(deptName);
			$("#deptCode").val(deptCode);
		}
		diag.close();
	};
	diag.show();
}