package vn.techmaster.btbuoi1.Controller;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.btbuoi1.Model.Bmi;
import vn.techmaster.btbuoi1.Model.Student;

@Controller
@RequestMapping

public class HomeController {

    // funtion random
    @RequestMapping(value = "/random", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String Random() {
        // sử dụng thu vien RandomStringUtils
        return RandomStringUtils.randomAlphanumeric(8);
    }

    @RequestMapping(value = "/random2", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String Random2() {
        // sử dụng mảng
        char[] x = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z' };

        String s = "";
        for (int i = 0; i < 8; i++) {
            Random ran = new Random();
            int a = ran.nextInt(61);
            s += x[a];
        }
        return s;
    }

    // funtion quote
    @RequestMapping(value = "/quote", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String Quote() {
        String a = "Kiến tha lâu đầy tổ";
        String b = "Có công mài sắt, có ngày nên kim";
        String c = "Không thầy đố mày làm nên";
        String d = "Học thầy không tày học bạn";

        Random ran = new Random();
        int x = ran.nextInt(4);
        if (x == 0) {
            return a;
        } else if (x == 1) {
            return b;
        } else if (x == 2) {
            return c;
        } else {
            return d;
        }
    }

    // funtion bmi
    @PostMapping(value = "/bmi", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public float echoBmi(@RequestBody Bmi bmi) {
        return bmi.weight() / (bmi.height() * bmi.height());
    }

    // funtion post - get
    ArrayList<Student> list = new ArrayList<>();

    @PostMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addStudent(@RequestBody Student student) {
        list.add(student);
        return;
    }

    @GetMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ArrayList<Student> getStudent() {
        return list;
    }

}
