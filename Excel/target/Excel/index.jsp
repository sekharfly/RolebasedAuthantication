
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload files</title>
</head>
<body>

	<h2>Upload Files</h2>

	<form method="post"	action="UploadToDBServlet">
			<input type="file" name="name">
			<input type="file" name="name1">
			dis:<input type="text" name="name2">
			 <input type="submit" value="Upload">
	 </form>
</body>
</html>