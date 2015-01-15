function __iframe_auto_adjust(win) {
    if (win && !window.opera) {
        if (win.contentDocument && win.contentDocument.body.offsetHeight) {
            win.height = win.contentDocument.body.offsetHeight;
        } else if(win.Document && win.Document.body.scrollHeight) {
            win.height = win.Document.body.scrollHeight;
        }
    }
}

function renderPersonDetailPanel() {
    var floatEle = $("#__floatPanel");

    floatEle.ready(function() {
        floatEle.render();

        floatEle.bind("stateChange", function(evt, state) {
            if ("show" != state) {
                return;
            }

            var id = "__iframe_id";
            var iframe = $("#" + id);

            if (0 < iframe.length) {
                return;
            }

            $('<iframe onload="__iframe_auto_adjust(this)" frameborder="0" scrolling="no" class="floating_xiangxi"></iframe>')
                .attr("id", id)
                .attr("src", floatEle.attr("prefix") + "/person/" + floatEle.attr("personId") + "/detail")
                .appendTo("#__floatPanel");

        });
    });
};

function renderDom() {
    // render dom
    var rDom = window.renderDOMs;
    if (!rDom) {
        rDom = [];
    }

    jQuery.renderPageComponent(["#__collectTab"].concat(rDom));

    // bind tab actived
    $("#__collectTab").bind("actived", function(evt, tabId) {
        if (window.selectedNodeInx == tabId) {
            return;
        }

        // show 加载
        var tabDOM = $("#tab-" + tabId);
        tabDOM.height("600").mask("正在加载中....");


        $("<form></form>")
           .attr("method", "get")
           .attr(
               "action",
                   window.collectURL
                       .replace("#n#", tabDOM.attr("nodeID"))
                       .replace("#p#", window.personID)
               ).appendTo("body")
               .submit();
     }); // end bind
}

function renderJS(readyFunc) {
     // render js
     var jsDom = window.jsDOMs;
     if (jsDom && 0 < jsDom.length) {
        var scriptCnt = 0;
        var callback = function() {
            if ((++scriptCnt) >= jsDom.length && readyFunc) {
                readyFunc();
            }
        };

        for (var i in jsDom) {
            jQuery.ajax({
                "url": window.jsPrefix + jsDom[i],
                "dataType": "script",
                "cache": true,
                "success":  callback,
                "error": function() {
                    top.Dialog.alert("页面加载异常，请重新刷新, check " + jsDom[i]);
                }
            });
        }

     } else if(readyFunc) {
        // just call callback
        readyFunc();
     }
}


/**
* <p>采集主页面js</p>
* bind tab事件
* 渲染页面
* 根据变量引用js
* @author cl
* @since 2014/11/27
*/
﻿﻿function initComplete(){
    renderDom();
    renderJS(function() {
        // callback
        if (window.pageReady) {
            window.pageReady();
        }
    });

    renderPersonDetailPanel();
    
}

