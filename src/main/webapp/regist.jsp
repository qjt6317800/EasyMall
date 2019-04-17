<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>欢迎注册EasyMall</title>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/regist.css"/>

	<!-- 引入jQuery库 -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.4.2.js"></script>

	<script>
        /* 文档就绪事件函数 */
        $(function(){
            /* 点击图片换一张验证码 */
            $("#img").click(function(){
                $(this).attr("src", "<%= request.getContextPath() %>/web/ValiImageServlet?time="+new Date().getTime());
            });

            /* 给所有输入框添加失去输入焦点事件, 当失去输入焦点时检查
            输入框是否为空或者两次密码是否一致或者邮箱格式是否正确 */
            //用户名
            $("input[name='username']").blur(function(){
                if(!formObj.checkNull("username", "用户名不能为空!")){
                    return;
                }

                //如果用户名不为空, 使用ajax检查用户名是否存在
                ajaxCheckName();


            });
            //密码
            $("input[name='password']").blur(function(){
                formObj.checkNull("password", "密码不能为空!");
                formObj.checkPassword("password", "两次密码不一致!");
            });
            //确认密码
            $("input[name='password2']").blur(function(){
                formObj.checkNull("password2", "确认密码不能为空!");
                formObj.checkPassword("password", "两次密码不一致!");
            });
            //昵称
            $("input[name='nickname']").blur(function(){
                formObj.checkNull("nickname", "昵称不能为空!");
            });
            //邮箱
            $("input[name='email']").blur(function(){
                formObj.checkNull("email", "邮箱不能为空!");
                formObj.checkEmail("email", "邮箱格式不正确!");
            });
            //验证码
            $("input[name='valistr']").blur(function(){
                if(!formObj.checkNull("valistr", "验证码不能为空!")){
                    //如果验证码不为空,检查验证码
					return;
				}
                ajaxCheckValistr();

            });

        })

		/* Ajax校验用户名是否重复 */
		function ajaxCheckName(){
            /* 转码解决中文乱码 */
            var username = encodeURI($("input[name='username']").val());
            /*
            $("#username_msg").load(url, data, fn);
                url: 所访问资源的url地址 "/day12/web/AjaxCheckUsernameServlet"
                data: {"username" : username[,"xxx": "xxx"]}
                fn: 函数, 该函数在响应成功之后会执行
                load函数会将服务器响应的结果(如: "用户名已存在"), 放在前面选择器选中的元素内部
            */
            /* 使用load函数实现ajax */
            //$("#username_msg").load("<%= request.getContextPath() %>/web/AjaxCheckUsernameServlet",
            //	{"username":username});


            /* 使用GET或POST方法实现ajax */
            $.post("<%= request.getContextPath() %>/web/AjaxCheckUsernameServlet", {"username": username}, function(result){
                //处理响应结果
                if(result=="1"){
                    $("#username_msg").html("用户名已存在!");
                    return false;
                }
                return true;

            });
		}
        /* Ajax校验验证码是否正确 */
        function ajaxCheckValistr(){
            var username = encodeURI($("input[name='username']").val());
            var valistr = encodeURI($("input[name='valistr']").val());
            $.post("<%= request.getContextPath() %>/web/AjaxCheckUsernameServlet", {"username": username,"valistr": valistr}, function(result){
                if(result=="2"){
                    $("#valistr_msg").html("验证码错误!");
                    return false;
                }
                return true;

            });
        }
        /* 使用ajax验证表单*/
        function ajaxCheckForm(){
            var username = encodeURI($("input[name='username']").val());
            var valistr = encodeURI($("input[name='valistr']").val());
            $.post("<%= request.getContextPath() %>/web/AjaxCheckUsernameServlet", {"username": username,"valistr": valistr}, function(result){
                //处理响应结果
                if(result=="1"){
                    $("#username_msg").html("用户名已存在!");
                    return;
                }if(result=="2"){
                    $("#valistr_msg").html("验证码错误!");
                    return;
                }
                if(formObj.checkForm()==false){
                    return;
                }
                $("form").submit();

            });
        }

        /* 注册表单的js校验 */
        var formObj = {
            "checkForm" : function(){
                //1.非空校验
                var res1 = this.checkNull("username", "用户名不能为空!");
                var res2 = this.checkNull("password", "密码不能为空!");
                var res3 = this.checkNull("password2", "确认密码不能为空!");
                var res4 = this.checkNull("nickname", "昵称不能为空!");
                var res5 = this.checkNull("email", "邮箱不能为空!");
                var res6 = this.checkNull("valistr", "验证码不能为空!");

                //2.两次密码是否一致校验
                var res7 = this.checkPassword("password", "两次密码不一致!");

                //3.邮箱格式校验
                var res8 = this.checkEmail("email", "邮箱格式不正确!");

                return res1&&res2&&res3&&res4&&res5&&res6&&res7&&res8;
            },
            "checkNull" : function(name, msg){
                var value = $("input[name='"+name+"']").val();

                //清空之前的提示消息
                // if(this.val() == "用户名已存在!"){
                //     return false;
                // }
				this.setMsg(name, "");

                if(value.trim() == ""){
                    this.setMsg(name, msg);
                    return false;
                }
                return true;
            },
            /* 设置错误提示消息 */
            "setMsg" : function(name, msg){
                //设置提示消息
                var $span = $("input[name='"+name+"']").nextAll("span");
                $span.html(msg);
                $span.css("color", "red");
            },
            /* 检查两次密码是否一致 */
            "checkPassword" : function(name, msg){
                var psw1 = $("input[name='"+name+"']").val();
                var psw2 =  $("input[name='"+name+"2']").val();

                if(psw1 != "" && psw2 != ""){
                    //清空之前的提示消息
                    this.setMsg(name+"2", "");
                    if(psw1 != psw2){
                        //设置提示消息
                        this.setMsg(name+"2", msg);
                        return false;
                    }
                }
                return true;
            },
            /* 检查邮箱格式是否正确 */
            "checkEmail" : function(name, msg){
                var email = $("input[name='"+name+"']").val();
                var regExp = /^\w+@\w+(\.\w+)+$/;

                if(email != ""){
                    if(!regExp.test(email)){
                        this.setMsg(name, msg);
                        return false;
                    }
                }
            }

        }
	</script>
