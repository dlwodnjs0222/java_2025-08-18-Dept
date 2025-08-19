<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			table, tr, td {
				border: 1px solid #ccc;
				border-collapse: collapse;
			}
			
			tr, td {padding: 10px 20px;}
		</style>
	</head>
	<body>
		<form action="insert" method="post">
		<input type="hidden" name="isUpdate" value="yes">
			<table>
				<tr>
					<td>deptno: <input type="text" name="deptno" value="${dto.deptno }"></td>
					<td>dnaem: <input type="text" name="dname" value="${dto.dname }"></td>
					<td>loc: <input type="text" name="loc" value="${dto.loc }"></td>
					<td>입력/수정: <input type="submit"></td>
				</tr>
			</table>
		</form>
		<br>
		<table>
			<c:forEach var="dto" items="${list }">
				<tr>
					<td><a href="updateForm?deptno=${dto.deptno }">${dto.deptno }</a></td>
					<td>${dto.dname }</td>
					<td>${dto.loc }</td>
					<td><button onclick="location.href='delete?deptno=${dto.deptno}'">삭제</button> </td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>