package com.java.src.lululu.controller;

import com.google.common.collect.Lists;
import com.java.src.lululu.business.pojo.LwGoodsPojo;
import com.java.src.lululu.business.service.LwGoodsService;
import com.java.src.lululu.dto.LwGoodsDto;
import com.java.src.lululu.dto.ResultDo;
import com.java.src.lululu.common.utils.BeanUtil;
import com.java.src.lululu.common.utils.StringTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static com.java.src.lululu.business.domain.Tables.LW_GOODS;

@RestController
@RequestMapping("front/goods/")
@Api(value = "front/goods/", description = "商品相关")
public class LwGoodsController {

    @Autowired
    private LwGoodsService lwGoodsService;

    @GetMapping("getById")
    @ApiOperation(value = "商品详情", notes = "getById")
    @ApiImplicitParam(paramType = "query", name = "id", value = "商品id", required = true)
    public ResultDo<LwGoodsPojo> getById(Long id){
        ResultDo<LwGoodsPojo> resultDo = ResultDo.build();
        resultDo.setResult(lwGoodsService.getById(id));
        return resultDo;
    }

    @GetMapping("listPage")
    @ApiOperation(value = "商品列表分页", notes = "listPage")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "页码", required = true),
            @ApiImplicitParam(paramType = "query", name = "goodsName", value = "商品名")
    })
    public ResultDo<Page> listPage(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String goodsName){
        ResultDo<Page> resultDo = ResultDo.build();
        Collection<Condition> condition = Lists.newArrayList();
        if(StringTools.isNotEmpty(goodsName)){
            condition.add(LW_GOODS.GOODS_NAME.like("%".concat(goodsName).concat("%")));
        }
        Page<LwGoodsPojo> page = lwGoodsService.findListPage(pageable,condition);
        resultDo.setResult(page);
        return resultDo;
    }

    @PostMapping("insert")
    @ApiOperation(value = "新增商品", notes = "insert")
    public ResultDo insert(@RequestBody LwGoodsDto lwGoodsDto){
        LwGoodsPojo lwGoodsPojo = new LwGoodsPojo();
        BeanUtil.copyPropertiesIgnoreNull(lwGoodsDto,lwGoodsPojo);
        lwGoodsPojo.setShopId(1l);//取登陆信息
        lwGoodsService.insert(lwGoodsPojo);
        return ResultDo.build();
    }

    @PostMapping("update")
    @ApiOperation(value = "修改商品", notes = "update")
    public ResultDo update(@RequestBody LwGoodsDto lwGoodsDto){
        ResultDo resultDo = ResultDo.build();
        LwGoodsPojo lwGoodsPojo = lwGoodsService.getById(lwGoodsDto.getId());
        if(lwGoodsPojo == null){
            resultDo.setErrorCode("商品不存在");
            return resultDo;
        }
        BeanUtil.copyPropertiesIgnoreNull(lwGoodsDto,lwGoodsPojo);
        lwGoodsService.update(lwGoodsPojo);
        return resultDo;
    }

    @PostMapping("updateGoodsName")
    @ApiOperation(value = "修改商品名", notes = "updateGoodsName")
    public ResultDo updateGoodsName(@RequestBody LwGoodsDto lwGoodsDto){
        ResultDo resultDo = ResultDo.build();
        LwGoodsPojo lwGoodsPojo = lwGoodsService.getById(lwGoodsDto.getId());
        if(lwGoodsPojo == null){
            resultDo.setErrorCode("商品不存在");
            return resultDo;
        }
        lwGoodsService.updateGoodsName(lwGoodsDto.getId(),lwGoodsDto.getGoodsName());
        return resultDo;
    }

    @DeleteMapping("delete")
    @ApiOperation(value = "商品逻辑删除", notes = "delete")
    public ResultDo delete(@RequestParam Long id){
        lwGoodsService.delete(id);
        return ResultDo.build();
    }
}