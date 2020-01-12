<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>Upload Successful</title>
<s:head />
</head>
<body>

 <h3>File Uploaded successfully</h3>
 File Name :
 <s:property value="documentFileName"></s:property>
 <br /> Content type:
 <s:property value="documentContentType"></s:property>
 <br /> User file :
 <s:property value="document"></s:property>
</body>
</html>