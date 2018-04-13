<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>修改用户</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/update" method="post">
		<input type="hidden" name="id" value="${user.id}"/>
    	<table border="1">
    		<tr>
    			<td>姓名</td>
    			<td><input type="text" name="name" value="${user.name}"/></td>
    		</tr>
    		<tr>
    			<td>性别</td>
    			<td><input type="text" name="sex" value="${user.sex}"></input></td>
    		</tr>
    		<tr>
    			<td>年龄</td>
    			<td><input type="text" name="age" value="${user.age}"/></td>
    		</tr>
    		<tr>
    			<td>地址</td>
    			<td><input type="text" name="address" value="${user.address}"/></td>
    		</tr>
    		<tr>
    			<td>生日</td>
    			<td><input type="text" name="birthday" value="<fmt:formatDate value='${user.birthday}' pattern='yyyy-MM-dd'/>" ></td>
    		</tr>
    		<tr>
    			<td>邮箱</td>
    			<td><input  type="text" name="email" value="${user.email}"/></td>
    		</tr>
    		<tr>
    			<td>手机号码</td>
    			<td><input type="text" name="mobile" value="${user.mobile}"/></td>
    		</tr>
    		<tr>
    			<td>备注</td>
    			<td><textarea name="remark">${user.remark}</textarea></td>
    		</tr>
    		<tr>
    			<td colspan=2 align="center"><input type="submit" value="确认修改"/></td>
    		</tr>
    		<tr>
    		</tr>
    	</table>
    </form>
</body>
</html>