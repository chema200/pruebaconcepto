package org.intricom.controller;

import org.intricom.model.Hotel;
import org.intricom.service.HotelService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hotel")
public class HotelServlet extends HttpServlet {

    @Inject
    private HotelService hotelService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            List<Hotel> hotels = hotelService.findAll();
            request.setAttribute("hotels", hotels);
            request.getRequestDispatcher("/vistas/hotel/hotel.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Hotel hotel = hotelService.findById(id);
            request.setAttribute("hotel", hotel);
            request.getRequestDispatcher("/vistas/hotel/editHotel.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            hotelService.delete(id);
            response.sendRedirect("hotel?action=list");
        } else {
            response.sendRedirect("hotel?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            Hotel hotel = new Hotel();
            hotel.setName(request.getParameter("name"));
            hotel.setAddress(request.getParameter("address"));
            hotelService.create(hotel);
        } else if ("update".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Hotel hotel = hotelService.findById(id);
            hotel.setName(request.getParameter("name"));
            hotel.setAddress(request.getParameter("address"));
            hotelService.update(hotel);
        }
        response.sendRedirect("hotel?action=list");
    }
}

