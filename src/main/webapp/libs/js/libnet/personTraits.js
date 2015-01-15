function closeCamera() {
    if (jQuery._varCamera) {
        jQuery._varCamera.closeCamera();
        delete jQuery._varCamera;
    }
}

function bindEvts() {
    $("#saveBtn").click(function() {
        var weight = $("#weight").val();
        var height = $("#height").val();
        var traitsDesc = $("#traitsDesc").val();
        var uPic = $("input[name='uPic']").val();
        var isAllValueEmpty = true;
        if(weight||height||traitsDesc!='黄头发，有纹身，左手六指'||uPic){
        	isAllValueEmpty = false;
        }
        if ($._varCamera) {
            // 保存所有到form
            var imgs = $._varCamera.getImageData();
            if (!imgs || 1 > imgs.length) {
                return;
            }
            isAllValueEmpty = false;
            var form = $("#personTraitsForm");
            for (var i=0; i<imgs.length; i++) {
                $("<input />")
                    .attr("name", "pic")
                    .attr("type", "hidden")
                    .attr("value", imgs[i])
                    .appendTo(form);

            }
        }
        closeCamera();
        if(isAllValueEmpty){
        	top.Dialog.alert("没有需要保存的信息！");
        	return;
        }
        // validate height and weight
        if (height&&!/^[1-9][0-9]{1,}\.[0-9]$|^[1-9][0-9]{1,}$/ig.exec(height.trim())) {
            top.Dialog.alert("身高错误，格式如下:165, 165.4");
            return;
        }
        $("#height").val(height.trim());
        if (weight&&!/^[1-9][0-9]{1,}\.[0-9]$|^[1-9][0-9]{1,}$/ig.exec(weight.trim())) {
            top.Dialog.alert("体重错误，格式如下:65, 65.4");
            return;
        }
        $("#weight").val(weight.trim());
        $("#personTraitsForm").submit();
    });
}

function getImageDom(img, isRawSrc) {
    var img = $("<img />")
        .attr("src", isRawSrc ? img : getImgSrc(img))
        .css("width", window.imgWidth)
        .css("height", window.imgHeight);

    var wrapper = $("<div class=\"timao_pic\"></div>");
    var wrapperOut = $("<div></div>");
    wrapper.html(img);
    wrapperOut.html(wrapper);
    return wrapperOut;
}

function getImgSrc(img) {
    return "data:image/jpeg;base64," + img;
};

function initPhoto() {

    var postPhotoAction = function(img) {
        $("#photoPreview > img").attr("src", getImgSrc(img));
        append2Scroll($._varCamera.getImageData());
    };

    $("#photoAction").click(function() {
        $._varCamera = new $.CameraX(
            META_DATA_MAPPING["prefix"],
            413, 600,
            301, 403,
            undefined, window.imgWidth,
            false, undefined,
            1, "photoAction", ["photoZoomDeinc", "photoZoomZero", "photoZoomInc"],
            "cameraObj", undefined, postPhotoAction
        );

        checkCameraIsInstalled(META_DATA_MAPPING["prefix"], $);
    });

    // bind qc tab actived event to auto close camera
    $("#__collectTab").bind("actived", autoCloseCamera);

    // bind scroll
    $("#ui-carousel-next")
        .add("#ui-carousel-prev")
        .add(".bullet")
        .hover(
            function(){
                $(this).css("opacity", 0.7);
            },
            function(){
                $(this).css("opacity", 1.0);
            }
        );
}

function initLocalUploadPhoto() {
    $("#localUpload").click(function(evt) {
        var tagName = $(evt.target).get(0).tagName.toLowerCase();
        if ("a" != tagName) {
            return;
        }

        if ($(evt.target).hasClass("wupin_add2")) {
            // 增加按钮
            var html = ["<div> <input name=\"uPic\" type=\"file\" />"];
            html[html.length] = "<a class=\"wupin_delete timao_add\" href=\"javascript:void(0)\"></a>";
            html[html.length] = "</div>";

            $(html.join("")).insertBefore($("#uploadTipDiv")).children("input").render();

        } else if ($(evt.target).hasClass("wupin_delete")) {
            // 删除按钮
            $(evt.target).parent().remove();
        }

    });

}

function pageReady() {
    renderScrollImg();
    bindEvts();
    initPhoto();
    initLocalUploadPhoto();
}

function renderScrollImg() {
    // render img in db
    var imgsCount = 0;
    if (window.images && 0 < window.images.length) {
        var imgArr = window.images;
        var imgs = $();
        for (var i=0; i<imgArr.length; i++) {
            var tmp = getImageDom(imgArr[i].url, true);
            tmp.attr("rinx", i + 1);
            tmp.attr("iId", imgArr[i].id);
            imgs = imgs.add(tmp);
        }

        $("#carousel").append(imgs);
        imgsCount = imgArr.length;


    }

    var defaultImgCount = 3;
    // add min elements
    var leftImgs =  defaultImgCount - imgsCount;
    if (0 < leftImgs) {
        for (var i=0; i<leftImgs; i++) {
            $("#carousel").append(getImageDom(window.previewImgURL, true));
        }
    }

    initScroll(defaultImgCount);

}

function initScroll(visibleCount) {
   $("#carousel").rcarousel({
            visible: visibleCount,
            step: 1,
            speed: 700,
            orientation: "vertical",
            auto: {
                enabled: true
            },
            width: window.imgWidth,
            height: window.imgHeight
    });
}

function append2Scroll(imgs) {
    var dom = getImageDom(imgs[imgs.length-1]);
    dom.attr("inx", imgs.length-1);

    $("#carousel").rcarousel("append", $().add(dom));

}
