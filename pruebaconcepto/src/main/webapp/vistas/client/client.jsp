<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>
    <h1>Clientes</h1>
    <a href="vistas/client/createClient.jsp">Agregar Cliente</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Fecha de Creación</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>${client.id}</td>
                <td>${client.name}</td>
                <td>${client.address}</td>
                <td>${client.phone}</td>
                <td>${client.createdDate}</td>
                <td>
                    <a href="client?action=edit&id=${client.id}">Editar</a>
                    <a href="client?action=delete&id=${client.id}"
                     onclick="return confirm('¿Estás seguro de que deseas eliminar este cliente? También se eliminarán las reservas asociadas a dicho cliente.');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="${pageContext.request.contextPath}/index.jsp" method="get">
            <button type="submit">Volver a Inicio</button>
        </form>
</body>
</html>
