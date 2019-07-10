package com.example.Controller;

import com.example.annotation.TestAnnotation;
import com.example.model.springdb.Users;
import com.example.model.winnerdb.Student;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangning on 2017/10/23.
 */

@Controller
public class PageController {

    @Value("${application.hello:hello world}")
    private String helloMessage = "hello world";

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(PageController.class);

    @RequestMapping(value = {"/", "/index"})
    public String index(Map<String, Object> model) {
        logger.info("--this is index page.");
        System.out.println("this is index controller");
        model.put("time", new Date());
        model.put("message", this.helloMessage);
        System.out.println(model.get("time"));
        return "index";
    }

    @RequestMapping(value = "getUsers")
    @ResponseBody
    @TestAnnotation(name = "wangning")
    public List<Users> getUsers() {
        List users = userService.getUsers();
        List userList = userService.getUsersByMapper();
        Users user = userService.getUsersByUsername("admin");
        List<Student> students = userService.getAllStudents();
        logger.info("--all Student--:" + students);
        logger.info("---userList:" + userList);
        logger.info("--user:" + user);
        return users;
    }


}
