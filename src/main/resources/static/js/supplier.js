$(function () {
    var date = new Date();
    var fullYear = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    $("#examineTime").html(fullYear+"年"+month+"月"+day+"日");
})
function one() {
    procurementExamineYes();
    $(function(){
        cutPage(1);
    });
    function cutPage(pageNO){
        $.post("/supplier/biddingInfo","pageNO="+pageNO+"&supplierScope="+ztype,function(info){
            // alert(JSON.stringify(info));
            var str = "";
            for(var i=0;i<info.data.length;i++){
                var obj = info.data[i];
                console.log(obj)
                str += "<tr><td>"+obj.supplierName+
                    "</td><td>"+obj.supplierScope+
                    "</td><td>"+obj.supplierPhone+
                    "</td><td>"+obj.supplierSite+
                    "</td><td>"+obj.supplierTime+
                    "</td><td><img style='height: 60px;width: 200px' src='data:image/jpg;base64,"+obj.supplierLicansePath+"'>"+
                    "</td><td><a class='layui-btn'  onclick=\"release(\'"+obj.supplierId+"\')\">发布</a></td></tr>"
            }

            $("#data").html(str);

            if(pageNO ==1){
                layui.use('laypage', function(){
                    var laypage = layui.laypage;
                    //执行一个laypage实例
                    laypage.render({
                        elem: 'cutPageDiv1' //注意，这里的 test1 是 ID，不用加 # 号
                        ,count: info.count, //数据总数，从服务端得到
                        limit:3,  //每页显示条数
                        jump:function(obj,first){ //页码变化时触发
                            if(!first){
                                cutPage(obj.curr);
                            }
                        }
                    });
                });
            }

        },"json");
    }
    layer.open({
        type:1,
        title:'采购信息发布',
        skin:'layui-layer-rim',//是否加边框
        area:['800px','500px'],//弹出层大小
        anim:2,//动画
        content:$("#findDiv")//需要显示的div
    });
}
function release(obj) {
    // alert(obj);
    // alert(zid)
    $.post("/supplier/biddingInfoAdd","supplierId="+obj+"&procurementPlanId="+zid,function(info){
        // alert(JSON.stringify(info));
    },"json");
    layer.closeAll();
}

var zid;
var ztype;
function two(id,type) {
    zid= id;
    ztype=type;
    // alert(zid);
    // alert(type);
    layer.open({
        type:1,
        title:'编辑审核意见',
        skin:'layui-layer-rim',//是否加边框
        area:['600px','400px'],//弹出层大小
        anim:2,//动画
        content:$("#findDiv1")//需要显示的div
    });
}

function examineNo(){
    // alert(zid);
    // alert($("#supplierGrade").val());
    // alert($("#examineMan").text());
    // alert($("#examineTime").text());
    // alert($("#examineInfo").text());
    $.post("/supplier/supplierExamineNo","id="+zid+"&grade="+$("#supplierGrade").val()+
        "&examineMan="+$("#examineMan").text()+
        "&examineTime="+$("#examineTime").text()+
        "&examineInfo="+$("#examineInfo").val()
        ,function(info){
         // alert(JSON.stringify(info));
         layer.closeAll();
    });
}

function examineYes() {
    // alert(zid);
    // alert($("#supplierGrade").val());
    // alert($("#examineMan").text());
    // alert($("#examineTime").text());
    // alert($("#examineInfo").text());
    $.post("/supplier/supplierExamineYes","id="+zid+"&grade="+$("#supplierGrade").val()+
        "&examineMan="+$("#examineMan").text()+
        "&examineTime="+$("#examineTime").text()+
        "&examineInfo="+$("#examineInfo").val()
        ,function(info){
            // alert(JSON.stringify(info));
            layer.closeAll();
        });
}

function procurementExamineNo() {
    // alert(zid);
    // alert($("#supplierGrade").val());
    // alert($("#examineMan").text());
    // alert($("#examineTime").text());
    // alert($("#examineInfo").text());
    $.post("/supplier/procurementExamineNo","id="+zid+
        "&examineMan="+$("#examineMan").text()+
        "&examineTime="+$("#examineTime").text()+
        "&examineInfo="+$("#examineInfo").val()
        ,function(info){
            // alert(JSON.stringify(info));
            layer.closeAll();
        });
}

function procurementExamineYes() {
    // alert(zid);
    // alert($("#s\upplierGrade").val());
    // alert($("#examineMan").text());
    // alert($("#examineTime").text());
    // alert($("#examineInfo").text());
    $.post("/supplier/procurementExamineYes","id="+zid+
        "&examineMan="+$("#examineMan").text()+
        "&examineTime="+$("#examineTime").text()+
        "&examineInfo="+$("#examineInfo").val()
        ,function(info){
            // alert(JSON.stringify(info));

        });
}