function pageReady() {
//    var getResultCode = function() {
//        var url = window.location.href;
//        var questionMarkPos = url.indexOf("?");
//        if (0 > questionMarkPos) {
//            return 3;
//        }
//
//        var query = url.substring(questionMarkPos+1);
//        var params = query.split("&");
//        if (!params || 1 > params.length) {
//            return 3;
//        }
//
//        for (var i=0; i<params.length; i++) {
//            var pair = params[i].split("=");
//            if (2 > pair.length) {
//                continue;
//            }
//
//            if ("e" == pair[0]) {
//                return parseInt(pair[1]);
//            }
//        }
//
//        return 3;
//    };

    var getImgParent = function(target) {
        var t = $(target);
        var parent = t.parent();
        var tagName = t.prop("tagName").toLowerCase();
        /*
            if tag == img:
                get parent parent
            else:
                get parent
            假的背景图使用backgroundimage放进去， 而预览则是img标签，分别处理

        */
        if ("img" == tagName) {
            t = parent;
            parent = parent.parent();
        }

        return [t, parent];
    };

    var selectCurImg = function(target) {
        // clean up
        getImgParent($("#" + lastSelectPreviewImgId))[1].removeClass("this");
        $("#list div[class=allow]").remove();

        var tmpParent = getImgParent(target);
        var t = tmpParent[0],
            parent = tmpParent[1];

        parent.addClass("this");
        $("<div class=\"allow\"></div>").appendTo(parent);

        // set last id
        lastSelectPreviewImgId = t.attr("id");
    };

    // 判断是否保存成功
//    switch(getResultCode()) {
//        case 0:
//            top.Dialog.alert("保存失败");
//            break;
//        case 1:
//            top.Dialog.alert("保存成功");
//            break;
//
//    }

    var previewNames = ["previewOne", "previewTwo", "previewThree"];
    var lastSelectPreviewImgId = previewNames[0];
    var cameraFileNames = ["leftPicFile", "midPicFile", "rightPicFile"];
    var uploadFileNames = ["leftUploadPicFile", "midUploadPicFile", "rightUploadPicFile"];

    // init camera obj
    $._varCamera = new $.CameraX(
        META_DATA_MAPPING["prefix"],
        413, 600,
        301, 403,
        previewNames, 84,
        true, 220,
        1, "photoAction", ["photoZoomDeinc", "photoZoomZero", "photoZoomInc"],
        "cameraObj", undefined, function(img, previewEleID) {
            this.previewImage("photoPreview", img);
            selectCurImg($("#" + previewEleID));
        }
    );

    var showClickTip = function() {
        top.Dialog.alert("请先点击拍照按钮初始化");
    };


    // bind take photo
    $("#photoAction").click(function() {
        checkCameraIsInstalled(undefined, $);
    });

    // 设置默认选中图片
    selectCurImg($("#" + lastSelectPreviewImgId));

    $("#savePhotoAction").click(function() {
    	var isAllValueEmpty = true;
    	
        if ($._varCamera) {
            for (var i=0; i<3; i++) {
                var val = $._varCamera.getImageData(i);
                if (val) {
                    $("#" + cameraFileNames[i]).val(val);
                    isAllValueEmpty = false;
                }
            }
        }

        // close camera
        autoCloseCamera();

        // 移动三个input到form
        for(var i=0; i<uploadFileNames.length; i++) {
            /*
            校验文件后缀名字
            */
            var ele = $("#" + uploadFileNames[i]);
            var path = ele.val();
            if (!ele || !path || 1 > path.length) {
                continue;
            }
            isAllValueEmpty = false;
            if (!/^.*\.(jpeg|jpg)$/ig.exec(path)) {
                top.Dialog.alert("文件格式错误");
                return;
            }

            ele.detach()
                .appendTo("#photoForm");
        }
        //如果所有照片数据都为空，则不保存
        if(isAllValueEmpty){
        	top.Dialog.alert("请选择需要保存的照片！");
        	return;
        }
        $("#photoForm").submit();
    });

    // bind qc tab actived event to auto close camera
    $("#__collectTab").bind("actived", autoCloseCamera);

}
