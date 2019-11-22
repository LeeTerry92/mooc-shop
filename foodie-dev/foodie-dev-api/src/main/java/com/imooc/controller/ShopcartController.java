package com.imooc.controller;

import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@ApiIgnore
@Api(value = "购物车",tags={"购物车相关接口"})
@RestController
@RequestMapping("shopcart")
public class ShopcartController {

    private final static Logger logger = LoggerFactory.getLogger(ShopcartController.class);

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "添加商品到购物车",notes = "添加商品到购物车",httpMethod = "GET")
    @GetMapping("/add")
    public IMOOCJSONResult add(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "shopcartBO",value = "购物车对象",required = true)
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response){

        if(StringUtils.isBlank(userId)){
            return  IMOOCJSONResult.errorMsg("");
        }

        //todo 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存


        return IMOOCJSONResult.ok();
    }


    @ApiOperation(value = "根据商品规格id刷新购物车里商品的最新信息",notes = "根据商品规格id刷新购物车里商品的最新信息",httpMethod = "GET")
    @GetMapping("/refresh")
    public IMOOCJSONResult refresh(
            @ApiParam(name = "itemSpecIds",value = "商品规格ids",required = true)
            @RequestParam String itemSpecIds){

        if(StringUtils.isBlank(itemSpecIds)){
            return  IMOOCJSONResult.errorMsg("");
        }

        List<ShopcartVO> result = itemService.queryItemBySpecIds(itemSpecIds);


        return IMOOCJSONResult.ok(result);
    }

    @ApiOperation(value = "删除购物车商品",notes = "删除购物车商品",httpMethod = "POST")
    @GetMapping("/del")
    public IMOOCJSONResult del(
            @ApiParam(name = "itemSpecId",value = "商品规格id",required = true)
            @RequestParam String itemSpecId,
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId){

        if(StringUtils.isBlank(itemSpecId)||StringUtils.isBlank(userId)){
            return  IMOOCJSONResult.errorMsg("");
        }

        //todo 用户商品删除操作，如果用户已登录，删除商品的时候还要同步更新后端购物车数据

        return IMOOCJSONResult.ok();
    }

}
