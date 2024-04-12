<%@ page language="java" contentType="text/html; charset=windows-1256"
pageEncoding="windows-1256"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Liste des Lieu</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="header.jsp" %>
<p></p>
<div class="container">
<div class="card">
<div class="card-header">
Liste des Lieu
</div>
<div class="card-body">
<table class="table table-striped">
<tr>
<th>ID</th><th>Pays</th><th>Suppression<th>Edition</th>
</tr>
<c:forEach items="${model.lieus}" var="l">
<tr>
<td>${l.idLieu }</td>
<td>${l.pays }</td>

<td><a onclick="return confirm('Etes-vous sûr ?')"
href="supprimerLieu?id=${l.idLieu}">Supprimer</a></td>
<td><a href="editerLieu?id=${l.idLieu}">Edit</a></td>
</tr>
</c:forEach>
</table>
</div>
</div>
</div>
</body>
</html>