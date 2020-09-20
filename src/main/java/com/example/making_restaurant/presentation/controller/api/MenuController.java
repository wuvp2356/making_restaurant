package com.example.making_restaurant.presentation.controller.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.making_restaurant.infrastructure.MenuRepository;
import com.example.making_restaurant.infrastructure.Menu;


@RestController
@RequestMapping("api/menu")
public class MenuController {
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * 登録されているメニューを全て返す
     */
    @GetMapping
    public Iterable<Menu> findAll() {
        return menuRepository.findAll();
    }

    @GetMapping("post")
    public Menu saveMenu() {
        Menu menu = new Menu();
        menu.setId(UUID.randomUUID());
        menu.setName("新メニュー");
        menu.setPrice(100);
        return menuRepository.save(menu);
    }
    
}