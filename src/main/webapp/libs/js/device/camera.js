(function($) {
  $.Camera = function(elementIdAppend2, width, height, options) {
    this._extW = this._extH = 50;
    this._w = width;
    this._h = height;
    // 默认图片大小 2寸
    this._picW = 413;
    this._picH = 600;
    this._valid = true;

    if (4 < arguments.length) {
        this._picW = arguments[4];
    }

    if (5 < arguments.length) {
        this._picH = arguments[5];
    }

    // append object
    var objectId = this._objId = "CanonOcx";
    var self = this;
    $("<object id=\"" + objectId + "\" CLASSID=\"CLSID:7F7CC7E9-279F-44E2-AA86-4FEB85400D8F\">"
        + "<param name=\"wmode\" value=\"opaque\" >"
        + "<param name=\"wmode\" value=\"transparent\" >" +
        "</object>")
      .attr("width", this._w)
      .attr("height", this._h)
      .appendTo("#" + elementIdAppend2);

    // crack license
    $(document).ready(function() {
      var caOcx = document.getElementById(objectId);
      try {
          caOcx.CtrlID = 19016;
          caOcx.License('a03KJ)*@JHQ!)mvmna-OU(ladlf_', 8);
      } catch (e) {
          self._valid = false;
          return;
      }

      var realOptions = $.extend({
        ShowCross: true,
        EnableCrop: true,
        EnableResizeCrop: false,
        EnableMoveCrop: true,
        LangID: 1033,
        CropRectColor: 0xEDEDED,
        ShowCropMask: true
      }, !options ? {} : options);

      for (var key in realOptions) {
        caOcx[key] = realOptions[key];
      }

    });

    this._obj = document.getElementById(objectId);
  };

  // methods renames
  $.Camera.prototype.getDeviceName = function(inx) {
    return this._obj.EnumCamera(inx);
  };

  $.Camera.prototype.openCamera = function(inx) {
    var flag = this._obj.ConnectToCamera(inx) &&
      this._obj.VideoOn();

    if (!flag) {
      return false;
    }


    // ga standard
    this._obj.SetDPI(300);
    this._obj.SetJpegQuality(80);
    this._obj.SetVideoRotate(270);
    this.loadCropRect();

    return true;
    // ConnectToCamera
    // videoOn
    // LoadCropRect
  };

  $.Camera.prototype.getDeviceCount = function() {
    return this._obj.GetDeviceCount();
  };

  // 默认裁剪2寸照片
  $.Camera.prototype.captureToBase64 = function() {
    // 2 村照片
    var w = this._picW,
        h = this._picH;

    if (0 < arguments.length) {
        w = arguments[0];
    }
    if (1 < arguments.length) {
        h = arguments[1];
    }

    this._obj.AeAfAwb();
    this._obj.SetCropImageSize(false, w, h);
    return this._obj.CaptureToBase64();
  };

  $.Camera.prototype.closeCamera = function() {
    this._obj.VideoOff();
    this._obj.DisconnectCamera();
  };

  $.Camera.prototype.loadCropRect = function() {
    // must be called before set crop rect
    this._obj.LoadCropRect();

    this._cropW = Math.floor( this._w - this._extW );
    this._cropH = Math.floor( this._h - this._extH );

    var x = Math.floor( (this._w - this._cropW) / 2);
    var y = Math.floor( (this._h - this._cropH) / 2);

    this._obj.SetCropRect(x, y, this._cropW,  this._cropH);
  };

  /**
  * 在某个DOM下展示照片内容
  * @param eleId dom elelemnt ID
  * @param imgData data returned by {@link captureToBase64}
  * @return
  */
  $.Camera.prototype.showCapture2Target = function(eleId, imgData) {
    var width = this._cropW;
    var height = this._cropH;

    // default value
    if (!width) {
        width = height = 100;
    }

    if (2 < arguments.length) {
        width = arguments[2];
    }
    if (3 < arguments.length) {
        height = arguments[3];
    }

    $("#" + eleId).html(
        this.getPreviewImageHTML(imgData, width, height)
    );
  };

  $.Camera.prototype.getImageSrcBase64 = function(imgData) {
      return "data:image/jpeg;base64," + imgData;
  };

  $.Camera.prototype.getZoomPos = function() {
    return this._obj.GetZoomPos();
  };

  $.Camera.prototype.setZoomPos = function(pos) {
    return this._obj.SetZoomPos(Math.floor(pos));
  };

  $.Camera.prototype.isValid = function() {
    return this._valid;
  };

  $.Camera.prototype.cleanUp = function() {
    $("#" + this._objId).remove();
  };

  $.Camera.prototype.getPreviewImageHTML = function(imgData, width, height) {
      return "<img src=\"" + imgData
            + "\" style=\"width:" + width + "px;height:" + height + "px\" />";
  };

})(jQuery);
