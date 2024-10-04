<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Hoteles</title>
</head>
<body>
    <h1>Hoteles</h1>
    <a href="vistas/hotel/createHotel.jsp">Agregar Hotel</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Dirección</th>
            <th>Fecha de Creación</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="hotel" items="${hotels}">
            <tr>
                <td>${hotel.id}</td>
                <td>${hotel.name}</td>
                <td>${hotel.address}</td>
                <td>${hotel.createdDate}</td>
                <td>
                    <a href="hotel?action=edit&id=${hotel.id}">Editar</a>
                    <a href="hotel?action=delete&id=${hotel.id}"
                      onclick="return confirm('¿Estás seguro de que deseas eliminar este hotel? También se eliminarán las reservas asociadas a dicho hotel.');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="${pageContext.request.contextPath}/index.jsp" method="get">
            <button type="submit">Volver a Inicio</button>
        </form>
</body>
</html>
