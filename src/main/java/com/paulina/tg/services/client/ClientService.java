package com.paulina.tg.services.client;

import com.paulina.tg.exceptions.ResourceNotFoundException;
import com.paulina.tg.models.Client;
import com.paulina.tg.repositories.ClientRepository;
import com.paulina.tg.request.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client getClientById(String id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
    }

    @Override
    public List<Client> getAllClients(String name, Pageable pageable) {
        return clientRepository.findByFullNameContainingIgnoreCase(name, pageable).getContent();
    }

    @Override
    public Client createClient(ClientRequest client) {
        return null;
    }

    @Override
    public Client updateClient(ClientRequest client) {
        return null;
    }

    @Override
    public void deleteClient(String id) {

    }
}
