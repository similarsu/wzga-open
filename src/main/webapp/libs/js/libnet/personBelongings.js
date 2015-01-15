
//车辆信息
  function vehicleInit(){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/vehicle/add");
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">号牌号码：</td><td></td><td class="callback"></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">号牌种类：</td><td></td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">车架号码：</td><td></td><td class="callback"></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">车辆品牌：</td><td></td><td></td></tr>');
	  var $code = $('<input name="vehicleCode" type="text" style="width: 170px;" class="validate[required]" onchange="checkCarByVehicleCode(\''+soaLink+'\',this)" />');
	  var $licence = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)" class="validate[required]"></select>');
	  var $carframeCode = $('<input name="carframeCode" type="text" style="width: 170px;" class="validate[required]" onchange="checkCarByCarframe(\''+soaLink+'\',this)" />');
	  var $brand = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)" class="validate[required]"></select>');
	  $addContent.find('td').eq(1).append($code);
	  $addContent.find('td').eq(4).append($licence).append('<input type="hidden" name="licenceType">');
	  $addContent.find('td').eq(7).append($carframeCode);
	  $addContent.find('td').eq(10).append($brand).append('<input type="hidden" name="brand">');
	  $addContent.render();
	  $code.render();
	  $licence.data("data",dataLicenceType);
	  $licence.render();
	  $carframeCode.render();
	  $brand.data("data",dataVBrand);
	  $brand.render();
	  fileContentInit();
  }
  //手机信息
  function phoneInit(){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/phone/add");
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">手机串号：</td><td></td><td class="callback"></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">手机型号：</td><td></td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">手机品牌：</td><td></td><td></td></tr>'
	  					+'<tr class="height_35"><td class="blue_1 font_bold">手机号码：</td><td></td><td></td></tr>');
	  var $imei = $('<input name="imei" type="text" style="width: 170px;" class="validate[required]" onchange="checkPhoneByImei(\''+soaLink+'\',this)" />');
	  var $model = $('<input name="model" type="text" style="width: 170px;" />');
	  var $brand = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $number = $('<input name="phoneNumber" type="text" style="width: 170px;" class="validate[required,custom[mobilephone]]"/>');
	  $addContent.find('td').eq(1).append($imei);
	  $addContent.find('td').eq(4).append($model);
	  $addContent.find('td').eq(7).append($brand).append('<input type="hidden" name="brand">');
	  $addContent.find('td').eq(10).append($number);
	  $addContent.render();
	  $imei.render();
	  $model.render();
	  $brand.data("data",dataPBrand);
	  $brand.render();
	  $number.render();
	  fileContentInit();
  }
  
  //---------------- 电瓶车 车辆品牌 模拟模糊查询相关 开始 ---------------------
  
  function setBatteryCarBrand(jqueryObj){
	  $("#brandName").val(jqueryObj.attr("key"));
	  $("input[name='brand']").val(jqueryObj.attr("value"));
	  closeSearchResult();
  }
  
  function searchInit(jqueryObj){
	  var searchResultStyle = 'position:absolute;width:170px;height:150px;z-index:10001;display:none;overflow:auto;';
	  jqueryObj.after('<div></div><div id="searchResult" class="white_bg border_gray" style="'+searchResultStyle+'"><table style="width:90%;"></table><div>');
	  var $searchResult = $('#searchResult');
	  $searchResult.mouseleave(function(){
		  closeSearchResult();
	  });
	  jqueryObj.typeWatch({captureLength:0,highlight:false,wait:100,callback:searchBatteryCarBrand});
	  jqueryObj.bind("focus",function(){
		  searchBatteryCarBrand(this.value);
	  });
  }
  
  function searchBatteryCarBrand(brandName){
	  if(!brandName) return;
	  var $searchResult = $("#searchResult");
	  var $resultTable = $searchResult.find('table');
	  $("input[name='brand']").val('');
	  $resultTable.empty();
	  for(var i=0;i< dataBBrand.list.length;i++){
		  if(dataBBrand.list[i].key.match(brandName)){
			  var $tr = $('<tr value="'+dataBBrand.list[i].value+'" key="'+dataBBrand.list[i].key+'"><td style="border:0px;"><span>'+dataBBrand.list[i].key+'</span></td></tr>');
			  $tr.hover(function(){
				  $(this).css("background-color","#469ce8");
			  },function(){
				  $(this).css("background-color","");
			  });
			  $tr.bind("click",function(){
				  setBatteryCarBrand($(this));
			  });
			  if(dataBBrand.list[i].key == brandName){
				  setBatteryCarBrand($tr);
			  }
			  $resultTable.append($tr);
		  }
	  }
	  $searchResult.show();
  }
  
  function closeSearchResult(){
	  $("#searchResult table").empty();
	  $("#searchResult").hide();
  }
  
  function isBatteryCarCorrect(){
	  if($("#brandName").val()){
		  if(!$("input[name='brand']").val()){
			  return false;
		  }
	  }
	  return true;
  }
  
  // ---------------- 电瓶车 车辆品牌 模拟模糊查询相关 结束 -------------------
  
  //电瓶车信息
  function batteryCarInit(){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/batterycar/add");
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">号牌号码：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">车辆类型：</td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">颜色代码：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">电机号：</td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">车架号码：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">车辆品牌：</td><td></td></tr>');
	  var $vehicleCode = $('<input name="vehicleCode" type="text" style="width: 170px;" class="validate[required]"/>');
	  var $vehicleType = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $colorCode = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $engineCode = $('<input name="engineCode" type="text" style="width: 170px;" class="validate[required]"/>');
	  var $carframeCode = $('<input name="carframeCode" type="text" style="width: 170px;" class="validate[required]"/>');
	  var $brand = $('<input id="brandName" type="text" style="width: 170px;"/>');
	  //var $brand = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  $addContent.find('td').eq(1).append($vehicleCode);
	  $addContent.find('td').eq(3).append($vehicleType).append('<input type="hidden" name="vehicleType">');
	  $addContent.find('td').eq(5).append($colorCode).append('<input type="hidden" name="colorCode">');
	  $addContent.find('td').eq(7).append($engineCode);
	  $addContent.find('td').eq(9).append($carframeCode);
	  $addContent.find('td').eq(11).append($brand).append('<input type="hidden" name="brand">');
	  $addContent.render();
	  $vehicleCode.render();
	  $vehicleType.data("data",dataBatteryCarType);
	  $vehicleType.render();
	  $colorCode.data("data",dataColor);
	  $colorCode.render();
	  $engineCode.render();
	  $carframeCode.render();
	  //$brand.data("data",dataBBrand);
	  $brand.render();
	  searchInit($brand);
	  fileContentInit();
  }
  
  //银行卡信息
  function bankCardInit(){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/bankcard/add");
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">银行帐号：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">开户银行：</td><td></td></tr>');
	  var $account = $('<input name="account" type="text" style="width: 170px;"  class="validate[required]"/>');
	  var $bank = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  $addContent.find('td').eq(1).append($account);
	  $addContent.find('td').eq(3).append($bank).append('<input type="hidden" name="bank">');
	  $addContent.render();
	  $account.render();
	  $bank.data("data",dataBank);
	  $bank.render();
	  fileContentInit();
  }
  //添加工具信息
  function toolInit(){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/tool/add");
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">工具类型：</td><td></td></tr>');
	  var $type = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)" class="validate[required]"></select>');
	  $addContent.find('td').eq(1).append($type).append('<input type="hidden" name="type">');
	  $addContent.render();
	  $type.data("data",dataType);
	  $type.render();
	  fileContentInit();
  }
  //新增物品
  function newBelongings(){
	  $("#belongingsType").val("vehicle").render();
	  vehicleInit();
  }
  //改变新增物品类型
  function changeBelongingsType(selectType){
	  var type = $(selectType).val();
	  switch(type){
	  	case 'vehicle' :vehicleInit();break;
	  	case 'batteryCar' :batteryCarInit();break;
	  	case 'phone' :phoneInit();break;
	  	case 'backCard' :bankCardInit();break;
	  	case 'tool' :toolInit();break;
	  }
  }
  
  //文件浏览控件初始化
  function fileContentInit(){
	  var $fileContent = $("#fileContent");
	  $fileContent.empty();
	  var $fileTable = $('<table class="tableStyle" formMode="transparent" footer="normal">'
				+'<tr class="height_35"><td width="25%" class="blue_1 font_bold">物品照片：</td><td></td><td><a class="wupin_add2" href="#" onclick="addFile()"></a></td></tr></table>');
	  var $file = $('<input type="file" name="picFile" />');
	  $fileTable.find('td').eq(1).append($file);
	  $fileContent.append($fileTable);
	  $fileTable.render();
	  $file.render();
  }
  
  //新增文件浏览控件
  function addFile(){
	  var $fileTable = $('<table class="tableStyle" formMode="transparent" footer="normal">'
			  				+'<tr class="height_35"><td width="25%" class="blue_1 font_bold">物品照片：</td><td></td><td><a class="wupin_delete" href="#" onclick="deleteFile(this)"></a></td></tr></table>');
	  var $file = $('<input type="file" name="picFile"/>');
	  $fileTable.find('td').eq(1).append($file);
	  var $fileContent = $("#fileContent");
	  $fileContent.append($fileTable);
	  $fileTable.render();
	  $file.render();
  }
  //删除文件浏览控件
  function deleteFile(delBtn){
	  $(delBtn).parent().parent().parent().parent().remove();
  }
  
  //物品编辑初始化
  function editBelongings(editBtn){
	  var $belongingTr = $(editBtn).parent().parent();
	  var type = $belongingTr.attr("type");
	  $("#belongingsType").val(type).render();
	  switch(type){
	  	case 'vehicle' :vehicleEdit($belongingTr.attr("id"),
	  								$belongingTr.attr("vehicleCode"),
	  								$belongingTr.attr("licenceType"),
	  								$belongingTr.attr("carframeCode"),
	  								$belongingTr.attr("brand"),
	  								$belongingTr.attr("photoUrls"));break;
	  	case 'phone' :phoneEdit($belongingTr.attr("id"),
	  							$belongingTr.attr("imei"),
	  							$belongingTr.attr("model"),
	  							$belongingTr.attr("brand"),
	  							$belongingTr.attr("phoneNumber"),
	  							$belongingTr.attr("photoUrls"));break;
	  	case 'batteryCar' :batteryCarEdit($belongingTr.attr("id"),
	  									  $belongingTr.attr("vehicleCode"),
	  									  $belongingTr.attr("vehicleType"),
	  									  $belongingTr.attr("colorCode"),
	  									  $belongingTr.attr("engineCode"),
	  									  $belongingTr.attr("carframeCode"),
	  									  $belongingTr.attr("brand"),
	  									  $belongingTr.attr("brandName"),
	  									  $belongingTr.attr("photoUrls"));break;
	  	case 'bankCard' :bankCardEdit($belongingTr.attr("id"),
	  								  $belongingTr.attr("account"),
	  								  $belongingTr.attr("bank"),
	  								  $belongingTr.attr("photoUrls"));break;
	  	case 'tool' :toolEdit($belongingTr.attr("id"),
	  						  $belongingTr.attr("toolType"),
	  						  $belongingTr.attr("photoUrls"));break;
	  }
  }
  
  function deleteBelongings(belongingsTrId){
	  top.Dialog.confirm("确定要删除吗？",function(){
		  $("#"+belongingsTrId).remove();
	  },null);
  }
  
  //车辆信息编辑
  function vehicleEdit(id,code,licence,carframeCode,brand,photoUrls){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/vehicle/update").append('<input type="hidden" name="id" value="'+id+'">');
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">号牌号码：</td><td></td><td class="callback"></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">号牌种类：</td><td></td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">车架号码：</td><td></td><td class="callback"></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">车辆品牌：</td><td></td><td></td></tr>');
	  var $code = $('<input name="vehicleCode" type="text" style="width: 170px;" class="validate[required]" onchange="checkCarByVehicleCode(\''+soaLink+'\',this)" />');
	  var $licence = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)" class="validate[required]"></select>');
	  var $licenceHidden = $('<input type="hidden" name="licenceType">');
	  var $carframeCode = $('<input name="carframeCode" type="text" style="width: 170px;" class="validate[required]" onchange="checkCarByCarframe(\''+soaLink+'\',this)" />');
	  var $brand = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)" class="validate[required]"></select>');
	  var $brandHidden = $('<input type="hidden" name="brand">');
	  $addContent.find('td').eq(1).append($code);
	  $addContent.find('td').eq(4).append($licence).append($licenceHidden);
	  $addContent.find('td').eq(7).append($carframeCode);
	  $addContent.find('td').eq(10).append($brand).append($brandHidden);
	  $addContent.render();
	  
	  if(code)
		  $code.val(code);
	  $code.render();
	  $licence.data("data",dataLicenceType);
	  if(licence){
		  $licence.attr('selectedValue',licence);
		  $licenceHidden.val(licence);
	  }
	  $licence.render();
	  if(carframeCode)
		  $carframeCode.val(carframeCode);
	  $carframeCode.render();
	  $brand.data("data",dataVBrand);
	  if(brand){
		  $brand.attr('selectedValue',brand);
		  $brandHidden.val(brand);
	  }
	  $brand.render();
	  fileContentInit();
	  showPhotos(photoUrls);
  }
  
