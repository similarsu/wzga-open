var ar_is=new Array(2);
ar_is[0]="否";ar_is[1]="是";

function sdhc(ip,sfzh,yh){ 
	// 是否逃犯（全国逃犯）
	ajax_qgtf(ip,sfzh);
	// 是否负案在逃（温州综合）
	ajax_fazt(ip,sfzh);
	// 是否指纹比重未归案
	ajax_zwbzwga(ip,sfzh);
	// 是否DNA比重未归案
	ajax_dnabzwga(ip,sfzh);
	// 是否打防控人员
	ajax_dfkry(ip,sfzh);
	// 全国违法犯罪人员
	ajax_qgwffzry(ip,sfzh);
	// 涉毒信息（全国吸贩毒人员）
	ajax_sdxx(ip,sfzh);
	// 嫌疑指数
	ajax_xyzs(ip,sfzh,yh);
	// 背景布控
	ajax_bjbk(ip,sfzh);
}

// 是否逃犯（全国逃犯）
function  ajax_qgtf(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/ztry/is/jb/0000/sffy/0/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#sftf").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sftf").html(ar_is[obj.is]);
						if(obj.is==1){$("#sftf").addClass("red_1");}
					}else{
						$("#sftf").html("查无记录");
					}
				},
				error:function(){
					$("#sftf").removeClass("loading_bar");
					$("#sftf").html("请求全国逃犯接口失败");
				}
			}
	);
}

// 是否负案在逃（温州综合）
function  ajax_fazt(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/faztry/is/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#sffazt").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sffazt").html(ar_is[obj.is]);
						if(obj.is==1){$("#sffazt").addClass("red_1");}
					}else{
						$("#sffazt").html("查无记录");
					}
				},
				error:function(){
					$("#sffazt").removeClass("loading_bar");
					$("#sffazt").html("请求温州负案在逃接口失败");
				}
			}
	);
}

// 是否指纹比中未归案
function  ajax_zwbzwga(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/zw/is/sfga/0/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#sfzwbzwga").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfzwbzwga").html(ar_is[obj.is]);
						if(obj.is==1){$("#sfzwbzwga").addClass("red_1");}
					}else{
						$("#sfzwbzwga").html("查无记录");
					}
				},
				error:function(){
					$("#sfzwbzwga").removeClass("loading_bar");
					$("#sfzwbzwga").html("请求指纹比中未归案接口失败");
				}
			}
	);
}

// 是否DNA比中未归案
function  ajax_dnabzwga(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/dna/is/sfga/0/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#sfdnabzwga").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfdnabzwga").html(ar_is[obj.is]);
						if(obj.is==1){$("#sfdnabzwga").addClass("red_1");}
					}else{
						$("#sfdnabzwga").html("查无记录");
					}
				},
				error:function(){
					$("#sfdnabzwga").removeClass("loading_bar");
					$("#sfdnabzwga").html("请求DNA比中未归案接口失败");
				}
			}
	);
}

