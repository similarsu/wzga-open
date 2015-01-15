function pageReady(){
    jQuery.renderPageComponent([
        "#queryBox",
        "#queryTable",
        "#queryTable tr td input[type='text']",
        "#queryBtn",

        "#listTable",
        "#listTable tr td[class='textSlice']",
        "#pageContent"
    ]);

    $("#listTable tr td[class='textSliceEmpty']").each(function() {
        var title = $(this).attr("title");
        if (title && 0 < title.length) {
            addTooltip(this);
        }
    });

    bindOpBtn();
}

function customHeightSet(contentHeight){
    $("#scrollContent").height(contentHeight-fixObjHeight);
}

function bindOpBtn() {
    $("#importBtn").click(function() {
        top.Dialog.open({
            URL: $(this).attr("url"),
            Title:"操作页面",
            Width:600,
            Height:300
        });
    });
}
