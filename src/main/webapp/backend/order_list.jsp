<%@ page language="java" import="java.util.*" isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>订单管理</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body {
            font-family: "微软雅黑";
            background-color: #EDEDED;
        }

        h2 {
            text-align: center;
        }

        table {
            margin: 0 auto;
            /* width: 96%; */
            text-align: center;
            border-collapse: collapse;
        }

        td, th {
            padding: 7px;
        }

        th {
            background-color: #DCDCDC;
        }

        th.ths {
            width: 100px;
        }

        input.pnum {
            width: 80px;
            height: 25px;
            font-size: 18px;
            text-align: center;
        }

        .row1 {
            background-color: green;
        }

        .row2 {
            background-color: red;
        }
    </style>

    <!--引入jquery的js库-->
    <script src="${app}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        /* 文档就绪事件函数 */
        $(function () {
            /* 修改订单收货地址 */
            $(".receiverinfo").blur(function () {
                /*
                 1.当输入框失去焦点时没有做任何修改, 不应该向服务器发送请求
                 2.当输入的数值不是合法数值时, 要提示"您输入的数值不合法"
                 3.当提示完"您输入的数值不合法"后, 将输入框的值还原上一次的值
                 */
                var _this = this;
                //获取订单的收货地址(旧的地址)
                var oldInfo = $(this).attr("oldInfo");
                //获取订单的收货地址(新的地址)
                var newInfo = $(this).val();

                //获取订单的id
                var oid = $(this).attr("id");
                //问题1:如果修改之前的地址和修改之后的地址一致, 就不做任何修改
                if (oldInfo == newInfo) {
                    return;
                }

                console.log("old: "+oldInfo);
                console.log("new: "+newInfo);

                //问题2:校验数值是否合法(必须是正整数(>=0))//0199
                // var reg = /^0$|^[1-9][0-9]*$/;
                // if (!reg.test(newInfo)) {//不是合法数值
                //     alert("您输入的数值不合法!");
                //     //如果数值不合法, 把输入框的值还原为上次输入的合法数值
                //     $(this).val(oldInfo);
                //     return;
                // }

                //使用ajax请求异步访问服务器, 修改订单收货地址
                $.post("${app}/servlet/BackReceiverInfoUpdateServlet", {"info": newInfo, "oid": oid,}, function (result) {
                    //result: true 表示修改成功, false: 修改失败
                    if ("true" == result) {
                        alert("修改成功!");
                        //修改成功后要及时更新旧的收货地址
                        $(_this).attr("ordInfo", newInfo);
                    } else {
                        alert("修改失败!");
                    }
                });

            });


            /* 删除订单
             * 给所有的删除超链接添加点击事件, 点击删除
             * 执行删除当前订单的操作!
             */
            $(".del").click(function () {
                //添加确认对话框, 确认是否真的删除订单
                if (!window.confirm("您确定要删除该订单吗?")) {
                    return;
                }

                //1.获取所要删除订单的id
                var oid = $(this).parents("tr").find(".del").attr("id");
                // alert(oid);

                var _this = this;

                //2.利用ajax异步访问服务器来删除指定id的订单
                $.post("${app}/servlet/BackOrderDelServlet", {"oid": oid}, function (result) {
                        //result:true表示删除成功
                        if ("true" == result) {
                        alert("删除成功!");
                        //同时将订单信息从页面中删除
                        $(_this).parents("tr").remove();
                    } else {
                        alert("删除失败!");
                    }
                });


            });

        });

    </script>
</head>
<body>
<h2>订单管理</h2>
<table border="1">
    <tr>
        <th>订单图片</th>
        <th width="200px">订单ID</th>
        <th class="ths">订单价格</th>
        <th class="ths">收货地址</th>
        <th class="ths">支付状态</th>
        <th class="ths">订单时间</th>
        <th class="this">用户ID</th>
        <th width="100px">操作</th>
    </tr>

    <!-- 模版数据 -->


    <%-- 通过jstl+el获取所有订单组成的list集合, 遍历显示 --%>
    <c:forEach items="${ list }" var="order" varStatus="stat">
        <tr
                <c:if test="${stat.index%2==0}">class="row1"</c:if>
                <c:if test="${stat.index%2==1}">class="row2"</c:if>
        >
            <td></td>
            <td>${ order.id }</td>
            <td>${ order.money }</td>
            <%--<td>${ order.receiverinfo}</td>--%>
            <td>
                <input type="text" id="${ order.receiverinfo }" class="receiverinfo" oldInfo="${ order.receiverinfo }" value="${ order.receiverinfo }"/>
            </td>
            <td>${ order.paystate }</td>
            <td>${ order.ordertime }</td>
            <td>${ order.user_id }</td>
            <td>
                <a class="del" id="${ order.id }" href="javascript:void(0)">删 除</a>|
                <a class="upd"  href="${app}/servlet/BackReceiverInfoUpdateServlet?oid=${order.id}&info=${newInfo}">修 改</a>
            </td>
        </tr>
    </c:forEach>


</table>
</body>
</html>
