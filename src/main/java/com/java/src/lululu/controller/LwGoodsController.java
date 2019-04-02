package com.java.src.lululu.controller;

import com.java.src.lululu.service.LwGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("front/goods/")
@Api(value = "front/goods/", description = "商品相关")
public class LwGoodsController {

    @Autowired
    private LwGoodsService lwGoodsService;

    @GetMapping("getById")
    @ApiImplicitParam(paramType = "query", name = "id", value = "商品id", required = true)
    public Object getById(Long id){
        return lwGoodsService.getById(id);
    }
}