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
import java.sql.Timestamp;

@ApiModel(value = "LwOrderPojo", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LwOrderPojo extends AbstractBasePojo {

	@ApiModelProperty(value = "")
	protected Long id;
	@ApiModelProperty(value = "备注信息")
	protected String remarks;
	@ApiModelProperty(value = "订单号")
	protected String orderNo;
	@ApiModelProperty(value = "订单金额")
	protected BigDecimal orderMoney;
	@ApiModelProperty(value = "实际支付金额")
	protected BigDecimal payMoney;
	@ApiModelProperty(value = "支付方式  现金  支付宝  微信。。。")
	protected Integer payWay;
	@ApiModelProperty(value = "支付类型   线上  线下")
	protected Integer payType;
	@ApiModelProperty(value = "支付时间")
	protected Timestamp payTime;
	@ApiModelProperty(value = "订单状态")
	protected Integer orderStatus;
	@ApiModelProperty(value = "支付状态")
	protected Integer payStatus;
	@ApiModelProperty(value = "")
	protected Long accountId;
	@ApiModelProperty(value = "用户id")
	protected Long memberId;
	@ApiModelProperty(value = "订单类型  消费  充值")
	protected Integer orderType;
	@ApiModelProperty(value = "")
	protected Long shopId;
	@ApiModelProperty(value = "商品id")
	protected Long goodsId;
	@ApiModelProperty(value = "返用积分")
	protected Integer integral;
	@ApiModelProperty(value = "")
	protected Integer goodsCount;
	@ApiModelProperty(value = "")
	protected String goodsName;
	@ApiModelProperty(value = "")
	protected BigDecimal goodsPrice;
}