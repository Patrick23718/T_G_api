package com.paulina.tg.services.client;

import com.paulina.tg.exceptions.ResourceNotFoundException;
import com.paulina.tg.models.Client;
import com.paulina.tg.repositories.ClientRepository;
import com.paulina.tg.request.ClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Client createClient(ClientRequest clientRequest) {
        Client client = Client.builder()
                .fullName(clientRequest.getFullName())
                .email(clientRequest.getEmail())
                .phone(clientRequest.getPhone())
                .address(clientRequest.getAddress())
                .city(clientRequest.getCity())
                .country(clientRequest.getCountry())
                .build();
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(ClientRequest client, String id) {
        return Optional.ofNullable(getClientById(id))
                .map(oldProduct -> {
                    oldProduct.setFullName(client.getFullName());
                    oldProduct.setEmail(client.getEmail());
                    oldProduct.setPhone(client.getPhone());
                    oldProduct.setAddress(client.getAddress());
                    oldProduct.setCity(client.getCity());
                    oldProduct.setCountry(client.getCountry());
                    return clientRepository.save(oldProduct);
                })
                .orElseThrow(()-> new ResourceNotFoundException("Client not found!"));
    }

    @Override
    public void deleteClient(String id) {
        Client client = getClientById(id);
        if (client.getOrders().isEmpty()) {
            //TODO: Client have order exception
            return;
        }
        clientRepository.delete(client);
    }
}
