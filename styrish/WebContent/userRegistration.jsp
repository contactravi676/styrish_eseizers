<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>styrish pvt ltd</title>
</head>
 <body>

        <s:include value="/header.jsp"></s:include>
        
  
<s:form action="userRegistration">


<!-- - --Select Course : <select name="course">
<s:iterator value="#courseMap" var="courseMapEntry">

<option value="<s:property value='#courseMapEntry.key'/>">
                <s:property value='#courseMapEntry.value' />
            </option>

</s:iterator>
</select>
-->
            <s:textfield name="firstName" label=" Name" />
            <s:textfield name="mobile" label="Mobile" />
            <s:textfield name="email" label="Email Id" />
           <s:password name="password" label="Password"></s:password>  
            <s:password name="confirmPassword" label="Confirm Password"></s:password>  
           <s:checkboxlist list="{'Student','Teacher'}" name="role"></s:checkboxlist>
           
            <s:submit />
        </s:form>  
         <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>