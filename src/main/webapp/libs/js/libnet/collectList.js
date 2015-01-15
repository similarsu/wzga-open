// customize height
function customHeightSet(contentHeight){
    $("#scrollContent").height(contentHeight-107);
}

function renderDom() {
    jQuery.renderPageComponent([
        "#queryBox",
        "#formTable",
        "#formTable tr td input[type='text']",
        "#btn-1",
        "#tableList",
        "#tableList tr[class*='textSliceRow'] td[class='textSliceTD'] span[class='textSlice']",
        "#pageContent"
    ]);
}

function renderDept() {
    /*
        load from ajax
        render page
    */
    $("#deptName").click(function() {
        var deptID = $("#deptCode").val();
        deptID = !deptID ? -1 : deptID;
		var diag = new top.Dialog();
		diag.Title = "单位选择";
		diag.URL = prefix + "/ajax/dept/" + deptID + "/init";
		diag.Width = 450;
		diag.Height = 300;
		diag.OKEvent = function() {
            diag.innerFrame.contentWindow.sureHandler(undefined, undefined, undefined, 1);
		};
		diag.show();
    });
}

// qui entry
function initComplete() {
    renderDom();
    renderDept();
}
