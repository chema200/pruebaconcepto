package org.intricom.service;

import org.intricom.dao.ClientDAO;
import org.intricom.model.Client;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ClientService {

    @Inject
    private ClientDAO clientDAO;

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public Client findById(Long id) {
        return clientDAO.findById(id);
    }

    public void create(Client client) {
        clientDAO.save(client);
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void delete(Long id) {
        clientDAO.delete(id);
    }
}
