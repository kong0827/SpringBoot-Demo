package com.kxj.swagger.controller;

import com.kxj.swagger.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName IndexController
 * @description TODO
 * @Author kongxiangjin
 * @Date 2019/12/19 13:35
 * @Version 1.0
 **/
@RequestMapping("index")
@RestController
public class IndexController {

    @ApiOperation("Hello控制类")
    @GetMapping("/get/list")
    public void get() {

    }

    @PostMapping("/user")
    public User save() {
        return new User();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() {

    }
}
