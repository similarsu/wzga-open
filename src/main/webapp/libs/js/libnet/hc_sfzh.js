var ar_is=new Array(2);
ar_is[0]="否";ar_is[1]="是";
var arr_check=[false,false,false,false,false,false,false,false,false,false,false,false];
function jbhc(ip,sfzh,yh){ 
	//是否逃犯（全国逃犯）
	ajax_qgtf(ip,sfzh);
	//是否负案在逃（温州综合）
	ajax_fazt(ip,sfzh);
	//是否指纹比中未归案
	ajax_zwbzwga(ip,sfzh);
	//是否DNA比中未归案
	ajax_dnabzwga(ip,sfzh);
	//是否指纹比中已归案
	ajax_zwbzyga(ip,sfzh);
	//是否DNA比中已归案
	ajax_dnabzyga(ip,sfzh);
	//是否打防控人员
	ajax_dfkry(ip,sfzh);
	//全国违法犯罪人员
	ajax_qgwffzry(ip,sfzh);
	//涉毒信息（全国吸贩毒人员）
	ajax_sdxx(ip,sfzh);
	//暂住地址
	ajax_zzdz(ip,sfzh);
	//嫌疑指数
	ajax_xyzs(ip,sfzh,yh);
	//背景布控
	ajax_bjbk(ip,sfzh);
}  

//是否逃犯（全国逃犯）
function  ajax_qgtf(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/ztry/is/jb/0000/sffy/0/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[0]=true;
					$("#sftf").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sftf").html(ar_is[obj.is]);
						$("#sftfHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#sftf").addClass("red_1");}
					}else{
						$("#sftf").html("查无记录");
					}
				},
				error:function(){
					arr_check[0]=true;
					$("#sftf").removeClass("loading_bar");
					$("#sftf").html("请求全国逃犯接口失败");
				}
			}
	);
}

//是否负案在逃（温州综合）
function  ajax_fazt(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/faztry/is/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[1]=true;
					$("#sffazt").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sffazt").html(ar_is[obj.is]);
						$("#sffaztHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#sffazt").addClass("red_1");}
					}else{
						$("#sffazt").html("查无记录");
					}
				},
				error:function(){
					arr_check[1]=true;
					$("#sffazt").removeClass("loading_bar");
					$("#sffazt").html("请求温州负案在逃接口失败");
				}
			}
	);
}

//是否指纹比中未归案
function  ajax_zwbzwga(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/zw/is/sfga/0/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[2]=true;
					$("#sfzwbzwga").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfzwbzwga").html(ar_is[obj.is]);
						$("#sfzwbzwgaHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#sfzwbzwga").addClass("red_1");}
					}else{
						$("#sfzwbzwga").html("查无记录");
					}
				},
				error:function(){
					arr_check[2]=true;
					$("#sfzwbzwga").removeClass("loading_bar");
					$("#sfzwbzwga").html("请求指纹比中未归案接口失败");
				}
			}
	);
}

//是否DNA比中未归案
function  ajax_dnabzwga(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/dna/is/sfga/0/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[3]=true;
					$("#sfdnabzwga").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfdnabzwga").html(ar_is[obj.is]);
						$("#sfdnabzwgaHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#sfdnabzwga").addClass("red_1");}
					}else{
						$("#sfdnabzwga").html("查无记录");
					}
				},
				error:function(){
					arr_check[3]=true;
					$("#sfdnabzwga").removeClass("loading_bar");
					$("#sfdnabzwga").html("请求DNA比中未归案接口失败");
				}
			}
	);
}

//是否打防控人员
function  ajax_dfkry(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/dfkry/is/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[4]=true;
					$("#dfkry").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#dfkry").html(ar_is[obj.is]);
						$("#sfdfkryHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#dfkry").addClass("red_1");}
					}else{
						$("#dfkry").html("查无记录");
					}
				},
				error:function(){
					arr_check[4]=true;
					$("#dfkry").removeClass("loading_bar");
					$("#dfkry").html("请求打防控人员接口失败");
				}
			}
	);
}

