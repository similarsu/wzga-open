/**
* 根据参数拍照，并预览照片
* depends on *device/camera.js*
*
* @author ray
* @since 2014/11/03
*/
(function($) {
    var  hiddenAttr4PreviewImg = "__data_inx";

    // 根据width/height比例，计算inputW的inputH
    var calHeight = function(width, height, inputW) {
        return Math.floor(height / width * inputW);
    }

    /**
    *
    * @param prefix web path prefix
    * @param imageW  图片宽度
    * @param imageH  图片高度
    * @param width 显示照相机的宽度
    * @param height 显示照相机的高度
    * @param previewElementIDs 预览图片的DOM id
    * @param previewW 预览图片的宽度, 高度根据imageW/imageH自动计算
    * @param isFullPreview 预览是否可全屏预览
    * @param fullPreviewW 全屏预览的宽度， 高度自动计算
    * @param zoomStep 放大缩小的步长
    * @param photoActionEleID 拍照按钮ID
    * @param photoZoomActionEleIDs 放大缩小还原按钮DOM ID数组
    * @param cameraEleId 展示照相DOM id
    * @param cbBeforePhotoAction 拍照按钮点击前调用 function(CameraX)
    * @param cbAfterPhotoAction 拍照按钮点击后调用 function(img, previewEleID)
    * @param cbPreviewClick 预览图片点击回调 function(target)
    */
    var camera = function(
        prefix,
        imageW, imageH,
        width, height,
        previewElementIDs, previewW,
        isFullPreview, fullPreviewW,
        zoomStep,
        photoActionEleID,
        photoZoomActionEleIDs,
        cameraEleId,
        cbBeforePhotoAction,
        cbAfterPhotoAction,
        cbPreviewClick
        ) {
        // default 2 寸照片 413 * 600
        this._iW = imageW ? imageW : 413;
        this._iH = imageH ? imageH : 600;

        // 展示相机大小
        this._sW = width ? width : this._iW;
        this._sH = height ? height : this._iH;

        this._pEleIDs = previewElementIDs && 0 < previewElementIDs.length
            ? previewElementIDs : undefined;
        this._pW = previewW ? previewW : 100;
        this._pH = calHeight(this._iW, this._iH, this._pW);

        this._isFP = isFullPreview;
        this._fPW = fullPreviewW ? fullPreviewW : this._iW;
        this._fPH = calHeight(this._iW, this._iH, this._fPW);

        this._zoomStep = zoomStep && 0 < zoomStep
             ? zoomStep : 1;

        this._pActionID = photoActionEleID;

        this._zoomActionIDs = photoZoomActionEleIDs && 0 < photoZoomActionEleIDs.length
            ? photoZoomActionEleIDs : undefined;

        this._cameraEleId = cameraEleId;

        this._imageData = []; // 图片数据
        this._imageCurInx = 0;

        this._preCB = cbBeforePhotoAction;
        this._postCB = cbAfterPhotoAction;
        this._previewCB = cbPreviewClick;

        this._prefix = prefix;

        // call init
        this.initPreviewEvts();
    };

    camera.prototype.isInstalled = function(callback) {
        if (!$.Camera) {
            var self = this;
            $.getScript(this._prefix + "/libs/js/device/camera.js", function() {
                self._cameraObj = new $.Camera(self._cameraEleId, self._sW, self._sH);
                callback(self._cameraObj.isValid());
            });

        } else {
            if (this._cameraObj) {
                callback(this._cameraObj.isValid());

            } else {
                this._cameraObj = new $.Camera(this._cameraEleId, this._sW, this._sH);
                callback(this._cameraObj.isValid());

            }

        }
    };

    /**
    * bind events
    */
    camera.prototype.init = function() {
        // init camera
        var self = this,
            camera = this._cameraObj;

        // bind take photo action
        if (this._pActionID) {
            var ele = $("#" + this._pActionID);
            this._originClickFn = ele.data("events")["click"][0]["handler"];

            ele.unbind("click").click(function() {
                if (self._preCB) {
                    self._preCB.call(self);
                }

                var img = camera.captureToBase64();
                var imgEle;

                self._imageData[self._imageCurInx] = img;

                if (self._isFP && self._pEleIDs) {
                    // 有预览元素则循环
                    var tolLen = self._pEleIDs.length;
                    if (self._imageCurInx >= tolLen) {
                        self._imageCurInx = 0;
                    }

                    imgEle = self._pEleIDs[self._imageCurInx]
                    self.previewImage(imgEle, img);

                }

                self._imageCurInx ++;

                if (self._postCB) {
                    self._postCB.call(self, img, imgEle);
                }

            });
        }

        // bind zoom action
        if (this._zoomActionIDs) {
            var len = this._zoomActionIDs.length;

            // zoom positive
            if (0 < len) {
                $("#" + this._zoomActionIDs[0]).click(function() {
                    var curZoomPos = camera.getZoomPos();
                    camera.setZoomPos( curZoomPos + self._zoomStep);

                });
            }

            // zoom negetive
            if (1 < len) {
                $("#" + this._zoomActionIDs[1]).click(function() {
                    var curZoomPos = camera.getZoomPos();
                    camera.setZoomPos( curZoomPos - self._zoomStep);

                });
            }

            // zoom 100%
            if (2 < len) {
                $("#" + this._zoomActionIDs[2]).click(function() {
                    camera.setZoomPos(0);

                });
            }
        }

    };

    // init preview event
    camera.prototype.initPreviewEvts = function() {
        var self = this;

        // bind preview if anny
        if (this._isFP && this._pEleIDs) {
            var getPreviewInx = function(ele) {
                var previewInx = ele.parent().attr(hiddenAttr4PreviewImg);
                var inx = 0;
                if (previewInx) {
                    inx = parseInt(previewInx);
                }
                return  inx;
            };

            for (var i=0; i<this._pEleIDs.length; i++) {
                $("#" + this._pEleIDs[i])
                    .attr(hiddenAttr4PreviewImg, i)
                    .attr("title", "点击后重新拍照")
                    .click(function(evt) {
                        // set current image to inx
                        self._imageCurInx = getPreviewInx($(evt.target));

                        if (self._previewCB) {
                            self._previewCB(evt.target);
                        }

                    })
                    .mouseover(function(evt) {
                        var ele = $(evt.target);
                        var inx = getPreviewInx(ele);
                        var imgSrc;
                        var content =
                            inx >= self._imageData.length ? undefined : self._imageData[inx];
                        if (!content) {
                            imgSrc = ele.attr("src");

                        } else {
                            imgSrc = self._cameraObj.getImageSrcBase64(content);

                        }

                        // 如果没有则不预览
                        if (!imgSrc || 1 > imgSrc.length) {
                            return;
                        }

                        var objIdDiv = $("#" + self._cameraEleId);
                        var objIdDivOffset = objIdDiv.offset();
                        // 展示全屏照片
                        Dialog.open({
                              InnerHtml:
                                  "<img src=\"" + imgSrc
                                    + "\" style=\"width:"
                                    + self._fPW + "px;height:" + self._fPH + "px\" />",
                              Width: self._fPW,
                              Height: self._fPH,
                              Top: objIdDivOffset.top - 30,
                              Left: objIdDivOffset.left + objIdDiv.width(),
                              MessageTitle: "图片预览",
                              Modal: false
                        });

                    })
                    .mouseout(function() {
                        Dialog.close();

                    });
            }
        }

    };

    // auto choose f550 camera
    camera.prototype.autoChooseCameraInx = function() {
        var camera = this._cameraObj;
        if (!camera) {
            return;
        }

        var deviceCnt = camera.getDeviceCount();
        var chosenDeviceInx = -1;

        for (var i = 0; i < deviceCnt; i++) {
            var name = camera.getDeviceName(i);
            if (name && -1 < name.toLowerCase().indexOf("f550")) {
                chosenDeviceInx = i;
                break;
            }
        }

        return chosenDeviceInx;
    };

    camera.prototype.openCamera = function() {
        var cameraInx = this.autoChooseCameraInx();
        if (undefined == cameraInx) {
            return false;
        }

        return this._cameraObj.openCamera(cameraInx);
    };

    camera.prototype.closeCamera = function() {
        if (this._cameraObj) {
            this._cameraObj.closeCamera();
            this._cameraObj.cleanUp();

            // unbind evts
            if (this._pActionID) {
                var ele = $("#" + this._pActionID);
                ele.unbind("click");

                if (this._originClickFn) {
                    ele.click(this._originClickFn);
                }
            }


            if (this._isFP && this._pEleIDs) {
                for (var i=0; i<this._pEleIDs.length; i++) {
                    $("#" + this._pEleIDs[i])
                        .unbind("click")
                        .unbind("mouseout")
                        .unbind("mouseover");
                }

            }


            if (this._zoomActionIDs) {
                for (var i=0; i<this._zoomActionIDs.length; i++) {
                    $("#" + this._zoomActionIDs[i])
                        .unbind("click");
                }
            }


        }
    };

    // get image data by index
    camera.prototype.getImageData = function(inx) {
        if (undefined == inx) {
            return this._imageData;
        }

        if (this._imageData && this._imageData.length > inx) {
            return this._imageData[inx];
        }
        return null;
    };

    camera.prototype.previewImage = function(target, img) {
        var w = this._pW,
            h = this._pH;

        if (2 < arguments.length) {
            w = arguments[2];
            h = calHeight(this._iW, this._iH, w);
        }

        this._cameraObj.showCapture2Target(
            target,
            this._cameraObj.getImageSrcBase64(img),
            w, h
        );
    };

    /**
    * <p>获取当前选中的照片值</p>
    *
    */
    camera.prototype.getChosenPreviewInx = function() {
        return this._imageCurInx;
    };

    /**
    * <p>设置选中的照片</p>
    *
    * @param inx 从1开始
    */
    camera.prototype.setChosenPreviewInx = function(index) {
        var inx = index - 1;
        if (0> inx || !this._imageData || inx >= this._imageData.length) {
            return;
        }
        this._imageCurInx = inx;
    };


    $.CameraX  = camera;
})(jQuery);
