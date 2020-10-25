package com.example.making_restaurant.presentation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.making_restaurant.application.service.RamenService;
import com.example.making_restaurant.infrastructure.Ramen;
import com.example.making_restaurant.infrastructure.RamenImage;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class HelloController {

    private final RamenService ramenService;

    public HelloController(final RamenService ramenService) {
        this.ramenService = ramenService;
    }

    @GetMapping("hello")
    public String hello(final Model model) {
        model.addAttribute("message", "Hello");
        return "hello";
    }

    @GetMapping("ramen_top")
    public String ramentop() {
        return "ramen_top";
    }

    @GetMapping("ramen_photo")
    @ResponseBody
    protected Resource ramen_p(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        Resource resource = new FileSystemResource("src\\main\\resources\\ramen.jpg");
        response.setContentType("image/jpg");
        return resource;

    }

    @GetMapping("ramen_photos")
    protected String photos1(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        List<RamenImage> rmList = new ArrayList<RamenImage>();

        // ファイル名の一覧を取得する
        File file = new File("src\\main\\resources\\photos");
        File files[] = file.listFiles();

        // 取得した一覧を表示する
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains("ramen")) {
                String mozitime = getTime(files[i].lastModified());
                System.out.println(mozitime);

                RamenImage rm = new RamenImage(files[i].getName(), mozitime);
                rmList.add(rm);
                System.out.println(files[i].getName());
                System.out.println(files[i].getPath());
            }
            System.out.println("ファイル" + (i + 1) + "→" + files[i]);
        }
        model.addAttribute("rmList", rmList);
        return "ramen_photos";

    }

    protected String getTime(Long a) throws ServletException, IOException {

        System.out.println(a);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String lastModifiedStr = sdf.format(a);

        return lastModifiedStr;

    }

    // @GetMapping("{ex:.+png|.+jpg}")
    // @ResponseBody
    // public Resource re_gazou(@PathVariable String ex ,final HttpServletResponse
    // response) throws IOException, CsvValidationException {

    // Resource resource = new FileSystemResource("src\\main\\resources\\photos\\" +
    // ex);
    // return resource;

    // }

    @GetMapping("{ex:.+png|.+jpg}")
    @ResponseBody
    public Resource re_gazou2(@PathVariable String ex, final HttpServletResponse response)
            throws IOException, CsvValidationException {

        Resource resource1 = new FileSystemResource("src\\main\\resources\\photos\\" + ex);
        // return RamenImage.reSize1(ImageIO.read(resource.getFile()) ,40 ,50);
        ImageIO.write(RamenImage.reSize1(ImageIO.read(resource1.getFile()), 500, 500), "JPG",
                new File("src\\main\\resources\\photos\\" + "hoge.jpg"));
        Resource resource2 = new FileSystemResource("src\\main\\resources\\photos\\" + "hoge.jpg");
        return resource2;
    }

    @GetMapping("ramenlist_s")
    public String ramen_start(final Model model) throws IOException, CsvValidationException {
        FileReader fileReader = null;
        CSVReader csvReader = null;
        try {
            fileReader = new FileReader(
                    "C:\\Users\\rolep\\workspace\\making_restaurant\\src\\main\\resources\\list.csv");
            csvReader = new CSVReader(fileReader);
            String[] record = null;
            boolean flag = true;
            while ((record = csvReader.readNext()) != null) {
                if (flag == true) {
                    flag = false;
                    continue;
                }
                System.out.println(record[0] + "," + record[1] + "," + record[2]);
                ramen2(record[0], record[1], record[2]);
            }
        } catch (final IOException e) {
            throw e;
        } catch (final CsvValidationException e) {
            throw e;
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (csvReader != null) {
                csvReader.close();
            }
        }

        return "ramenlist_s";

    }

    @GetMapping("ramenlist")
    public String ramen(final Model model) throws IOException, CsvValidationException {
        final List<Ramen> ramenlist = ramenService.findAll();
        model.addAttribute("ramenlist", ramenlist);
        return "ramenlist";

    }

    public void ramen2(final String id, final String name, final String genre) {

        ramenService.create(id, name, genre);

    }

    @GetMapping("ramensearch")
    public String ramensearch() {
        return "ramensearch";
    }

    @PostMapping("ramensearch")
    protected String doPost2(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        final String confirm1 = request.getParameter("name"); // 店名
        final List<Ramen> ramenlist = ramenService.find(confirm1);
        model.addAttribute("ramenlist", ramenlist);
        return "ramenlist";

    }

    @GetMapping("ramenlist_downroad")
    @ResponseBody
    protected void ramenlist_downroad(final Model model, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {

        File resource = new File("src\\main\\resources\\ramen.jpg");

        InputStream in = new FileInputStream(resource);

        File file = new File("C:\\Users\\rolep\\Downloads\\file.jpg"); // 保存先
        FileOutputStream out = new FileOutputStream(file);

        // Xバイトの保存場所（配列として用意する。）
        byte[] bytes = new byte[1];
        while (true) {
            // 右辺:バイト数
            // 左辺:最大Xバイト読み込むが途中で途切れたら、その分だけ読み込む
            int ret = in.read(bytes);
            System.out.println(ret);
            if (ret == -1)
                break;
            // 読み込んだデータを先頭から読み込んだバイト数読み込む
            out.write(bytes, 0, ret);
        }

        out.close();
        in.close();

        // return resource;

    }

    // 引数名 `file` は、ファイルのリクエストパラメータ名と一致させる。
    @PostMapping("uploadFile")
    public void handle(HttpServletResponse response, MultipartFile file) {
        // MultipartFile:ユーザーが送信した画像データを保持。
        // ユーザー側のパスをサーバーは知らない。

        try {
            InputStream fis = file.getInputStream(); // パスが必要ないから右辺もinputstreamなのか。（データだけあればよい）
            OutputStream fos = new FileOutputStream("src\\main\\resources\\photos\\a.jpg"); // データの書き込む場所の指定も必要なので、fileoutputstreamが必要と想定
            int c = 0;
            while (true) {
                // 1バイトずつ読み込む
                c = fis.read();
                System.out.println(c);
                if (c == -1) {
                    break;
                }
                fos.write(c);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}