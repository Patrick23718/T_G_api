package com.paulina.tg.repositories;

import com.paulina.tg.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, String> {
    Page<Client> findByFullNameContainingIgnoreCase(String name, Pageable pageable);
}
