<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Reservas</title>
</head>
<body>
    <h1>Reservas de Hotel</h1>
    <a href="hotelbooking?action=create">Agregar Reserva</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Hotel</th>
            <th>Fecha de Creación</th>
            <th>Nombre de la Reserva</th>
            <th>Dirección de la Reserva</th>
            <th>Acciones</th>
        </tr>
        <c:forEach var="hotelBooking" items="${hotelBookings}">
            <tr>
                <td>${hotelBooking.id}</td>
                <td>${hotelBooking.client.name}</td>
                <td>${hotelBooking.hotel.name}</td>
                <td>${hotelBooking.createdDate}</td>
                <td>${hotelBooking.name}</td>
                <td>${hotelBooking.address}</td>
                <td>
                    <a href="hotelbooking?action=edit&id=${hotelBooking.id}">Editar</a>
                    <a href="hotelbooking?action=delete&id=${hotelBooking.id}"
                       onclick="return confirm('¿Estás seguro de que deseas eliminar esta reserva?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="${pageContext.request.contextPath}/index.jsp" method="get">
            <button type="submit">Volver a Inicio</button>
        </form>
</body>
</html>
