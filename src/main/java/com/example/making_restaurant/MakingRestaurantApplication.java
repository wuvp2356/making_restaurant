package com.example.making_restaurant;
// ctrl shift p で "togg　add、ステージングはどうやるんだっけか。
// https://vim-adventures.com/ vimのキーを練習できるゲーム 途中までは確か無料
// まあある程度開発も進んできたからちょっと説明すると、コミットは（人によるけど）比較的小さい単位でコミットする
// コミットメッセージに迷うのはコミットの単位が大きくなりすぎてる可能性がある
// あとgitは複数のコミットをまとめて一つのコミットにすることもできる
// RPGのセーブポイントだと思ってくれると良い
// push前なら修正できるjよ
// git commit --amend -m 'ここにコミットメッセージ' で直前のコミットメッセージを変更できる
// まずは git log
// jで下にいけるよ
// kで上に G で一番下に gg で一番上にいける
// q で抜ける
// vscodevimっていう拡張機能が必要
// normal modeでカーソル移動ができて、insert modeで文字を入力できるようになる
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