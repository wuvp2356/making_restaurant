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

    // 本当はここはPostMappingを使うべきなんだけど、Postするのめんどくさい
    // そう。HTTP通信の規格で決められていて、取得がGET, 登録がPOST, 更新がPUT, 削除がDELETE
    // になってる。
    // 一応そういう役割だからそれに則ってやるのがベスト。
    // 実際強制されてるわけじゃないから↓みたいに中身は登録とかやっても、一応は動く。
    // @PostMapping // 普通はこっちを使う
    // いま http://localhost:8080/api/menu/post (menuが抜けてた。。)ってやると、
    // 「新メニュー」っていうメニューがどんどん登録されていく
    // で、こういう感じにcontrollerで処理を書くのはfat controllerっていうダメな書き方
    @GetMapping("post")
    public Menu saveMenu() {
        Menu menu = new Menu();
        menu.setId(UUID.randomUUID());
        menu.setName("新メニュー");
        menu.setPrice(100);
        return menuRepository.save(menu);
    }
    // 一応これで動くようにはなってるはず
}
