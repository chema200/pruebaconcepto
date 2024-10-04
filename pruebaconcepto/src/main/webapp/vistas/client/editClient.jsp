<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Cliente</title>
</head>
<body>
    <h1>Editar Cliente</h1>
    <form action="${pageContext.request.contextPath}/client" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${client.id}"> <!-- Campo oculto con el ID del cliente -->

        <label for="name">Nombre:</label>
        <input type="text" id="name" name="name" value="${client.name}" required><br><br>

        <label for="address">Dirección:</label>
        <input type="text" id="address" name="address" value="${client.address}" required><br><br>

        <label for="phone">Teléfono:</label>
        <input type="text" id="phone" name="phone" value="${client.phone}" required><br><br>

        <button type="submit">Actualizar</button>
    </form>


    <form action="${pageContext.request.contextPath}/client" method="get">
        <input type="hidden" name="action" value="list">
        <button type="submit">Volver</button>
    </form>
</body>
</html>