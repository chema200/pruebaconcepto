package org.intricom.controller;

import org.intricom.model.Client;
import org.intricom.model.Hotel;
import org.intricom.model.HotelBooking;
import org.intricom.service.ClientService;
import org.intricom.service.HotelBookingService;
import org.intricom.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hotelbooking")
public class HotelBookingServlet extends HttpServlet {

    @Inject
    private HotelBookingService hotelBookingService;

    @Inject
    private ClientService clientService;

    @Inject
    private HotelService hotelService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            List<HotelBooking> hotelBookings = hotelBookingService.findAll();
            request.setAttribute("hotelBookings", hotelBookings);
            request.getRequestDispatcher("/vistas/hotelbooking/booking.jsp").forward(request, response);
        } else if ("create".equals(action)) {
            List<Client> clients = clientService.findAll();
            List<Hotel> hotels = hotelService.findAll();
            request.setAttribute("clients", clients);
            request.setAttribute("hotels", hotels);
            request.getRequestDispatcher("/vistas/hotelbooking/createBooking.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            HotelBooking hotelBooking = hotelBookingService.findById(id);
            List<Client> clients = clientService.findAll();
            List<Hotel> hotels = hotelService.findAll();
            request.setAttribute("hotelBooking", hotelBooking);
            request.setAttribute("clients", clients);
            request.setAttribute("hotels", hotels);
            request.getRequestDispatcher("/vistas/hotelbooking/editBooking.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            hotelBookingService.delete(id);
            response.sendRedirect("hotelbooking?action=list");
        } else {
            response.sendRedirect("hotelbooking?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            HotelBooking hotelBooking = new HotelBooking();

            // Obtener cliente y hotel
            hotelBooking.setClient(clientService.findById(Long.parseLong(request.getParameter("clientId"))));
            hotelBooking.setHotel(hotelService.findById(Long.parseLong(request.getParameter("hotelId"))));

            // Obtener los valores de nombre y dirección
            hotelBooking.setName(request.getParameter("name"));
            hotelBooking.setAddress(request.getParameter("address"));

            // Guardar la nueva reserva
            hotelBookingService.create(hotelBooking);

        } else if ("update".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            HotelBooking hotelBooking = hotelBookingService.findById(id);

            // Actualizar cliente y hotel
            hotelBooking.setClient(clientService.findById(Long.parseLong(request.getParameter("clientId"))));
            hotelBooking.setHotel(hotelService.findById(Long.parseLong(request.getParameter("hotelId"))));

            // Actualizar nombre y dirección
            hotelBooking.setName(request.getParameter("name"));
            hotelBooking.setAddress(request.getParameter("address"));

            // Guardar los cambios en la reserva
            hotelBookingService.update(hotelBooking);
        }

        response.sendRedirect("hotelbooking?action=list");
    }
}
