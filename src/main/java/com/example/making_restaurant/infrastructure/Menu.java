package com.example.making_restaurant.infrastructure;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商品クラス
 * このクラスはDBとの関連付けのためのクラス
 * このクラスを使ってDBへの操作を行う。
 * DBの操作を行うのはRepositoryと呼ばれるところ
 */
@Entity
public class Menu {
    @Id
    private UUID id;
    private String name;
    private Integer price;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name; 
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}