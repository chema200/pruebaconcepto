<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Reserva de Hotel</title>
</head>
<body>
    <h1>Editar Reserva de Hotel</h1>
    <form action="${pageContext.request.contextPath}/hotelbooking" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${hotelBooking.id}">

        <label for="client">Cliente:</label>
        <select id="client" name="clientId" required>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}" <c:if test="${client.id == hotelBooking.client.id}">selected</c:if>>${client.name}</option>
            </c:forEach>
        </select><br><br>

        <label for="hotel">Hotel:</label>
        <select id="hotel" name="hotelId" required>
            <c:forEach var="hotel" items="${hotels}">
                <option value="${hotel.id}" <c:if test="${hotel.id == hotelBooking.hotel.id}">selected</c:if>>${hotel.name}</option>
            </c:forEach>
        </select><br><br>

        <label for="name">Nombre de la Reserva:</label>
        <input type="text" id="name" name="name" value="${hotelBooking.name}" required><br><br>

        <label for="address">Dirección de la Reserva:</label>
        <input type="text" id="address" name="address" value="${hotelBooking.address}" required><br><br>

        <button type="submit">Actualizar</button>
    </form>

        <form action="${pageContext.request.contextPath}/hotelbooking" method="get">
            <input type="hidden" name="action" value="list">
            <button type="submit">Volver</button>
        </form>
</body>
</html>
