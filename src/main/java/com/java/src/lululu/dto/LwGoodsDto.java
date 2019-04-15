package com.java.src.lululu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@ApiModel(value = "LwGoodsDto", description = "商品dto")
public class LwGoodsDto {
    protected Long id;

    @ApiModelProperty(value = "备注信息")
    protected String remarks;

    @ApiModelProperty(value = "")
    protected Long shopId;

    @ApiModelProperty(value = "商品名")
    @NotEmpty(message = "商品名不能为空")
    @Size(max = 200,message = "商品名最大长度200字符")
    protected String goodsName;

    @ApiModelProperty(value = "商品单价")
    @NotNull(message = "请输入商品单价")
    protected BigDecimal price;

    @ApiModelProperty(value = "返用积分")
    @NotNull(message = "请输入商品单价")
    protected Integer integral;

    @ApiModelProperty(value = "")
    protected Integer state;
}