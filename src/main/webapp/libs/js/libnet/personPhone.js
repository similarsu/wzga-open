
//根据身份证号获取名址信息
function  getNameAddr(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ws/hl/wz/nameaddr/txhm/norepeat/findbysfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				jsonpCallback: 'mzxx_callback', 
				error:function(){
					//alert("请求名址信息接口服务失败");
					$("#mzDiv").find("table").append('<tr><td class="center_tb font_bold red_1" colspan="2">请求名址信息接口服务失败！</td></tr>');
				}
			}
	);
}

//提取名址信息中的手机号码
function mzxx_callback(mzInfo){
	if(mzInfo!=null){
		var mzDiv = $("#mzDiv");
		if(mzInfo.length==0){
			mzDiv.find("table").append('<tr><td class="center_tb font_bold red_1" colspan="2">无记录</td></tr>');
		}else{
			for(var i=0;i<mzInfo.length;i++){
				mzDiv.find("table").append('<tr><td class="center_tb">'+(i+1)+'</td>'
						+'<td>'+mzInfo[i]+'</td></tr>');
			}
		}
	}
}