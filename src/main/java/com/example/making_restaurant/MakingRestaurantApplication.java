package com.example.making_restaurant;
// ctrl shift p で "togg　add、ステージングはどうやるんだっけか。すまぬ
// git add .
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakingRestaurantApplication {

	// 多分あとはDBのCRUDだけだから、ほとんど作れるようになるんじゃないかな
	// というわけでDBに登録、参照、更新、削除の機能を作ってみる
	// DBへのデータの出し入れはインフラ層の仕事だからinfrastructure下にクラスを作る
	public static void main(String[] args)  throws IOException{
		SpringApplication.run(MakingRestaurantApplication.class, args);
	}
	
}