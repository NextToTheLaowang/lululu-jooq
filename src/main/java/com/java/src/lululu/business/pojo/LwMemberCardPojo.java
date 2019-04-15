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
import java.lang.Byte;

@ApiModel(value = "LwMemberCardPojo", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LwMemberCardPojo extends AbstractBasePojo {

	@ApiModelProperty(value = "")
	protected Long id;
	@ApiModelProperty(value = "备注信息")
	protected String remarks;
	@ApiModelProperty(value = "")
	protected Long shopId;
	@ApiModelProperty(value = "会员id")
	protected Long memberId;
	@ApiModelProperty(value = "会员卡号")
	protected String cardNo;
	@ApiModelProperty(value = "余额")
	protected BigDecimal money;
	@ApiModelProperty(value = "积分")
	protected Integer integral;
	@ApiModelProperty(value = "状态  预留字段")
	protected Byte state;
}