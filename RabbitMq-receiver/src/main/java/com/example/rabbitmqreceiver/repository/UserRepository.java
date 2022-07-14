package com.example.rabbitmqreceiver.repository;

import com.example.rabbitmqreceiver.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
}
