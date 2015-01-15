function __iframe_auto_adjust(win) {
    if (win && !window.opera) {
        if (win.contentDocument && win.contentDocument.body.offsetHeight) {
            win.height = win.contentDocument.body.offsetHeight;
        } else if(win.Document && win.Document.body.scrollHeight) {
            win.height = win.Document.body.scrollHeight;
        }
    }
};

(function($) {
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

            $('<iframe onload="__iframe_auto_adjust(this)" id="__panel_win" name="win" frameborder="0" scrolling="no"  class="floating_xiangxi"></iframe>')
                .attr("id", id)
                .attr("src", floatEle.attr("prefix") + "/person/" + floatEle.attr("personId") + "/detail")
                .appendTo("#__floatPanel");

        });
    });
})(jQuery);