//全国违法犯罪人员
function  ajax_qgwffzry(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/wffzry/is/sffy/1/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[5]=true;
					$("#qgwffz").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#qgwffz").html(ar_is[obj.is]);
						$("#sfqgwffzHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#qgwffz").addClass("red_1");}
					}else{
						$("#qgwffz").html("查无记录");
					}
				},
				error:function(){
					arr_check[5]=true;
					$("#qgwffz").removeClass("loading_bar");
					$("#qgwffz").html("请求全国违法犯罪人员接口失败");
				}
			}
	);
}

//涉毒信息（省厅涉毒人员）
function  ajax_sdxx(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/sdry/is/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[6]=true;
					$("#sdxx").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sdxx").html(ar_is[obj.is]);
						$("#sfsdxxHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#sdxx").addClass("red_1");}
					}else{
						$("#sdxx").html("查无记录");
					}
				},
				error:function(){
					arr_check[6]=true;
					$("#sdxx").removeClass("loading_bar");
					$("#sdxx").html("请求全国吸贩毒人员接口失败");
				}
			}
	);
}

//是否指纹比中已归案
function  ajax_zwbzyga(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/zw/is/sfga/1/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[7]=true;
					$("#sfzwbzyga").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfzwbzyga").html(ar_is[obj.is]);
						$("#sfzwbzygaHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#sfzwbzyga").addClass("red_1");}
					}else{
						$("#sfzwbzyga").html("查无记录");
					}
				},
				error:function(){
					arr_check[7]=true;
					$("#sfzwbzyga").removeClass("loading_bar");
					$("#sfzwbzyga").html("请求指纹比中已归案接口失败");
				}
			}
	);
}

//是否DNA比中已归案
function  ajax_dnabzyga(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/dna/is/sfga/1/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[8]=true;
					$("#sfdnabzyga").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfdnabzyga").html(ar_is[obj.is]);
						$("#sfdnabzygaHidden").val(ar_is[obj.is]);
						if(obj.is==1){$("#sfdnabzyga").addClass("red_1");}
					}else{
						$("#sfdnabzyga").html("查无记录");
					}
				},
				error:function(){
					arr_check[8]=true;
					$("#sfdnabzyga").removeClass("loading_bar");
					$("#sfdnabzyga").html("请求DNA比中已归案接口失败");
				}
			}
	);
}

//暂住地址（温州综合）
function  ajax_zzdz(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/zzrk/zzdz/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[9]=true;
					$("#zzdz").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#zzdz").html(obj.zzdz);
					}else{
						$("#zzdz").html("查无记录");
					}
				},
				error:function(){
					arr_check[9]=true;
					$("#zzdz").removeClass("loading_bar");
					$("#zzdz").html("请求暂住地址接口失败");
				}
			}
	);
}

//嫌疑指数
function  ajax_xyzs(ip,sfzh,yh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/xyzs/info/yh/"+yh+"/zdlx/1/hclx/1/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[10]=true;
					$("#fs").attr("class","xianyi_text");
					$("#clcs").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#fs").html(obj.fs);
						$("#clcs").html(obj.clcs);
						$("#rate").css("background-color",obj.ys);
						$("#rate").Rating({
							readOnly:true,
							start:parseInt(obj.gs),
							number:parseInt(obj.gs)
						});
						$("#fsHidden").val(obj.fs);
						$("#gsHidden").val(obj.gs);
						$("#clcsHidden").val(obj.clcs);
						$("#ysHidden").val(obj.ys);
					}else{
						$("#fs").html("查无记录");
						$("#clcs").html("查无记录");
					}
				},
				error:function(){
					arr_check[10]=true;
					$("#fs").removeClass("loading_bar");
					$("#fs").html("请求嫌疑指数接口失败");
					$("#clcs").removeClass("loading_bar");
					$("#clcs").html("请求嫌疑指数接口失败");
				}
			}
	);
}

//背景布控（浙江）
function  ajax_bjbk(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/bjbk/info/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					arr_check[11]=true;
					$("#sfbkdx").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfbkdx").html(obj.jg);
						$("#sfbkdxHidden").val(obj.jg);
					}else{
						$("#sfbkdx").html("查无记录");
					}
				},
				error:function(){
					arr_check[11]=true;
					$("#sfbkdx").removeClass("loading_bar");
					$("#sfbkdx").html("请求背景布控接口失败");
				}
			}
	);
}
