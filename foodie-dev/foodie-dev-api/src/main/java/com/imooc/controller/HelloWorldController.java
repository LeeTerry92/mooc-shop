package com.imooc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@ApiIgnore
@Api(value = "hello测试",tags={"hello测试接口"})
@RestController
public class HelloWorldController {

    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @ApiOperation(value = "测试接口1",notes = "测试接口1",httpMethod = "GET")
    @GetMapping("/helloworld")
    public String hello(){
        return "helloWorld";
    }


    @ApiOperation(value = "session测试接口1",notes = "session测试接口1",httpMethod = "GET")
    @GetMapping("/getSession")
    public Object setSession(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("userInfo","new user");
        session.setMaxInactiveInterval(10);
        session.getAttribute("userInfo");
      //  session.removeAttribute("userInfo");

        return "ok";
    }

}
