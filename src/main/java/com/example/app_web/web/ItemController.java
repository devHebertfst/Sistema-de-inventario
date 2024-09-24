package com.example.app_web.web;


import com.example.app_web.model.Item;
import com.example.app_web.model.User;
import com.example.app_web.repository.UserRepository;
import com.example.app_web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final UserRepository userRepository;

    @Autowired
    public ItemController(ItemService itemService, UserRepository userRepository) {
        this.itemService = itemService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listItems(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        List<Item> items = itemService.getItemsByUser(user);
        model.addAttribute("items", items);
        return "items/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/new";
    }

    @PostMapping
    public String createItem(@ModelAttribute("item") Item item) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        item.setUser(user);
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        return "items/edit";
    }

    @PostMapping("/update/{id}")
    public String updateItem(@PathVariable("id") Long id, @ModelAttribute("item") Item item) {
        Item existingItem = itemService.getItemById(id);
        existingItem.setDescription(item.getDescription());
        existingItem.setQuantity(item.getQuantity());
        itemService.updateItem(existingItem);
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return "redirect:/items";
    }
}
