package com.example.making_restaurant.infrastructure;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商品クラス このクラスはDBとの関連付けのためのクラス このクラスを使ってDBへの操作を行う。 DBの操作を行うのはRepositoryと呼ばれるところ
 */
@Entity
public class File {

    @Id
    private UUID uuid;
    private String name;
    private String startdate;
    private String enddate;

    
    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuId(UUID uuid) {
        this.uuid = uuid;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartdate() {
        return this.startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return this.enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}