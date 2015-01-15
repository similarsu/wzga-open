// qui entry
function initComplete(){
    jQuery.renderPageComponent([
        "#checkBox",
        "#table1",
        "#table2",
        "#table3",
        "#table4"
    ]);

    jbhc(soaPrefix, idNo,yh);

    $("#commitBtn").click(saveWithCheck);

}

function saveWithCheck(){
    /*
        if no info:
            显示是否跳到手动录入
        if unfinished check:
            pop

    */
    if (!hasInfo) {
        top.Dialog.confirm("查询不到此人基本信息，是否跳转到手动录入？",
            function(){
                window.location.href = noInfo2URL + (idNo ? "?identifyCode=" + idNo : "");
            },
            function(){});
        return false;
    }

    var checkProcess=true;
    $.each(arr_check,function(i,n){
        if(n==false){
            checkProcess=false;
            return;
        }
    });
    if(checkProcess){
        save();
    }else{
        top.Dialog.confirm("核查未完成，是否进入采集？",function(){save();},function(){});
    }
}

function save(){
    var queryInfo=generateQueryInfo();
    $("#queryInfo").val(queryInfo);
    $("#personInfoForm").submit();
}

//拼凑json
function generateQueryInfo(){
    var queryInfo='{';
    queryInfo+='"sftf":"'+$("#sftfHidden").val()+'",';
    queryInfo+='"sffazt":"'+$("#sffaztHidden").val()+'",';
    queryInfo+='"sfbkdx":"'+$("#sfbkdxHidden").val()+'",';
    queryInfo+='"sfzwbzwga":"'+$("#sfzwbzwgaHidden").val()+'",';
    queryInfo+='"sfdnabzwga":"'+$("#sfdnabzwgaHidden").val()+'",';
    queryInfo+='"sfdfkry":"'+$("#sfdfkryHidden").val()+'",';
    queryInfo+='"sfqgwffz":"'+$("#sfqgwffzHidden").val()+'",';
    queryInfo+='"sfzwbzyga":"'+$("#sfzwbzygaHidden").val()+'",';
    queryInfo+='"sfdnabzyga":"'+$("#sfdnabzygaHidden").val()+'",';
    queryInfo+='"sfsdxx":"'+$("#sfsdxxHidden").val()+'",';
    queryInfo+='"fs":"'+$("#fsHidden").val()+'",';
    queryInfo+='"gs":"'+$("#gsHidden").val()+'",';
    queryInfo+='"clcs":"'+$("#clcsHidden").val()+'",';
    queryInfo+='"ys":"'+$("#ysHidden").val()+'"';
    queryInfo+='}';
    return queryInfo;
}
