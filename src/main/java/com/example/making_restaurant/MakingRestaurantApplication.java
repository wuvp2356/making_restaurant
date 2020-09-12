package com.example.making_restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakingRestaurantApplication {

	// 多分あとはDBのCRUDだけだから、ほとんど作れるようになるんじゃないかな
	// というわけでDBに登録、参照、更新、削除の機能を作ってみる
	// DBへのデータの出し入れはインフラ層の仕事だからinfrastructure下にクラスを作る
	public static void main(String[] args) {
		SpringApplication.run(MakingRestaurantApplication.class, args);
	}

}
