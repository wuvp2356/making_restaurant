package com.example.making_restaurant.application.service;

import org.springframework.stereotype.Service;
import com.example.making_restaurant.infrastructure.Menu;
import com.example.making_restaurant.infrastructure.MenuRepository;

import java.util.UUID;

/**
 * メニューのサービスクラス
 * ユーザが行いたい操作（いわゆるユースケース）を記述する
 * これでSpringMVC + Thymeleafでできる基本的なことは全部
 * あとはMVCのMのところ、モデルを作るのが一番重要で大変なところ
 * M: Model
 * V: View
 * C: Controller
 * VはThymeleaf, Cはcontroller, Serviceを通してModelを動かす
 * thymeleafはhtmlにControllerから受けとったパラメータを埋め込む役割
 * よくあるダメな例として、Thymeleafの中に業務ロジックを書く、というのがある。
 * thymeleafはControllerからパラメータを受け取って、htmlに埋め込む。
 * <div th:text="${message}">この部分がmessageで置き換わるよ！</div>
 * でも一応thymeleafにもif文とかがあって、条件によって表示する・しないとかが制御できる
 * でもそれを間違って使うと、本来業務として判断するロジックをthymeleafでやってしまって、
 * 巨大なhtmlが作り上げられる。
 * そうそう、一部機能を使わないとか本当はmodel内で判定すべきことがviewでやれてしまう
 * そうなると「業務」の流れがmodel内だけじゃなく、viewも見に行かないと分からなくなるから、
 * 死ぬ。
 * あとthymeleaf見てわかる通りjavaじゃないから、参照関係で追うことも難しい。
 * どうしてもviewとmodelの形が合わない場合はdto(data transfer object)や
 * view modelを使う。
 * 業務ロジックは絶対にmodelの中に閉じ込めないといけない。
 * 障害や改修のときもそうだけど、システム移行のときもmodelがあるかどうかでかなり違う。
 * コストでいうと多分数十倍ぐらい変わる。
 */
@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * メニューを全検索する
     */
    public Iterable<Menu> findAll() {
        return menuRepository.findAll();
    }

    /**
     * メニューを新しく作る
     */
    public Menu create(String name, Integer price) {
        Menu menu = new Menu();
        menu.setId(UUID.randomUUID());
        menu.setName(name);
        menu.setPrice(price);
        return menu;
    }
}