function one() {
    layer.open({
        type:1,
        title:'采购信息发布',
        skin:'layui-layer-rim',//是否加边框
        area:['800px','500px'],//弹出层大小
        anim:2,//动画
        content:$("#findDiv")//需要显示的div
    });
}
function two() {
    layer.open({
        type:1,
        title:'编辑审核意见',
        skin:'layui-layer-rim',//是否加边框
        area:['600px','400px'],//弹出层大小
        anim:2,//动画
        content:$("#findDiv1")//需要显示的div
    });
}