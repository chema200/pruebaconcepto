package org.intricom.controller;

import org.intricom.model.Client;
import org.intricom.service.ClientService;
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

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ClientServlet.class);

    @Inject
    private ClientService clientService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            List<Client> clients = clientService.findAll();
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/vistas/client/client.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Client client = clientService.findById(id);
            logger.info("cliente: "+client);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/vistas/client/editClient.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            clientService.delete(id);
            response.sendRedirect("client?action=list");
        } else {
            response.sendRedirect("client?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            Client client = new Client();
            client.setName(request.getParameter("name"));
            client.setAddress(request.getParameter("address"));
            client.setPhone(request.getParameter("phone"));
            clientService.create(client);
        } else if ("update".equals(action)) {
            Long id = Long.parseLong(request.getParameter("id"));
            Client client = clientService.findById(id);
            client.setName(request.getParameter("name"));
            client.setAddress(request.getParameter("address"));
            client.setPhone(request.getParameter("phone"));
            clientService.update(client);
        }
        response.sendRedirect("client?action=list");
    }
}

