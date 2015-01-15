function initComplete(){
    // 渲染和绑定素搜索
    jQuery.renderPageComponent([
        "#searchInput"
    ]);

    var p = $("#provice"),
        city = $("#city"),
        country = $("#country"),
        dept = $("#deptCode");

    $("#searchInput").bind("listSelect",function(e,obj){
        var selectedVal = obj.relValue;
        var len = selectedVal.length;
        if (1 < len) {
            var proviceCode = selectedVal.substring(0, 2)
            if (proviceCode != p.val()) {
                p.attr("selectedValue", proviceCode);
                loadProvince();

            }

        }

        if (3 < len) {
            var cityCode = selectedVal.substring(0, 4);
            if (cityCode != city.val()) {
                city.attr("selectedValue", cityCode);
                loadCity(p.val());
            }
        }

        if (5 < len) {
            var countryCode = selectedVal.substring(0, 6);
            if (countryCode != country.val()) {
                country.attr("selectedValue", countryCode);
                loadCounty(city.val());
            }
        }

        if (11 < len) {
            var deptCode = selectedVal;
            if (deptCode != dept.val()) {
                dept.attr("selectedValue", deptCode);
                loadDept(country.val());
            }
        }

    });

    // 省市初始化
    p.data("data", provinceData).render();
    city.data("data", cityData).render();
    country.data("data", countryData).render();
    dept.data("data", deptData).render();


    p.bind("change", function() {
        if (-1 != $(this).val()) {
            loadCity($(this).val());
        }
    });

    city.bind("change", function() {
        if (-1 != $(this).val()) {
            loadCounty($(this).val());
        }
    });


    country.bind("change", function() {
        if (-1 != $(this).val()) {
            loadDept($(this).val());
        }
    });

    // 初始化
    if (-1 == city.val()) {
        p.change();
    }

    if (-1 == country.val()) {
        city.change();
    }

    if (-1 == dept.val()) {
        country.change();
    }

}

function loadCity(proviceCode){
    if(!proviceCode) {
        return;
    }

    reqData("/ajax/dept/"+proviceCode+"/city", function(res){
        var data = assembleData(res);
        if (data) {
            $("#city").data("data", data).render();
        }
    });

}

function loadCounty(cityCode){
    if(!cityCode) {
        return;
    }

    reqData("/ajax/dept/"+cityCode+"/county", function(res){
        var data = assembleData(res);
        if (data) {
            $("#country").data("data", data).render();
        }
    });
}

function loadDept(countyCode){
    if(!countyCode) {
        return;
    }

    reqData("/ajax/dept/"+countyCode+"/dept", function(res){
        var data = assembleData(res);
        if (data) {
            $("#deptCode").data("data", data).render();
        }
    });
}

/**
* 其他页获取单位代码方法，调用方式
* if (!diag.innerFrame.contentWindow.sureHandler('', '', '')) {
*	// error
* }
* @param nameEleID 单位名称元素
* @param codeEleID 单位代码元素
* @param idEleID 单位ID元素
* @param level 必选层级:0可为空, 1区， 2单位
*/
function sureHandler(nameEleID, codeEleID, idEleID, level){
    var target = $(1 == level ? "#country" : "#deptCode");

    if(-1 == target.val()){
        top.Dialog.alert((1 == level ? "区" : "单位") + "代码不能为空!");
        return false;
    }

    var nameID = !nameEleID ? "#deptName" : nameEleID,
        codeID = !codeEleID ? "#deptCode" : codeEleID,
        idID = !idEleID ? "#deptID" : deptID;

    // 部门若不为空则为部门
    var dept = $("#deptCode");
    if (1 == level && -1 != dept.val()) {
        target = dept;
    }

    $(nameID, parent.frmright.document).val(target.attr("relText"));
    $(codeID, parent.frmright.document).val(target.val());
    $(idID, parent.frmright.document).val(target.data("selectedNode").id);

    top.Dialog.close();
    return true;
}

function reqData(url, successFn) {
    $.ajax({
        url: prefix + url,
        type:"GET",
        dataType:"json",
        contentType:"application/json;charset=UTF-8",
        error:function(){
            alert("加载数据出错!");
        },
        success: successFn
    });
}

function assembleData(list) {
    if (!list || 1 > list.length) {
        return;
    }

    var res = [{"value":"-1", "key": "请选择"}];
    for (var i=0; i<list.length; i++) {
        res[res.length] = {
            "value": list[i].deptCode,
            "id": list[i].id,
            "key": list[i].deptName
            };
    }

    return {"list": res};
}

function loadProvince(){
    reqData("/ajax/dept/province", function(res){
        var data = assembleData(res);
        if (data) {
            $("#provice").data("data", data).render();
        }
    });
}
