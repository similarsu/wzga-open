function getDnaCodeAjax(ip,sfzh){ 
	//DNA编号
	ajax_func("dna",ip+"/ry/dna/code/sfzh/" + sfzh + ".json","请求DNA接口服务失败!");
	
}  

function  ajax_func( type,url,msg){
	$(document.body).mask("正在加载...");
	$.ajax(
			{
				type:"get",
				url:url,
				dataType:"jsonp",
				jsonp:"_jsonp",
				jsonpCallback: type+'_callback', 
				error:function(){
					$("#callbackMsg").html(msg);
					$(document.body).unmask();
				}
			}
	);
}
 
//DNA编号
function dna_callback(msg){
	if(msg){
		if(msg["data"]["dnacode"]){
			$("#code").val(msg["data"]["dnacode"]);
			$("#code").attr("readonly","readonly");
			$("#callbackMsg").html("库中已有此人DNA信息，此处无需采集！");
		}
	}
	$(document.body).unmask();
} 
 
 
