<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>click below url</p>

	<form action="TwoFactorAuthExample">
		<img
			src=https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=200x200&chld=M|0&cht=qr&chl=otpauth://totp/sekharfly@gmail.com%3Fsecret%3DNY4A5CPJZ46LXZCP>
		</img> <br>
		<br> Enter otp : <input type="text" name="otp" value="otp">
		<h1><% session.getAttribute("code") %><h1>
		<input type="submit" value="submit">
	</form>
</body>
</html>