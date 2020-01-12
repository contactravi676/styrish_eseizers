<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<s:include value="/header.jsp"></s:include>

 
Welcome, <s:property value="role"></s:property>
<c:set var="role" value="${role}"></c:set>

<c:choose>
  <c:when test="${role=='Teacher'}">
  
  
         <s:include value="/AddressAdd.jsp"></s:include>
  </c:when>
  <c:otherwise>
        Welcome, <s:property value="firstName"></s:property>. You have registered successfully for course


<s:property value="#session.userName"/> 
<s:property value="#session.user_type"/> 
  </c:otherwise>
</c:choose>

<s:property value="#session.userName"/> 
<s:property value="#session.user_type"/> 
  <s:include value="/footer.jsp"></s:include>