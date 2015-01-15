function initNodeType(id) {
    var codeData = [
            {"key": "请选择", "value": ""},
            {"key": "基本信息采集", "value": "info"},
            {"key": "人像信息采集", "value": "photo"},
            {"key": "指纹信息采集", "value": "finger"},
            {"key": "DNA信息采集", "value": "dna"},
            {"key": "虚拟身份采集", "value": "netprop"},
            {"key": "体貌特征采集", "value": "traits"},
            {"key": "随身物品采集", "value": "belongings"},
            {"key": "多维采集", "value": "nDCollect"}
    ];
    $("#" + id).data("data", codeData).render();
}
