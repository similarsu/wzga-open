
function openHtml() {
	var deptCode = $("#deptCode").val();
	if (deptCode == "") {
		deptCode = "-1";
	}
	var diag = new top.Dialog();
	diag.Title = "单位选择";
	diag.URL = deptURL.replace("#p#", deptCode);
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

