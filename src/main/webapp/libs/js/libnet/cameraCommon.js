/**
* common util for camera operation
*
*/
var checkCameraIsInstalled = function(prefix, $) {
    var showErr = function(code) {
        var msg = ['<div class="red_1"><span>'];
        msg[msg.length] = META_DATA_MAPPING[code];
        msg[msg.length] = '</div>|错误';
        top.Dialog.alert(msg.join(""));
    };

    if (!$._varCamera) {
        // 2 寸
        $._varCamera = new $.CameraX(
            prefix,
            413, 600,
            301, 403,
            ["photoPreview"], 100,
            false, undefined,
            1, "photoAction", ["photoZoomDeinc", "photoZoomZero", "photoZoomInc"],
            "cameraObj", undefined, undefined
        );

    }

    var notInstalledShowErr  = function() {
        var msg = ['<div class="red_1"><span>'];
        msg[msg.length] = META_DATA_MAPPING["cameraNotInstalled"];
        msg[msg.length] = "</span>&nbsp;"
        msg[msg.length] = "<a class=\"blue_1\" href=\"" + META_DATA_MAPPING["cameraDriverURI"]
            + "\" target=\"_blank\" >" + META_DATA_MAPPING["cameraDriverText"] + "</a>,";
        msg[msg.length] = "<a class=\"blue_1\" href=\"" +
            META_DATA_MAPPING["cameraPluginDownloadURI"]
            + "\" target=\"_blank\" >" + META_DATA_MAPPING["cameraPluginText"] + "</a>";
        msg[msg.length] = '</div>|错误';
        top.Dialog.alert(msg.join(""));
        delete $._varCamera;
    };

    $._varCamera.isInstalled(function(isInstalled) {
        if (!isInstalled) {
            notInstalledShowErr();

        } else {
            $._varCamera.init();
            setTimeout(function() {
                if(!$._varCamera.openCamera()) {
                    $._varCamera.closeCamera();
                    delete $._varCamera;
                    showErr("cameraOpenFailure");
                    return;
                }
            }, 50);

        }
    });
};

var autoCloseCamera = function() {
    if ($._varCamera) {
        $._varCamera.closeCamera();
        delete $._varCamera;
    }
};
