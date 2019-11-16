package com.imooc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

//@ApiIgnore
@Api(value = "hello测试",tags={"hello测试接口"})
@RestController
public class HelloWorldController {


    @ApiOperation(value = "测试接口1",notes = "测试接口1",httpMethod = "GET")
    @GetMapping("/helloworld")
    public String hello(){
        return "helloWorld";
    }

}
