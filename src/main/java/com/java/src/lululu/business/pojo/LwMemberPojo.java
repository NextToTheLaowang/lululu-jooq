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
import java.lang.Integer;
import java.sql.Date;

@ApiModel(value = "LwMemberPojo", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LwMemberPojo extends AbstractBasePojo {

	@ApiModelProperty(value = "")
	protected Long id;
	@ApiModelProperty(value = "备注信息")
	protected String remarks;
	@ApiModelProperty(value = "姓名")
	protected String name;
	@ApiModelProperty(value = "电话")
	protected String mobile;
	@ApiModelProperty(value = "照片")
	protected String photo;
	@ApiModelProperty(value = "性别")
	protected Integer sex;
	@ApiModelProperty(value = "地址")
	protected String address;
	@ApiModelProperty(value = "")
	protected Date birthday;
	@ApiModelProperty(value = "")
	protected Long shopId;
}