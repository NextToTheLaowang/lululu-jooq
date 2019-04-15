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
import com.java.src.lululu.business.domain.enums.LwAccountType;

@ApiModel(value = "LwAccountPojo", description = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LwAccountPojo extends AbstractBasePojo {

	@ApiModelProperty(value = "")
	protected Long id;
	@ApiModelProperty(value = "备注信息")
	protected String remarks;
	@ApiModelProperty(value = "姓名")
	protected String name;
	@ApiModelProperty(value = "电话")
	protected String mobile;
	@ApiModelProperty(value = "密码")
	protected String password;
	@ApiModelProperty(value = "照片")
	protected String photo;
	@ApiModelProperty(value = "登录名")
	protected String loginName;
	@ApiModelProperty(value = "性别")
	protected Integer sex;
	@ApiModelProperty(value = "地址")
	protected String address;
	@ApiModelProperty(value = "token")
	protected String token;
	@ApiModelProperty(value = "")
	protected String wxOpenid;
	@ApiModelProperty(value = "")
	protected Date birthday;
	@ApiModelProperty(value = "")
	protected Long shopId;
	@ApiModelProperty(value = "员工类型  店长  店员")
	protected LwAccountType type;
	@ApiModelProperty(value = "")
	protected String salt;
}