//手机信息编辑
  function phoneEdit(id,imei,model,brand,number,photoUrls){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/phone/update").append('<input type="hidden" name="id" value="'+id+'">');
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">手机串号：</td><td></td><td class="callback"></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">手机型号：</td><td></td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">手机品牌：</td><td></td><td></td></tr>'
	  					+'<tr class="height_35"><td class="blue_1 font_bold">手机号码：</td><td></td><td></td></tr>');
	  var $imei = $('<input name="imei" type="text" style="width: 170px;" class="validate[required]" onchange="checkPhoneByImei(\''+soaLink+'\',this)" />');
	  var $model = $('<input name="model" type="text" style="width: 170px;" />');
	  var $brand = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $brandHidden = $('<input type="hidden" name="brand">');
	  var $number = $('<input name="phoneNumber" type="text" style="width: 170px;" class="validate[required,custom[mobilephone]]"/>');
	  $addContent.find('td').eq(1).append($imei);
	  $addContent.find('td').eq(4).append($model);
	  $addContent.find('td').eq(7).append($brand).append($brandHidden);
	  $addContent.find('td').eq(10).append($number);
	  $addContent.render();
	  if(imei)
		  $imei.val(imei);
	  $imei.render();
	  if(model)
		  $model.val(model);
	  $model.render();
	  $brand.data("data",dataPBrand);
	  if(brand){
		  $brand.attr('selectedValue',brand);
		  $brandHidden.val(brand);
	  }
	  $brand.render();
	  if(number)
		  $number.val(number);
	  $number.render();
	  fileContentInit();
	  showPhotos(photoUrls);
  }
  //电瓶车信息编辑
  function batteryCarEdit(id,vehicleCode,vehicleType,colorCode,engineCode,carframeCode,brand,brandName,photoUrls){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/batterycar/update").append('<input type="hidden" name="id" value="'+id+'">');
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">号牌号码：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">车辆类型：</td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">颜色代码：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">电机号：</td><td></td></tr>'
			  			+'<tr class="bg_tb height_35"><td class="blue_1 font_bold">车架号码：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">车辆品牌：</td><td></td></tr>');
	  var $vehicleCode = $('<input name="vehicleCode" type="text" style="width: 170px;" class="validate[required]"/>');
	  var $vehicleType = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $vehicleTypeHidden = $('<input type="hidden" name="vehicleType">');
	  var $colorCode = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $colorCodeHidden = $('<input type="hidden" name="colorCode">');
	  var $engineCode = $('<input name="engineCode" type="text" style="width: 170px;" class="validate[required]"/>');
	  var $carframeCode = $('<input name="carframeCode" type="text" style="width: 170px;" class="validate[required]"/>');
	  var $brand = $('<input id="brandName" type="text" style="width: 170px;"/>');
	  //var $brand = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $brandHidden = $('<input type="hidden" name="brand">');
	  $addContent.find('td').eq(1).append($vehicleCode);
	  $addContent.find('td').eq(3).append($vehicleType).append($vehicleTypeHidden);
	  $addContent.find('td').eq(5).append($colorCode).append($colorCodeHidden);
	  $addContent.find('td').eq(7).append($engineCode);
	  $addContent.find('td').eq(9).append($carframeCode);
	  $addContent.find('td').eq(11).append($brand).append($brandHidden);
	  $addContent.render();
	  if(vehicleCode)
		  $vehicleCode.val(vehicleCode);
	  $vehicleCode.render();
	  $vehicleType.data("data",dataBatteryCarType);
	  if(vehicleType){
		  $vehicleType.attr('selectedValue',vehicleType);
		  $vehicleTypeHidden.val(vehicleType);
	  }
	  $vehicleType.render();
	  $colorCode.data("data",dataColor);
	  if(colorCode){
		  $colorCode.attr('selectedValue',colorCode);
		  $colorCodeHidden.val(colorCode);
	  }
	  $colorCode.render();
	  if(engineCode)
		  $engineCode.val(engineCode);
	  $engineCode.render();
	  if(carframeCode)
		  $carframeCode.val(carframeCode);
	  $carframeCode.render();
	  //$brand.data("data",dataBBrand);
	  if(brand){
		  $brand.val(brandName);
		  $brandHidden.val(brand);
	  }
	  $brand.render();
	  searchInit($brand);
	  fileContentInit();
	  showPhotos(photoUrls);
  }
  //银行卡信息编辑
  function bankCardEdit(id,account,bank,photoUrls){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/bankcard/update").append('<input type="hidden" name="id" value="'+id+'">');
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">银行帐号：</td><td></td></tr>'
			  			+'<tr class="height_35"><td class="blue_1 font_bold">开户银行：</td><td></td></tr>');
	  var $account = $('<input name="account" type="text" style="width: 170px;"  class="validate[required]"/>');
	  var $bank = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)"></select>');
	  var $bankHidden = $('<input type="hidden" name="bank">');
	  $addContent.find('td').eq(1).append($account);
	  $addContent.find('td').eq(3).append($bank).append($bankHidden);
	  $addContent.render();
	  if(account)
		  $account.val(account);
	  $account.render();
	  $bank.data("data",dataBank);
	  if(bank){
		  $bank.attr('selectedValue',bank);
		  $bankHidden.val(bank);
	  }
	  $bank.render();
	  fileContentInit();
	  showPhotos(photoUrls);
  }
  //添加工具信息编辑
  function toolEdit(id,toolType,photoUrls){
	  removeHiddenId();
	  $("#belongingsForm").attr("action",ctx+"/person/tool/update").append('<input type="hidden" name="id" value="'+id+'">');
	  var $addContent = $("#addContent");
	  $addContent.empty();
	  $addContent.append('<tr class="bg_tb height_35"><td width="25%" class="blue_1 font_bold">工具类型：</td><td></td></tr>');
	  var $type = $('<select selWidth="170" prompt="请选择" onchange="changeInputValue(this)" class="validate[required]"></select>');
	  var $typeHidden = $('<input type="hidden" name="type">');
	  $addContent.find('td').eq(1).append($type).append($typeHidden);
	  $addContent.render();
	  $type.data("data",dataType);
	  if(toolType){
		  $type.attr('selectedValue',toolType);
		  $typeHidden.val(toolType);
	  }
	  $type.render();
	  fileContentInit();
	  showPhotos(photoUrls);
  }
  
  function showPhotos(photoUrls){
	  var $photoContain =  $("#photoContain");
	  $photoContain.empty();
	  var $carousel = $('<div id="carousel" class="carousel3" ></div>');
	  if(!photoUrls) return;
	  var photoUrlArr = photoUrls.split(';');
	  for(var i=0;i<photoUrlArr.length;i++){
		  var $imgDiv = $('<div class="float_left margin_left10">'
				  			+'<a href="'+photoUrlPrefix+'/'+photoUrlArr[i]+'" class="highslide" onclick="return hs.expand(this)">'
				  		 +		'<img width="85" height="110" src="'+photoUrlPrefix+'/'+photoUrlArr[i]+'"/></a></div>');
		  $carousel.append($imgDiv);
	  }
	  $photoContain.append($carousel);
	  $photoContain.append('<a href="#" id="ui-carousel-next" class="ui-carousel-next3"><span></span></a>'
			  			  +'<a href="#" id="ui-carousel-prev" class="ui-carousel-prev3"><span></span></a>');
	  if(photoUrlArr.length>3)
		  imgContainInit();
  }
  
  // 设置与select相连的隐藏域值
  function changeInputValue(obj) {
	 var select = $(obj);
	 select.next("input").val(select.val());
  }
  
  function removeHiddenId(){
	  $("#belongingsForm").find("input[name=id]").remove();
  }
  
  
