<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="js/jquery-1.6.1.min.js"> </script>
	<script type="text/javascript">
		function adduser(){
			location.href="${pageContext.request.contextPath}/addUser"
		}
		function deleteuser(id){
			location.href="${pageContext.request.contextPath}/delete?id="+id;
		}
		function updateuser(id){
			location.href="${pageContext.request.contextPath}/findUserById?id="+id;
		}
		function searchuser(){
			var id=$("#sid").val();
			location.href="${pageContext.request.contextPath}/search?id="+id;
		}
	</script>
	</head>
	<body>
		<font color="#FF0000"><h1>首页</h1></font>
		<font color="#4B0082"><h4>用户信息</h4></font>
	<!-- 	<input type="button" value="导入" >
		<input type="button" value="导出" > -->
		<br>
		<input type="button" value="查询" onclick="searchuser()">
		<input id="sid" type="text" />
		<table border="1">
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>住址</th>
				<th>生日</th>
				<th>邮箱</th>
				<th>手机</th>
				<th>备注</th>
				<th colspan="2">操作</th>
			</tr>
			<c:forEach items="${userlist}" var="user">  
			    <tr>  
			     	<!-- <td>${user.id}</td> -->
					<td>${user.name}</td>
					<td>${user.sex}</td>
					<td>${user.age}</td>
					<td>${user.address}</td>
					<td> <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></td>
					<td>${user.email}</td>
					<td>${user.mobile}</td>
					<td>${user.remark}</td>
					<td><input type="button" value="修改"  onclick="updateuser(${user.id})"></td>
					<td><input type="button" value="删除" onclick="deleteuser(${user.id})"></td>
			    </tr>  
			</c:forEach> 
		</table>
		<input type="button" value="新增" onclick="adduser()">
	</body>
</html>