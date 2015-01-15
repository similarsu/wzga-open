function renderDOM() {
    jQuery.renderPageComponent([
        "#box",
        "#table1",
        "#table1 tr td input",
        "#table1 tr td select",
        "#table1 tr td[title]"
    ]);

    renderList("listTable");
}

function renderList(tableID) {
    $("#" + tableID).quiGrid({
        columns: [
            { display: '姓名', name: 'name', align: 'center', width: 80},
            { display: '身份证号', name: 'identifyCode', align: 'center', width:160},
            { display: '现住地址', name: 'presentAddr', align: 'center', width:"600"}
        ],

        url: dataUrl,
        method: "post",
        pageSize: 20,
        rownumbers: true,
        height: '100%',
        width:"100%",
        params: window.dataParam,
        pageParmName: "pageNo",
        pagesizeParmName: "pageSize",
        enabledSort: false,
        onSelectRow: function(dataObj) {
            top.Dialog.open({
                URL: detailURL.replace("#pID#", dataObj["id"]),
                Title:"详情",
                Width:300,
                Height:800
            });
        }
    });
}

// main
function pageReady() {
    renderDOM();
}
