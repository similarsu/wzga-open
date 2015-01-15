(function($) {
  var obj = {};
  $.Card = obj;

  // ecard
	/**
	* 构造函数
	* @Usage
	*	(function($) {
	*		var eCard = new Card.ECard();
	*		if (!eCard.init() || !ecard.connect()) {
	*			// 错误处理
	*			return;
	* 		}
	*		var cardInfo = eCard.read();
  *   eCard.destory();
	*
	*	})(jQuery);
	*/
	obj.ECard = function(id) {
        $("<object id=\"" + id + "\" type=\"application/x-carde\" style=\"display:none;\"></object>")
             .appendTo("html > body");
		this.ele = document.getElementById(id);
	};

	/**
	* 初始化设备
	* @reutrn true | false
	*/
	obj.ECard.prototype.init = function() {
	    try {
            return this.ele.init();
	    } catch(e) {}
	    return false;
	};

	/**
	* 连接设备
	* @reutrn true | false
	*/
	obj.ECard.prototype.connect = function() {
	    try{
            return this.ele.connect();
	    } catch (e) {}
	    return false;
	};

	/**
	* 读取E居卡内容
	* @return
  * {
  *   "type": 1:e居卡, "cardID": string|byte array|int, "idCardNo": 身份证号, "tel": 联系电话, "name": 姓名,
  *   "curResidence": 现住地, "regResidence": 户籍地
  * }
	*/
	obj.ECard.prototype.read = function() {
		return this.ele.read();
	};

  /**
  * 清理控件
  * @return
  */
  obj.ECard.prototype.destory = function() {
		this.ele.destroy();
	};

  // id card
  /**
  * 读取二代身份证信息类
  * @Usage
  * (function($){
  *   var reader = new $.Card.IDCard();
  *   if (!reader.isValid()) {
  *     // print error
  *   }
  *   if (1 == reader.read()) {
  *     reader.getName();
  *   }
  *
  * })(jQuery);
  */
  obj.IDCard = function() {
    $("<object style=\"display:none\" classid=\"clsid:5EB842AE-5C49-4FD8-8CE9-77D4AF9FD4FF\" id=\"IdrControl1\"></object>")
      .appendTo("body");
    try {
        new ActiveXObject("IDRCONTROL.IdrControlCtrl.1")
        this._obj = IdrControl1;
    } catch (e) {}
  };

  /**
  *
  * 读取二代证信息，必须在其他方法前调用。
  *
  * @return
  *     1: success, -2: 重新放卡， 其他：失败
  */
  obj.IDCard.prototype.read = function() {
    return this._obj.ReadCard("1001", "c:\\idcardReadTmp.jpg");
  };

  obj.IDCard.prototype.isValid = function() {
    return this._obj;
  }


  var idcardActivexMethos2Expose = [
    "GetName",
    "GetFolk",
    "GetSex",
    "GetBirthYear",
    "GetBirthMonth",
    "GetBirthDay",
    "GetCode",
    "GetAddress",
    "GetAgency",
    "GetValid",
    "GetCardPhotobuf",
    "GetIDCardSN"
  ];

  var lowerCapitalStr = function(content) {
    if (!content || 1 > content.length) {
      return "";
    }

    return content.charAt(0).toLowerCase() + content.substring(1);
  };

  for (var i=0; i<idcardActivexMethos2Expose.length; i++) {
    var methodName = idcardActivexMethos2Expose[i];
    obj.IDCard.prototype[lowerCapitalStr(methodName)] = new Function("return this._obj." + methodName + "();");
  }


})(jQuery);
