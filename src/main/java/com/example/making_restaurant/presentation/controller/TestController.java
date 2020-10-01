package com.example.making_restaurant.presentation.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.making_restaurant.application.service.FileService;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {

    private final FileService fileService;

    public TestController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("file2")
    public String file2(Model model) throws IOException, CsvValidationException {
        try {
            // ファイルのパスを指定する
            File file = new File("C:\\Users\\rolep\\workspace\\making_restaurant\\src\\main\\resources\\test.txt");

            // ファイルが存在しない場合に例外が発生するので確認する
            // if (!file.exists()) {
            // System.out.print("ファイルが存在しません");
            // return;
            // }

            // BufferedReaderクラスのreadLineメソッドを使って1行ずつ読み込み表示する
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data;
            int cnt = 0;
            String mozi1 = "^[0-9].+年.+月.*";
            String mozi2 = "^[0-9].+旬.*";
            List<String> fl = new ArrayList<String>();
            int x = 0;

            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            while ((data = bufferedReader.readLine()) != null) {
                fl.add(data);
                if (data.matches(mozi1) || data.matches(mozi2)) {
                    cnt = cnt + 1;
                }

                if (cnt == 1) {
                    if (fl.get(x).contains("～")) {
                        String[] splitfl = fl.get(x).split(("～"));

                        sb1 = new StringBuilder(splitfl[0]);
                        sb2 = new StringBuilder(splitfl[1]);
                        // 末尾から1文字分を削除
                        sb1.setLength(sb1.length() - 3);
                        sb2.setLength(sb2.length() - 3);
                    }
                    System.out.println(fl.get(x - 1) + " "+ sb1.toString()+ " "+  sb2.toString());
                    fileService.create(fl.get(x - 1), sb1.toString(), sb2.toString());
                    cnt = 0;
                }
                x = x + 1;
                System.out.println(data);
            }

            // 最後にファイルを閉じてリソースを開放する
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<com.example.making_restaurant.infrastructure.File> eventlist = fileService.findAll();
        model.addAttribute("eventlist", eventlist);
        return "eventlist";

    }

}