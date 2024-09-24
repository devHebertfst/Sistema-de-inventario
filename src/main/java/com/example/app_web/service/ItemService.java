package com.example.app_web.service;

import com.example.app_web.model.Item;
import com.example.app_web.model.User;

import java.util.List;

public interface ItemService {
    List<Item> getItemsByUser(User user);
    Item getItemById(Long id);
    void saveItem(Item item);
    void updateItem(Item item);
    void deleteItem(Long id);
}
