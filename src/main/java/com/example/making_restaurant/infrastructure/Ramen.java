package com.example.making_restaurant.infrastructure;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商品クラス このクラスはDBとの関連付けのためのクラス このクラスを使ってDBへの操作を行う。 DBの操作を行うのはRepositoryと呼ばれるところ
 */
@Entity
public class Ramen {

    @Id
    private UUID uuid;
    private String id;
    private String name;
    private String genre;

    public UUID getUuid() {
        return this.uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}