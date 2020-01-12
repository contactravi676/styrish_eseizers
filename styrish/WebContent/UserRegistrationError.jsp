<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<s:include value="/header.jsp"></s:include>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
User is already exist with username :
<s:property value="email"/>
  <s:include value="/footer.jsp"></s:include>
</body>
</html>