// 打防控人员
function  ajax_dfkry(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/dfkry/info/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#dfkry").empty();
					var obj=res["data"];
					if(obj!=''){
						var tbody="";
						$.each(obj,function(i,item){
							tbody+="<tr>";
							tbody+="<td>"+item.rybh+"</td>";
							tbody+="<td>"+item.tbdw+"</td>";
							tbody+="<td>"+item.tbr+"</td>";
							tbody+="<td>"+item.tbsj+"</td>";
							var content='';
							content+='<table class="tableStyle" formMode="line" footer="normal">';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">人员编号</td> ';
							content+='  <td  width="20%">'+item.rybh+'</td>';
							content+='  <td  width="10%" class="blue_1 font_bold">人员类别</td> ';
							content+='  <td  width="20%">'+item.rylb+'</td> ';
							content+='  <td  width="10%" class="blue_1 font_bold">人像编号</td> ';
							content+='  <td  width="20%">'+item.rxbh+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">姓名</td> ';
							content+='  <td>'+item.xm+'</td>';
							content+='  <td class="blue_1 font_bold">性别</td> ';
							content+='  <td>'+item.xb+'</td> ';
							content+='  <td class="blue_1 font_bold">出生日期</td> ';
							content+='  <td>'+item.csrq+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">籍贯</td> ';
							content+='  <td>'+item.jg+'</td>';
							content+='  <td class="blue_1 font_bold">身份证号</td> ';
							content+='  <td>'+item.sfzh+'</td> ';
							content+='  <td class="blue_1 font_bold">文化程度</td> ';
							content+='  <td>'+item.whcd+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">身份</td> ';
							content+='  <td>'+item.sf+'</td>';
							content+='  <td class="blue_1 font_bold">工作单位</td> ';
							content+='  <td>'+item.gzdw+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">身高</td> ';
							content+='  <td>'+item.sg+'</td>';
							content+='  <td class="blue_1 font_bold">脸型</td> ';
							content+='  <td>'+item.lx+'</td> ';
							content+='  <td class="blue_1 font_bold">体型</td> ';
							content+='  <td>'+item.tx+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">指纹编号</td> ';
							content+='  <td>'+item.zwbh+'</td>';
							content+='  <td class="blue_1 font_bold">DNA编号</td> ';
							content+='  <td>'+item.dnabh+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">户籍地</td> ';
							content+='  <td colspan="5">'+item.hjdxz+'</td>';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">现住址</td> ';
							content+='  <td colspan="5">'+item.xzzxz+'</td>';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">录入人</td> ';
							content+='  <td>'+item.lrr+'</td>';
							content+='  <td class="blue_1 font_bold">录入单位</td> ';
							content+='  <td>'+item.lrdw+'</td> ';
							content+='  <td class="blue_1 font_bold">录入时间</td> ';
							content+='  <td>'+item.lrsj+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">填表人</td> ';
							content+='  <td>'+item.tbr+'</td>';
							content+='  <td class="blue_1 font_bold">填表单位</td> ';
							content+='  <td>'+item.tbdw+'</td> ';
							content+='  <td class="blue_1 font_bold">填表时间</td> ';
							content+='  <td>'+item.tbsj+'</td> ';
							content+='</tr> ';
							content+='</table>';
							tbody+="<td><a onclick=\"showDetail('dfkry"+i+"','打防控人员详细信息')\">详情</a><input id='dfkry"+i+"' type='hidden' value='"+content+"'/></td>";
							tbody+="<tr>";
						});
						$("#dfkry").html(tbody);
					}else{
						$("#dfkry").html("<tr><td colspan=\"5\">查无记录</td></tr>");
					}
				},
				error:function(){
					$("#dfkry").empty();
					$("#dfkry").html("<tr><td colspan=\"5\">请求打防控人员接口失败</td></tr>");
				}
			}
	);
}

// 全国违法犯罪人员
function  ajax_qgwffzry(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/wffzry/info/sffy/1/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#qgwffzry").empty();
					var obj=res["data"];
					if(obj!=''){
						var tbody="";
						$.each(obj,function(i,item){
							tbody+="<tr>";
							tbody+="<td>"+item.xm+"</td>"; 
							tbody+="<td>"+item.xb+"</td>";
							tbody+="<td>"+item.sfzh+"</td>"; 
							tbody+="<td>"+item.hjdxz+"</td>";
							var content='';
							content+='<table class="tableStyle" formMode="line" footer="normal">';
							content+='<tr class=" bg_tb" >';        
							content+='  <td class="blue_1 font_bold">姓名</td> ';
							content+='  <td>'+item.xm+'</td>';
							content+='  <td class="blue_1 font_bold">性别</td> ';
							content+='  <td>'+item.xb+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">曾用名</td> ';
							content+='  <td>'+item.cym+'</td>';
							content+='  <td class="blue_1 font_bold">身份证号</td> ';
							content+='  <td>'+item.sfzh+'</td> ';
							content+='  <td class="blue_1 font_bold">文化程度</td> ';
							content+='  <td>'+item.whcd+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">身高</td> ';
							content+='  <td>'+item.sg+'</td>';
							content+='  <td class="blue_1 font_bold">足长</td> ';
							content+='  <td>'+item.zc+'</td> ';
							content+='  <td class="blue_1 font_bold">专长</td> ';
							content+='  <td>'+item.zhuanc+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">案件编号</td> ';
							content+='  <td>'+item.ajbh+'</td>';
							content+='  <td class="blue_1 font_bold">案件类别</td> ';
							content+='  <td>'+item.ajlb+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">简要案情</td> ';
							content+='  <td colspan="5">'+item.jyaq+'</td>';
							content+='</tr> ';
							content+='<tr>';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">户籍地</td> ';
							content+='  <td colspan="5">'+item.hjdxz+'</td>';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">居住地</td> ';
							content+='  <td colspan="5">'+item.jzdxz+'</td>';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">入所原因</td> ';
							content+='  <td>'+item.rsyy+'</td>';
							content+='  <td class="blue_1 font_bold">入库日期</td> ';
							content+='  <td>'+item.rksj+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">出所原因</td> ';
							content+='  <td>'+item.csyy+'</td>';
							content+='  <td class="blue_1 font_bold">出所日期</td> ';
							content+='  <td>'+item.csrq+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='</table>';
							tbody+="<td><a onclick=\"showDetail('wffzry"+i+"','违法犯罪人员详细信息')\">详情</a><input id='wffzry"+i+"' type='hidden' value='"+content+"'/></td>";
							tbody+="<tr>";
						});
						$("#qgwffzry").html(tbody);
					}else{
						$("#qgwffzry").html("<tr><td colspan=\"5\">查无记录</td></tr>");
					}
				},
				error:function(){
					$("#qgwffzry").empty();
					$("#qgwffzry").html("<tr><td colspan=\"5\">请求全国违法犯罪人员接口失败</td></tr>");
				}
			}
	);
}

