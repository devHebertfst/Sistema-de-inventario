package com.example.app_web.repository;

import com.example.app_web.model.Item;
import com.example.app_web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser(User user);
}