var $curTD;

//根据号牌号码获取机动车信息
function checkCarByVehicleCode(ip,elem){ 
	var $callbackTD = $(elem).parent().parent().find(".callback");
	$callbackTD.html('');
	//$callbackTD.html('<span class="img_loading" style="margin-left:-30px;"></span>');
	$curTD = $callbackTD;
	//ajax_func("vehicleCode",ip+"/ws/queryqg/gab/bdqjdc/wfy/isbyhphm/"+elem.value+".json","请求被盗抢车辆信息接口服务失败！"); 
	ajax_func("vehicleCode",ip+"/wp/bdqjdc/info/jb/0000/sffy/0/hphm/"+elem.value+".json","请求被盗抢车辆信息接口服务失败！"); 
} 

//根据车架号获取机动车信息
function checkCarByCarframe(ip,elem){
	var $callbackTD = $(elem).parent().parent().find(".callback");
	$callbackTD.html('');
	//$callbackTD.html('<span class="img_loading" style="margin-left:-30px;"></span>');
	$curTD = $callbackTD;
	//ajax_func("carframe",ip+"/ws/queryqg/gab/bdqjdc/wfy/isbycjh/"+elem.value+".json","请求被盗抢车辆信息接口服务失败！");
	ajax_func("carframe",ip+"/wp/bdqjdc/info/jb/0000/sffy/0/cjh/"+elem.value+".json","请求被盗抢车辆信息接口服务失败！");
}

