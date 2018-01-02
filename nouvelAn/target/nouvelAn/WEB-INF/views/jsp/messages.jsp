<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des messages</title>
<s:url value="/resources/css/bonjour.css" var="coreStyle" />
<s:url value="/resources/css/bootstrap.css" var="bootstrapStyle" />
<link href="${coreStyle}" rel="stylesheet" />
<link href="${bootstrapStyle}" rel="stylesheet" />
</head>
<body>
<h2> Liste des messages</h2>
<div class="contener"> 
<div class="well">
	<form method="post" action="addMessage">
		<div class="form-group">
	 		<label for="titre"> titre du message</label>
	 		<input type ="text" class="form-control" id="titre" name="titre"/>
	 	 </div>
	 	<div class="form-group">
	 		<label for="corps"> Corps du message</label>
	 		<input type ="text" class="form-control" id="corps" name="corps"/>
	 	 </div> 
	 	 <input type="submit" class="btn btn-primary" value="ajouter"/>
	</form>
</div>
	<table border="1" class="table table-striped">
		<thead>
			<tr> <th>titre</th> <th>Corps</th></tr>
		</thead>
		<tbody>
			<c:forEach var="msg" items="${messages}">
				<tr>
					<td> ${msg.titre} </td>
					<td> ${msg.corps} </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>