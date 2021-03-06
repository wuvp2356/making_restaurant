package com.example.making_restaurant.application.service;

import java.util.UUID;

import com.example.making_restaurant.infrastructure.File;
import com.example.making_restaurant.infrastructure.FileRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
 * というわけで、商品の新規登録、一覧表示の機能を画面も合わせて実装してみて。
 * 画面から新規登録するときはhtmlのformっていうのを使うと、楽にPOSTできるよ。
 * 多分PHPでやってたから分かると思う。
 * あ、あとgit push ってできる？
 */
@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * メニューを全検索する
     */
    public List<File> findAll() {
        return (List)fileRepository.findAll();
    }

    /**
     * メニューを新しく作る
     */
    public File create(String name,String startdate,String enddate) {
        final File file = new File();
        file.setUuId(UUID.randomUUID());
        file.setName(name);
        file.setStartdate(startdate);
        file.setEnddate(enddate);

        return fileRepository.save(file);
 
    }
}