// main entry
function initComplete(){
	docInit();
	initPager();

	if (window.pageReady) {
		window.pageReady();
	}
}

function docInit(){
	var table = $("#scrollContent > table");

	// hover
    table.find('tr').hover(function(){
            $(this).addClass('highlight');
        }, function(){
            $(this).removeClass('highlight');
        });

    //点击着色
    table.click(function(evt){
            $(this).siblings().removeClass('selected');
            $(this).addClass('selected');
            // 选中
            var tagName = $(evt.target).get(0).tagName.toLowerCase();
            var trDom;
            if ("td" == tagName) {
            	trDom = $(evt.target).parent();

            } else if ("tr" == tagName) {
            	trDom = $(evt.target);

            } else {
            	trDom = $(evt.target).parent().parent();
            }

            var childs = trDom.children("td").get(1);
            if (!childs) {
            	return;
            }
            $(childs).children("input[type='radio']").attr("checked", "checked");


        });
}

var pageNo = 1;

var pageSize = 20;

var totalRows = 0;

function initPager() {
    //当提交表单刷新本页面时关闭弹窗
    //top.Dialog.close();
    var $pager = $("#pageContent");
    $pager.bind("pageChange",function(e,index){
        pageNo = index + 1;
        $("#pageNo").val(pageNo);
        $("#pageSize").val($pager.attr("pageSize"));
        postData();
    });


    $pager.bind("sizeChange",function(e,num){
        pageSize = num;
        $("#pageSize").val(pageSize);
        if (!window.postData) {
        	top.Dialog.alert("函数找不到");
        	return false;
        }

        window.postData();
    });
}

function operationItem(uri, opType){
	opType = opType==''?'操作':opType;
	var flag = false;
	var id = '';
	var breakFuc = false;
	$("[name='ids']").each(function(){
		if($(this).attr("checked")){
			if(!flag){
				flag = true;
				id = $(this).val();
			}
			else{
				top.Dialog.alert("最多只能选择一条记录"+opType+"！|提示信息",function(){}); 
				breakFuc = true;
				return false;
			}
		}  
	});
	if(breakFuc){
		return;
	}
	if(!flag){
		top.Dialog.alert("请选择一个记录"+opType+"！|提示信息",function(){});
		return;
	}
	
	location.href = uri.replace(/#id#/, id);
}

function modifyItem(uri){
	operationItem(uri,'更新');
}


function removeItem(uri, isDel){
	var flag = false;
	var id = '';
	var breakFuc = false;
	$("[name='ids']").each(function(){
		if($(this).attr("checked")){
			if(!flag){
				flag = true;
				id = $(this).val();
			}
			else{
				top.Dialog.alert("最多只能选择一条记录删除！|提示信息",function(){});
				breakFuc = true;
				return false;
			}
		}  
	});
	if(breakFuc){
		return;
	}
	if(!flag){
		top.Dialog.alert("请选择一个记录删除！|提示信息",function(){});
		return;
	}
	
	top.Dialog.confirm(
        "删除后将无法恢复，确认删除？",
        function(){
            removeOpteration(uri.replace(/#id#/, id), isDel);
        }, function(){});
}

function viewItem(uri){
	var flag = false;
	var id = '';
	var breakFuc = false;
	$("[name='ids']").each(function(){
		if($(this).attr("checked")){
			if(!flag){
				flag = true;
				id = $(this).val();
			}
			else{
				top.Dialog.alert("最多只能选择一条记录查看！|提示信息",function(){});
				breakFuc = true;
				return false;
			}
		}  
	});
	if(breakFuc){
		return;
	}
	if(!flag){
		top.Dialog.alert("请选择一个记录查看！|提示信息",function(){});
		return;
	}
	
	location.href = uri.replace(/#id#/, id);	
}

function removeOpteration(uri, isDel){
	if (!isDel) {
		// 原先逻辑
        var frm = document.forms[0];
        frm.action=uri;
        frm.submit();
	} else {
        $.ajax({
            url: uri,
            dataType: "json",
            type: "delete",
            error: function() {
                top.Dialog.alert("删除失败");

            },
            success: function() {
                window.location.reload();
                top.Dialog.alert("删除成功");
            }

        })
	}

}

function toAction(uri, tooltip, method, multi, limit){
	confirm = method == ('post'||'POST');
	multi = multi || false;
	limit = limit || 'table';
	var $checked = $(limit + ' [name="ids"]:checked');
	var len = $checked.length;
	if(len == 0){
		top.Dialog.alert('请选择一条记录进行' + tooltip + '操作！|提示信息');
		return;
	}else if(!multi && len != 1){
		top.Dialog.alert('只能选择一条记录进行' + tooltip + '操作！|提示信息');
		return;
	}
	var value = new Array(len);
	$checked.each(function(i, item){
		value[i] = $(this).val();
	});
	if(typeof uri  == "string"){
		uri = uri.replace(/#id#/g, value.join());
	}else{
		uri.URL = uri.URL.replace(/#id#/g, value.join());
	}
	if(confirm){
		top.Dialog.confirm('确认进行'+ tooltip +'操作？', function(){toLocation(uri, method);}, function(){return false;});
	}else{
		toLocation(uri);
	}
}

function toLocation(uri, method){
	method = method || 'get';
	if(method == 'get'){
		if(typeof uri  == "string"){
			window.location.href = uri;
		}else{
			uri.show();
		}
	}else{
		$("<form></form>").attr('method', method).attr('action', uri).submit();
	}
}