//根据手机串号获取损失手机信息
function checkPhoneByImei(ip,elem){
	var $callbackTD = $(elem).parent().parent().find(".callback");
	$callbackTD.html('');
	//$callbackTD.html('<span class="img_loading" style="margin-left:-30px;"></span>');
	$curTD = $callbackTD;
	//ajax_func("imei",ip+"/ws/zyzh/wz/wp/sj/isbysjch/"+elem.value+".json","请求损失手机信息接口服务失败！");
	ajax_func("imei",ip+"/wp/sssj/is/sjch/"+elem.value+".json","请求损失手机信息接口服务失败！");
}

function ajax_func( type,url,msg){
	$.ajax(
			{
				type:"get",
				url:url,
				dataType:"jsonp",
				jsonp:"_jsonp",
				jsonpCallback: type+'_callback', 
				error:function(){
					$curTD.html('<div class="info_out" style="z-index:10001;">'
								+'<div class="info" style="width:250px;">'
								+'<span class="cause"></span><span class="Validform_checktip">'+msg+'</span>'
								+'<div class="close hand" onclick="deleteMessage(this)">X</div>'
								+'<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>'
								+'</div>'
								+'</div>');
				}
			}
	);
}

//被盗车辆信息
function vehicleCode_callback(msg){
	if(msg.data[0]){
		$curTD.html('<div class="info_out" style="z-index:10001;">'
				+'<div class="info" style="width:240px;">'
				+'<span class="cause"></span><span class="Validform_checktip">该号牌号码为被盗抢车辆号牌号码！</span>'
				+'<div class="close hand" onclick="deleteMessage(this)">X</div>'
				+'<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>'
				+'</div>'
				+'</div>');
	}else{
		$curTD.html("");
	}
}

