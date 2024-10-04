<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Crear Reserva de Hotel</title>
</head>
<body>
    <h1>Crear Nueva Reserva</h1>
    <form action="${pageContext.request.contextPath}/hotelbooking" method="post">
        <input type="hidden" name="action" value="create">

        <label for="client">Cliente:</label>
        <select id="client" name="clientId" required>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}">${client.name}</option>
            </c:forEach>
        </select><br><br>

        <label for="hotel">Hotel:</label>
        <select id="hotel" name="hotelId" required>
            <c:forEach var="hotel" items="${hotels}">
                <option value="${hotel.id}">${hotel.name}</option>
            </c:forEach>
        </select><br><br>

        <!-- Agregar campo de nombre -->
        <label for="name">Nombre de la Reserva:</label>
        <input type="text" id="name" name="name" required><br><br>

        <!-- Agregar campo de dirección -->
        <label for="address">Dirección:</label>
        <input type="text" id="address" name="address" required><br><br>

        <button type="submit">Crear</button>
    </form>

        <form action="${pageContext.request.contextPath}/hotelbooking" method="get">
            <input type="hidden" name="action" value="list">
            <button type="submit">Volver</button>
        </form>
</body>
</html>
