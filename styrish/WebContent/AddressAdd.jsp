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

   <s:form action="userAddressAction">

            <s:textfield name="fullName" label=" Name" />
            <s:textfield name="address1" label="Address1" />
            <s:textfield name="address2" label="Address2" />
            <s:textfield name="city" label="City" />
            <s:textfield name="state" label="state" />
            <s:textfield name="zipcode" label="zipcode" />
            <s:textfield name="country" label="country" />
           <s:textfield name="userPhoto" label="userPhoto" />
            <s:textfield name="idProof" label="idProof" />
             <s:hidden name="userId"  value="%{userId}" />
            <s:hidden name="email"  value="%{email}" />
            <s:hidden name="firstName"  value="%{firstName}" />
                 <s:submit />
   </s:form>  
       
           
    </body>
</html>