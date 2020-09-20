package com.example.making_restaurant.presentation.controller;

import java.io.FileReader;
// なんか必要なimportも消してない？alt shitf o
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.making_restaurant.application.service.MenuService;
import com.example.making_restaurant.application.service.RamenService;
import com.example.making_restaurant.infrastructure.Ramen;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RestController
@RequestMapping("/")
public class HelloController {

    private final MenuService menuService;
    private final RamenService ramenService;

    public HelloController(MenuService menuService, RamenService ramenService) {
        this.menuService = menuService;
        this.ramenService = ramenService;
    }

    @PostMapping("hello")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータを取得
        String confirm1 = request.getParameter("name"); // 名前
        String confirm2 = request.getParameter("price"); // 値段

        // 商品登録
        menuService.create(confirm1, Integer.valueOf(confirm2).intValue());
    }

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello");
        return "hello";
    }

    @GetMapping("bye")
    public String bye() {
        return "bye";
    }

    @GetMapping("ramenlist")
    public String ramen(Model model) throws IOException, CsvValidationException {
        FileReader fileReader = null;
        CSVReader csvReader = null;
        try {
            fileReader = new FileReader(
                    "C:\\Users\\rolep\\workspace\\making_restaurant\\src\\main\\resources\\list.csv");
            csvReader = new CSVReader(fileReader);
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                System.out.println(record[0] + "," + record[1] + "," + record[2]);
                ramen2(record[0], record[1], record[2]);
            }
        } catch (IOException e) {
            throw e;
        } catch (CsvValidationException e) {
            throw e;
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (csvReader != null) {
                csvReader.close();
            }
        }

        List<Ramen> ramenlist = ramenService.findAll();
        model.addAttribute("ramenlist", ramenlist);
        return "ramenlist";

    }


    public void ramen2(String id, String name, String genre) {

        ramenService.create(id, name, genre);

    }

    @GetMapping("ramensearch")
    public String ramensearch() {
        return "ramensearch";
    }

    @PostMapping("ramensearch")
    protected String doPost2(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String confirm1 = request.getParameter("name"); // 店名
        List<Ramen> ramenlist = ramenService.find(confirm1);
        model.addAttribute("ramenlist", ramenlist);
        return "ramenlist";

    }
}