</head>
<body>
<!-- onsubmit事件在表单提交时触发, 该事件会根据返回值决定
    是否提交表单, 如果onsubmit="return true"会继续提交表单
    如果onsubmit="return fasle"表单将不会提交!

    onsubmit=""引号中报错并不是因为代码有问题, 而是myeclipse
    工具在检查语法认为这个代码有问题, 其实没有错误!!
 -->

<form onsubmit="return false" action="<%= request.getContextPath() %>/web/RegistServlet" method="POST">
	<h1>欢迎注册EasyMall</h1>
	<table>
		<tr>
			<td colspan="2" style="text-align:center;color:red">
				<%= request.getAttribute("msg") == null ? "" : request.getAttribute("msg")  %>
			</td>
		</tr>
		<tr>
			<td class="tds">用户名：</td>
			<td>
				<input type="text" name="username"
					   value="<%= request.getParameter("username") == null? "" : request.getParameter("username") %>"/>
				<span id="username_msg">${ username_msg }</span>
			</td>
		</tr>
		<tr>
			<td class="tds">密码：</td>
			<td>
				<input type="password" name="password"
					   value="<%= request.getParameter("password") == null? "" : request.getParameter("password") %>"
				/>
				<span></span>
			</td>
		</tr>
		<tr>
			<td class="tds">确认密码：</td>
			<td>
				<input type="password" name="password2"
					   value="<%= request.getParameter("password2") == null? "" : request.getParameter("password2") %>"
				/>
				<span></span>
			</td>
		</tr>
		<tr>
			<td class="tds">昵称：</td>
			<td>
				<input type="text" name="nickname"
					   value="<%= request.getParameter("nickname") == null? "" : request.getParameter("nickname") %>"
				/>
				<span></span>
			</td>
		</tr>
		<tr>
			<td class="tds">邮箱：</td>
			<td>
				<input type="text" name="email"
					   value="<%= request.getParameter("email") == null? "" : request.getParameter("valistr_msg") %>"
				/>
				<span></span>
			</td>
		</tr>
		<tr>
			<td class="tds">验证码：</td>
			<td>
				<input type="text" name="valistr"
					   value="<%= request.getParameter("valistr") == null? "" : request.getParameter("valistr") %>"
				/>
				<img id="img" src="<%= request.getContextPath() %>/web/ValiImageServlet" alt="" />
				<span id="valistr_msg"> ${ valistr_msg }</span>
			</td>
		</tr>
		<tr>
			<td class="sub_td" colspan="2" class="tds">
				<input type="submit" value="注册用户" onclick="ajaxCheckForm()"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>

