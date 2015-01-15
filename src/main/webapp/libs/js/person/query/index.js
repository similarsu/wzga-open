// bind events
function pageReady() {
    $("#idCardForm").submit(function() {
        var idcard = jQuery("#idCardNo").val().trim();
        if (idcard == "" || !checkIdcard(idcard)) {
            top.Dialog.alert('<div class="red">' + META_DATA_MAPPING["idcardFormat"] + '</div>|错误');
            return false;
        }
        $("#idCardNoHidden").val(idcard);
        return true;
    });

    var checkIDDevice = function() {
        var showError = function() {
            var msg = ['<div class="red_1"><span>'];
            msg[msg.length] = META_DATA_MAPPING["idcardInitErr"];
            msg[msg.length] = "</span>&nbsp;"
            msg[msg.length] = "<a class=\"blue_1\" href=\"" + META_DATA_MAPPING["idcardDriverDownloadURI"]
                + "\" target=\"_blank\" >" + META_DATA_MAPPING["idcardDriverDownloadURIText"] + "</a>,";
            msg[msg.length] = "<a class=\"blue_1\" href=\"" +
                META_DATA_MAPPING["idcardDownloadURI"]
                + "\" target=\"_blank\" >" + META_DATA_MAPPING["idcardDownloadURIText"] + "</a>";
            msg[msg.length] = '</div>|错误';
            top.Dialog.alert(msg.join(""));
        };

        if (!$.Card || !$.Card.IDCard) {
            showError();
            return false;
        }

        $._varCard = !$._varCard ? new $.Card.IDCard() : $._varCard;

        if (!$._varCard || !$._varCard.isValid()) {
            showError();
            return false;
        }

        return true;
    };

    var checkECardDevice = function() {
        var showError = function() {
            // dialog
            var msg = ['<div class="red_1"><span>'];
            msg[msg.length] = META_DATA_MAPPING["eCardInitErrorText"];
            msg[msg.length] = "</span>&nbsp;"
            msg[msg.length] = "<a class=\"blue_1\" href=\"" + META_DATA_MAPPING["eCardDriverURI"]
                + "\" target=\"_blank\" >" + META_DATA_MAPPING["eCardDriverText"] + "</a>,";

            msg[msg.length] = "<a class=\"blue_1\" href=\"" +
                META_DATA_MAPPING["idcardDownloadURI"]
                + "\" target=\"_blank\" >" + META_DATA_MAPPING["eCardActiveXText"] + "</a>";

            msg[msg.length] = '</div>|错误';
            top.Dialog.alert(msg.join(""));

        };

        if (!$.Card || !$.Card.ECard) {
            showError();
            return false;
        }

        if (!$._varECard) {
            $._varECard = new $.Card.ECard();
            if (!$._varECard.init()) {
                showError();
                return false;
            }
        }

        if (!$._varECard.connect()) {
            showError();
            return false;
        }

        return true;
    };

    $(document).ready(function() {
        $("#qcTab").bind("actived", function(evt, tabId) {
            // clean up camera for device bugs
            autoCloseCamera();

            if (4 == tabId) {
                window.location.href = nameOnlyCollectURL;

            } else if (1 == tabId) {
                // 姓名核查
                var iframe = $("#nameIframe");
                if (!iframe.attr("src")) {
                    iframe.attr("src", "https://rk-zyk.zx.ga/businessConsole/xxfw/czrkquery/baseinfoquery/index.jsp");
                }

            }

        });

        $("#idCardSubmitBtn").click(function() {
            $("#idCardForm").submit();
        });

        // bind id card read
        $("#readIDCardFromDevice").click(function() {
            if (!checkIDDevice()) {
                return;
            }

            /*
            * read from device
            * if 1 != code:
            *   alert error
            * set idCardNo value
            * commit form
            */

            var readCode = $._varCard.read();
            switch(readCode) {
                case 1:
                    $("#idCardNo").val($._varCard.getCode());
                    $("#idCardForm").submit();
                    break;

                case -2:
                    top.Dialog.alert('<div class="red">' + META_DATA_MAPPING["idcardReaderReplaceText"] + '</div>|错误');
                    break;

                default:
                    top.Dialog.alert('<div class="red">' + META_DATA_MAPPING["idcardReaderErrText"] + '</div>|错误');
                    break;
            }

        });

        // bind e card btn
        $("#eCardReadBtn").click(function() {
            if (!checkECardDevice()) {
                return;
            }

            var eCardInfo = $._varECard.read();
            $("#idCardNo").val(eCardInfo["idCardNo"]);
            $("#idCardForm").submit();
        });

        // bind name submit button
        $("#nameQueryBtn").click(function() {
            if (!$("#idNoCopy").val()) {
                top.Dialog.alert("请输入身份证号");
                return false;
            }
            $("#idCardNo").val($("#idNoCopy").val());
            $("#idCardForm").submit();
        });


        // show cmp page
        var showFaceCmp = function(content) {
            $(document.body).unmask();
            $("#faceCMPDiv").html(content);
            $("#faceList").css("display", "block");
        };

        // 人脸比对回调函数
        var renderFaceCMPResult = function(list) {
            if (!list || 1 > list.length) {
                showFaceCmp("人脸比对暂无数据");
                return;
            }

            var html = [];

            for(var i=0; i<list.length; i++) {
                var tmp = list[i];
                html[html.length] = "<a class=\"float_left single single_t\" idNo=\"" + tmp["sfzh"] + "\" >";
                html[html.length] = "<img width=\"90\" src=\"data:image/jpeg;base64," + tmp["zp"] + "\" />";
                html[html.length] = "<h1>" + tmp["xm"] + "</h1>"
                html[html.length] = "<p>" + tmp["sfzh"] + "</p></a>"

            }

            html[html.length] = "<div class=\"height_20\"></div>";

            $("#faceCMPDiv")
                .unbind("click")
                .click(function(evt) {
                    autoCloseCamera();
                    // get sfzh
                    // commit form
                    var target = $(evt.target);
                    var tagName = target.get(0).tagName.toLowerCase();
                    var tagA = undefined;

                    if ("a" == tagName) {
                        tagA = target;

                    } else {
                        tagA = target.parent();

                    }


                    $("#idCardNo").val(tagA.attr("idNO"));
                    $("#idCardForm").submit();

            });

            showFaceCmp(html.join(""));
        };


        // bind photo action
        $("#photoAction").click(function() {
            $._varCamera = new $.CameraX(
                META_DATA_MAPPING["prefix"],
                413, 600,
                301, 403,
                undefined, 100,
                false, undefined,
                1, "photoAction", ["photoZoomInc", "photoZoomDeinc", "photoZoomZero"],
                "cameraObj", undefined,
                function(img) {
                    // 预览图片
                    this.previewImage("photoPreview", img);
                    /*
                    调用soa
                    展示返回结果
                    bind click
                    */
                    $(document.body).mask("正在比对, 请耐心等待。。。");
                    $.ajax({
                        url: faceCmpURL,
                        data: {"pic": img},
                        dataType: "json",
                        type: "POST",
                        error: function(){
                            showFaceCmp("人脸比对接口调用失败");
                        },

                        success: function(obj) {
                            if (!obj) {
                                showFaceCmp("比对失败");
                                return;
                            }

                            if (obj.hasOwnProperty("errorCode")) {
                                showFaceCmp("比对失败，错误代码: [" + obj["errorCode"] + "]");
                                return;
                            }

                            if (obj.hasOwnProperty("data")) {
                                renderFaceCMPResult($.parseJSON(obj["data"])["data"]);
                                return
                            }

                            showFaceCmp("比对失败");
                        }
                    });
                }
            );

            checkCameraIsInstalled(META_DATA_MAPPING["prefix"], $);
        });

    });

    // 绑定分页
    $("#pageBox").bind("pageChange",function(e,index){
        $("#pageNo").val(index+1);
        $("#unColListForm").submit();
    });


    // render dept query
    $("#deptName").click(function() {
        var deptID = $("#deptCode").val();
        deptID = !deptID ? -1 : deptID;
		var diag = new top.Dialog();
		diag.Title = "单位选择";
		diag.URL = META_DATA_MAPPING["prefix"] + "/ajax/dept/" + deptID + "/init";
		diag.Width = 450;
		diag.OKEvent = function() {
            diag.innerFrame.contentWindow.sureHandler(undefined, undefined, undefined, 1);
		};
		diag.show();
    });
}
