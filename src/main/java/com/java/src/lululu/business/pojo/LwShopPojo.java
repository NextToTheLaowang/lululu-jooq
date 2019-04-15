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

@ApiModel(value = "LwShopPojo", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LwShopPojo extends AbstractBasePojo {

	@ApiModelProperty(value = "")
	protected Long id;
	@ApiModelProperty(value = "备注信息")
	protected String remarks;
	@ApiModelProperty(value = "店名")
	protected String name;
	@ApiModelProperty(value = "地址")
	protected String address;
	@ApiModelProperty(value = "联系电话")
	protected String contactNumber;
}