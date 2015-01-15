function renderDOM() {
    jQuery.renderPageComponent([
        "#queryBox",
        "#queryTable",
        "#queryTable tr td input",
        "#queryTable tr td select",
        "#queryBtn"
    ]);

    renderList("listTable");
}

function avgData(data, tolPropName, numPropName, targetPropName) {
    if(!data) {
        return [];
    }

    for (var i in data) {
        var ele = data[i];
        ele[targetPropName] = ele[tolPropName] / ele[numPropName];
    }

    return data;
}

function renderList(tableID) {
    /*var data = [
        {"title": "xx派出所", "loginNum": 12, "tolLoginNum": 20},
        {"title": "xx派出所", "loginNum": 10, "tolLoginNum": 20},
        {"title": "xx派出所", "loginNum": 3, "tolLoginNum": 21}
    ];*/

    $.quiDefaults.Grid.formatters['avg'] = function (value, column) {
        return value.toFixed(2);
    }


    $("#" + tableID).quiGrid({
        columns: [
            { display: '单位', name: 'deptName', align: 'center'},
            { display: '登录人数', name: 'peCnt', align: 'center'},
            { display: '总登录次数', name: 'totalCnt', align: 'center'},
            { display: '平均登录次数', name: 'avgCnt',  align: 'center', type: "avg"}
        ],

        data: {
            //"form.paginate.pageNo": 1,
            //"form.paginate.totalRows":13,
            "rows": avgData(data, "totalCnt", "peCnt", "avgCnt")
        },
        usePager:false,
        //pageSize: 10,
        rownumbers: true,
        height: '100%', width:"100%"
    });
}

function pageReady() {
    renderDOM();
}

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
