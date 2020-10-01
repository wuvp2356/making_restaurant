package com.example.making_restaurant.presentation.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.making_restaurant.application.service.MenuService;
import com.example.making_restaurant.application.service.RamenService;
import com.example.making_restaurant.infrastructure.Ramen;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class HelloController {

    private final MenuService menuService;
    private final RamenService ramenService;

    public HelloController(final MenuService menuService, final RamenService ramenService) {
        this.menuService = menuService;
        this.ramenService = ramenService;
    }

    @PostMapping("hello")
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータを取得
        final String confirm1 = request.getParameter("name"); // 名前
        final String confirm2 = request.getParameter("price"); // 値段

        // 商品登録
        menuService.create(confirm1, Integer.valueOf(confirm2).intValue());
    }

    @GetMapping("hello")
    public String hello(final Model model) {
        model.addAttribute("message", "Hello");
        return "hello";
    }

    @GetMapping("bye")
    public String bye() {
        return "bye";
    }

    @GetMapping("ramen_top")
    public String ramentop() {
        return "ramen_top";
    }

    @GetMapping("ramen_photo")
    @ResponseBody
    protected Resource ramen_p(final Model model, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {

        Resource resource = new FileSystemResource("src\\main\\resources\\ramen.jpg");
        response.setContentType("image/jpg");
        response.setHeader("Content-Disposition", "attachment;filename=ramentest.jpg");
        return resource;

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

    @GetMapping("ramen_photos")
    protected String rps(final Model model, final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        //ファイル名の一覧を取得する
        File file = new File("src\\main\\resources\\photos");
        File files[] = file.listFiles();

        List<Resource> resources = new ArrayList<Resource>();
        //取得した一覧を表示する
        for (int i=0; i<files.length; i++) {
            resources.add(new FileSystemResource(files[i]));
            System.out.println("ファイル" + (i+1) + "→" + files[i]);
        }


        // response.setContentType("image/jpg");
        // response.setHeader("Content-Disposition", "attachment;filename=ramentest.jpg");
        model.addAttribute("resources", resources);

        return "ramen_photos";

        // final String confirm1 = request.getParameter("name"); // 店名
        // final List<Ramen> ramenlist = ramenService.find(confirm1);
        // model.addAttribute("ramenlist", ramenlist);
        // return "ramenlist";

    }


    @GetMapping("ramenlist_downroad")
    @ResponseBody
    protected Resource ramenlist_downroad(final Model model, final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException {

        Resource resource = new FileSystemResource("src\\main\\resources\\ramen.jpg");
        response.setContentType("image/jpg");
        response.setHeader("Content-Disposition", "attachment;filename=ramentest.jpg");
        return resource;

    }

      // 引数名 `file` は、ファイルのリクエストパラメータ名と一致させる。
  @PostMapping(value="/uploadFile")
  public void handle(
    HttpServletResponse response,MultipartFile file
  ){
    // ファイルが空の場合は HTTP 400 を返す。
    if (file.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    // アップロードされたファイルを保存。
    try {
      InputStream in =file.getInputStream();
      OutputStream out = new FileOutputStream("src\\main\\resources\\photos\\"+file.getOriginalFilename());

      FileCopyUtils.copy(in, out);
      
    } catch (IOException e) {   
      throw new RuntimeException("Error uploading file.", e);
    }
  }

}