function carframe_callback(msg){
	if(msg.data[0]){
		$curTD.html('<div class="info_out" style="z-index:10001;">'
				+'<div class="info" style="width:210px;">'
				+'<span class="cause"></span><span class="Validform_checktip">该车架号为被盗抢车辆车架号！</span>'
				+'<div class="close hand" onclick="deleteMessage(this)">X</div>'
				+'<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>'
				+'</div>'
				+'</div>');
	}else{
		$curTD.html("");
	}
}

function imei_callback(msg){
	if(msg.data.is){
		$curTD.html('<div class="info_out" style="z-index:10001;">'
				+'<div class="info" style="width:180px;">'
				+'<span class="cause"></span><span class="Validform_checktip">该串号为损失手机串号！</span>'
				+'<div class="close hand" onclick="deleteMessage(this)">X</div>'
				+'<span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>'
				+'</div>'
				+'</div>');
	}else{
		$curTD.html("");
	}
}

//删除被盗抢车辆、损失手机等提示信息条
function deleteMessage(delBtn){
	$(delBtn).parent().parent().html("");
}

//图片浏览初始化
function imgContainInit(){
	  
	 $("#carousel").rcarousel({
			visible: 3,
			orientation:'horizontal',
			step: 1,
			speed: 500,
			auto: {
				enabled: false
			},
			width: 90,
			height: 120
	 });
			
	 $("#ui-carousel-next").add("#ui-carousel-prev").hover(
	 function(){
		$(this).css("opacity", 0.7 );
	 },
	 function(){
		$(this).css("opacity", 1.0 );
	 });
 }
  