layui.use(['laypage', 'layer'], function () {
    var laypage = layui.laypage,
        layer = layui.layer;

    //总页数大于页码总数
    laypage.render({
        elem: 'pages'
        , count: 10
        , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
        , jump: function (obj) {
            console.log(obj)
        }
    });
});

function showStatus() {
    layer.open({
        title: '用户状态审核',
        type: 1,
        shadeClose: true, //点击遮罩关闭
        area: ['400px', '300px'], //宽高
        anim: 4,                   //动画
        content: $("#qrDiv"), //内容为frameDiv内容
        offset: 'auto'//设置div位置，默认居中
    });
}

function showMessage() {
    layer.open({
        title: '理由信息',
        type: 1,
        shadeClose: true, //点击遮罩关闭
        area: ['400px', '300px'], //宽高
        anim: 4,                   //动画
        content: $("#messageDiv"), //内容为frameDiv内容
        offset: 'auto'//设置div位置，默认居中
    });
}