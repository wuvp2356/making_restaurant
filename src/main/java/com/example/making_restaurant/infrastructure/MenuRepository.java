package com.example.making_restaurant.infrastructure;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * Menuのリポジトリインタフェース
 * 継承しているCrudRepositoryが既にMenuの登録、参照、更新、削除を勝手に作ってくれる。
 * このリポジトリを使ってDBへのアクセスを行う。
 * 実際のWebサービスとしての定義はサービス層（application packageの中）で実装する。
 * けど今回はまあ、そんな複雑なことはまだやらないからcontrollerで完結させても一応はできる。
 */
@Repository
public interface MenuRepository extends CrudRepository<Menu, UUID> {}