// 涉毒信息（省厅涉毒人员）
function  ajax_sdxx(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/sdry/info/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#sdxx").empty();
					var obj=res["data"];
					if(obj!=''){
						var tbody="";
						$.each(obj,function(i,item){
							tbody+="<tr>";
							tbody+="<td>"+item.xm+"</td>"; 
							tbody+="<td>"+item.xb+"</td>";
							tbody+="<td>"+item.sfzh+"</td>"; 
							tbody+="<td>"+item.hjdxz+"</td>";
							var content='';
							content+='<table class="tableStyle" formMode="line" footer="normal">';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">姓名</td> ';
							content+='  <td>'+item.xm+'</td>';
							content+='  <td class="blue_1 font_bold">性别</td> ';
							content+='  <td>'+item.xb+'</td> ';
							content+='  <td class="blue_1 font_bold">出生日期</td> ';
							content+='  <td>'+item.csrq+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">绰号</td> ';
							content+='  <td>'+item.ch+'</td>';
							content+='  <td class="blue_1 font_bold">证件类型</td> ';
							content+='  <td>'+item.zjzl+'</td> ';
							content+='  <td class="blue_1 font_bold">证件号码</td> ';
							content+='  <td>'+item.sfzh+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">身高</td> ';
							content+='  <td>'+item.sg+'</td>';
							content+='  <td class="blue_1 font_bold">文化程度</td> ';
							content+='  <td>'+item.whcd+'</td> ';
							content+='  <td class="blue_1 font_bold">身份</td> ';
							content+='  <td>'+item.sf+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold"> 服务场所</td> ';
							content+='  <td colspan="5">'+item.gzdw+'</td>';
							content+='</tr> ';
							content+='<tr>'; 
							content+='<tr>';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">户籍地</td> ';
							content+='  <td colspan="5">'+item.hjdxz+'</td>';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">居住地</td> ';
							content+='  <td colspan="5">'+item.jzdxz+'</td>';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">指纹编号</td> ';
							content+='  <td>'+item.zwbh+'</td>';
							content+='  <td class="blue_1 font_bold">在逃类型</td> ';
							content+='  <td>'+item.ztlx+'</td> ';
							content+='  <td class="blue_1 font_bold">注射毒品种类</td> ';
							content+='  <td>'+item.zsdpzl+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">原填报单位</td> ';
							content+='  <td>'+item.ytbdw+'</td>';
							content+='  <td class="blue_1 font_bold">现状登记日期</td> ';
							content+='  <td>'+item.xzdjrq+'</td> ';
							content+='  <td class="blue_1 font_bold">滥用毒品种类</td> ';
							content+='  <td>'+item.xsdpzl+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">吸毒后果</td> ';
							content+='  <td>'+item.xdhg+'</td>';
							content+='  <td class="blue_1 font_bold">吸毒场所</td> ';
							content+='  <td>'+item.xdcs+'</td> ';
							content+='  <td class="blue_1 font_bold">吸毒人员实有标识</td> ';
							content+='  <td>'+item.xdbs+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">特殊体貌特征</td> ';
							content+='  <td>'+item.tstmtz+'</td>';
							content+='  <td class="blue_1 font_bold">填报地区</td> ';
							content+='  <td>'+item.tbdq+'</td> ';
							content+='  <td class="blue_1 font_bold">涉毒人员编号</td> ';
							content+='  <td>'+item.sdrybh+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">人员现状</td> ';
							content+='  <td>'+item.ryxz+'</td>';
							content+='  <td class="blue_1 font_bold">其他证件号码</td> ';
							content+='  <td>'+item.qtzjhm+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">健康状况</td> ';
							content+='  <td>'+item.jkzk+'</td>';
							content+='  <td class="blue_1 font_bold">婚姻状况</td> ';
							content+='  <td>'+item.hyzk+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">录入人</td> ';
							content+='  <td>'+item.lrr+'</td>';
							content+='  <td class="blue_1 font_bold">录入单位</td> ';
							content+='  <td>'+item.lrdw+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">列管日期</td> ';
							content+='  <td>'+item.lgrq+'</td>';
							content+='  <td class="blue_1 font_bold">列管民警</td> ';
							content+='  <td>'+item.lgmj+'</td> ';
							content+='  <td class="blue_1 font_bold">列管单位详称</td> ';
							content+='  <td>'+item.lgdwxc+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">列管日期</td> ';
							content+='  <td>'+item.lgrq+'</td>';
							content+='  <td class="blue_1 font_bold">列管民警</td> ';
							content+='  <td>'+item.lgmj+'</td> ';
							content+='  <td class="blue_1 font_bold">列管单位详称</td> ';
							content+='  <td>'+item.lgdwxc+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">毒品犯罪嫌疑人类型</td> ';
							content+='  <td>'+item.fzxyrlx+'</td>';
							content+='  <td class="blue_1 font_bold">法律文书编号</td> ';
							content+='  <td>'+item.flwsbh+'</td> ';
							content+='  <td class="blue_1 font_bold">单位联系电话</td> ';
							content+='  <td>'+item.dwlxdh+'</td> ';
							content+='</tr> ';
							content+='<tr class=" bg_tb">';        
							content+='  <td class="blue_1 font_bold">登记日期</td> ';
							content+='  <td>'+item.djrq+'</td>';
							content+='  <td class="blue_1 font_bold">初次吸毒日期</td> ';
							content+='  <td>'+item.ccxdrq+'</td> ';
							content+='  <td class="blue_1 font_bold">初次吸毒查获日期</td> ';
							content+='  <td>'+item.ccchrq+'</td> ';
							content+='</tr> ';
							content+='<tr>';        
							content+='  <td class="blue_1 font_bold">本人联系方法</td> ';
							content+='  <td>'+item.brlxdh+'</td>';
							content+='  <td class="blue_1 font_bold">备注</td> ';
							content+='  <td>'+item.bz+'</td> ';
							content+='  <td class="blue_1 font_bold"></td> ';
							content+='  <td></td> ';
							content+='</tr> ';
							content+='</table>';
							tbody+="<td><a onclick=\"showDetail('sdry"+i+"','省厅涉毒人员详细信息')\">详情</a><input id='sdry"+i+"' type='hidden' value='"+content+"'/></td>";
							tbody+="<tr>";
						});
						$("#sdxx").html(tbody);
					}else{
						$("#sdxx").html("<tr><td colspan=\"5\">查无记录</td></tr>");
					}
				},
				error:function(){
					$("#sdxx").empty();
					$("#sdxx").html("<tr><td colspan=\"5\">请求省厅涉毒人员接口失败</td></tr>");
				}
			}
	);
}

// 嫌疑指数
function  ajax_xyzs(ip,sfzh,yh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/xyzs/info/yh/"+yh+"/zdlx/1/hclx/2/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#fs").removeClass("loading_bar");
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
					}else{
						$("#fs").html("查无记录");
						$("#clcs").html("查无记录");
					}
				},
				error:function(){
					$("#fs").removeClass("loading_bar");
					$("#fs").html("请求嫌疑指数接口失败");
					$("#clcs").removeClass("loading_bar");
					$("#clcs").html("请求嫌疑指数接口失败");
				}
			}
	);
}

// 背景布控（浙江）
function  ajax_bjbk(ip,sfzh){
	$.ajax(
			{
				type:"get",
				url:ip+"/ry/bjbk/info/sfzh/"+sfzh+".json",
				dataType:"jsonp",
				jsonp:"_jsonp",
				success:function(res){
					$("#sfbkdx").removeClass("loading_bar");
					var obj=res["data"];
					if(obj!=''){
						$("#sfbkdx").html(obj.jg);
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