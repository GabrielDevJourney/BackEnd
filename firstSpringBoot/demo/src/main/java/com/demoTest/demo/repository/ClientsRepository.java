package com.demoTest.demo.repository;

import com.demoTest.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client,Integer> {
}
