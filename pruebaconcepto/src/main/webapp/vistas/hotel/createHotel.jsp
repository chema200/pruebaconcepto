<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Crear Hotel</title>
</head>
<body>
    <h1>Crear Nuevo Hotel</h1>
    <form action="${pageContext.request.contextPath}/hotel" method="post">
        <input type="hidden" name="action" value="create">
        <label for="name">Nombre:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="address">Dirección:</label>
        <input type="text" id="address" name="address" required><br><br>

        <label for="createdDate">Fecha de Creación:</label>
        <input type="text" id="createdDate" name="createdDate" value="${hotel.createdDate}" disabled><br><br>

        <button type="submit">Crear</button>
    </form>
      <form action="${pageContext.request.contextPath}/hotel" method="get">
                    <input type="hidden" name="action" value="list">
                    <button type="submit">Volver</button>
                </form>
</body>
</html>
