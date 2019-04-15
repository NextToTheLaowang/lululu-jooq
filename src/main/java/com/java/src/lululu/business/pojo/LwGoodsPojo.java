package com.java.src.lululu.business.pojo;

import com.java.src.lululu.business.pojo.AbstractBasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.lang.Integer;

@ApiModel(value = "LwGoodsPojo", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LwGoodsPojo extends AbstractBasePojo {

	@ApiModelProperty(value = "")
	protected Long id;
	@ApiModelProperty(value = "备注信息")
	protected String remarks;
	@ApiModelProperty(value = "")
	protected Long shopId;
	@ApiModelProperty(value = "商品名")
	protected String goodsName;
	@ApiModelProperty(value = "商品单价")
	protected BigDecimal price;
	@ApiModelProperty(value = "返用积分")
	protected Integer integral;
	@ApiModelProperty(value = "")
	protected Integer state;
}