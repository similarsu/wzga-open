/*
{key:"名字", value: "值|id", isR: true // 是否必采}
*/
var oldRequireSel = [];

// qui entry
function initComplete(){
    jQuery.renderPageComponent([
        "#body",
        "#addTable",
        "#btnTD input"
    ]);

    renderChooser();
    bindForm();
}

function renderChooser() {
    var data = {
        "toList": selectedNodes.length > 0 ? selectedNodes[0] : selectedNodes,
        "fromList":  initNodes.length > 0 ? initNodes[0] : initNodes
    };

    var getCheckedValList = function() {
        var list = $("#requiredTD > div > input");
        if (1 > list.length) {
            return;
        }

        var ret = [];
        for (var i=0; i<list.length; i++) {
            var tmp = $(list.get(i));
            if ("checked" == tmp.attr("checked")) {
                ret[ret.length] = tmp.val();
            }
        }

        return ret;
    }
    // 渲染必须选中的流程
    var renderSelectedVal = function(selectedList) {
        if (!selectedList || 1 > selectedList.length) {
            $("#requiredTD").html("");
            oldRequireSel = [];
            return;
        }

        var html = [];
        for(var i=0; i<selectedList.length; i++) {
            html[html.length] = "<div>";
            html[html.length] = "   <input type=\"checkbox\" name=\"flowList\" ";
            html[html.length] = " value=\"" + selectedList[i]["value"] + "\" ";
            if (selectedList[i]["isR"]) {
                html[html.length] = " checked ";
            }
            html[html.length] = " />";
            html[html.length] = selectedList[i]["key"];
            html[html.length] = "</div>";
        }
        $("#requiredTD").html(html.join(""));
        oldRequireSel = selectedList;
    };

    var mergeSelected = function(oldSelected, newSel) {
        if (!oldSelected || !newSel) {
            return !newSel ? undefined : newSel;
        }

        var ret = [];
        for (var i=0; i<newSel.length; i++) {
            var tmp = newSel[i];
            var isOldRequire = false;
            for (var j=0; j<oldSelected.length; j++) {
                if (oldSelected[j] == tmp["value"]) {
                    isOldRequire = true;
                    break;
                }
            }

            if (isOldRequire) {
                tmp["isR"] = true;
            } else {
                delete tmp["isR"];
            }
            ret[ret.length] = tmp;
        }

        return ret;
    };

    $("#chooser").data("data", data).render();
    $("#chooser").bind("itemClick", function(e){
            var selectedList = $("#chooser").data("selectedNodes");
            renderSelectedVal(mergeSelected(getCheckedValList(), selectedList));
    });
}

function bindForm() {
    $("#sysCollectTypeForm").submit(function() {
        if (!oldRequireSel || 1 > oldRequireSel.length) {
            top.Dialog.alert("请选择流程");
            return false;
        }
        var ret = [];

        for(var i=0; i<oldRequireSel.length; i++) {
            var obj = oldRequireSel[i];
            ret.push({"i":obj.value, "s":obj.isR ? false : true})
        }

        $("#nodes").val(JSON.stringify(ret));
        return true;
    });
}
