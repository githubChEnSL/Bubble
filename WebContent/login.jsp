<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登陆</title>
<link href="css/bootstrap/bootstrap.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<%
	String username = "";
	String password = "";
	Cookie cookies[] = request.getCookies();
	if (cookies != null) {
		for (int i = 0; i < cookies.length; i++) {
			if ("username".equals(cookies[i].getName())) {
				username = cookies[i].getValue();
			}
			if ("password".equals(cookies[i].getName())) {
				password = cookies[i].getValue();
			}
		}
	}
%>
<body>
	<div class="login_div">
		<div class="title">店铺管理后台</div>
		<form action="LoginServlet" method="post">
			<h2>用户登录</h2>
			<div class="msg">
				<p style="color: red;">${sessionScope.LoginMsg}</p>
			</div>
			<div class="form-group">
				<input class="form form-control" type="text" placeholder="用户名"
					name="userName" value="<%=username%>" maxlength="20" />
			</div>
			<div class="form-group">
				<input class="form form-control" type="password" placeholder="密码"
					name="password" value="<%=password%>" maxlength="16" />
			</div>
			<div class="form-group">
				<div class="msg">
					<p style="color: red;">${sessionScope.error}</p>
				</div>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-info col-sm-12" value="登录 " />
			</div>
		</form>
	</div>
</body>
</html>