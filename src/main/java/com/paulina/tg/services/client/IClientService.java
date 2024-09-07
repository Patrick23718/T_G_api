package com.paulina.tg.services.client;

import com.paulina.tg.models.Client;
import com.paulina.tg.request.ClientRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {
    Client getClientById(String id);
    List<Client> getAllClients(String name, Pageable pageable);
    Client createClient(ClientRequest client);
    Client updateClient(ClientRequest client, String id);
    void deleteClient(String id);

}
