package com.java.src.lululu.dto;

import com.java.src.lululu.business.pojo.AbstractBasePojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by jimmy on 2017/2/9.
 */
@ToString
public class ResultDo<T> extends AbstractBasePojo {

    @ApiModelProperty(value = "操作是否成功")
    private boolean isSuccess = true;
    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码")
    private String error;
    /**
     * 错误描述信息
     */
    @ApiModelProperty(value = "错误描述信息")
    private String errorDescription;

    /**
     * 返回结果对象
     */
    @ApiModelProperty(value = "操作正确后返回结果对象")
    private T result;

    public ResultDo() {
    }

    public ResultDo(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public static ResultDo build() {
        return new ResultDo();
    }

    public static ResultDo build(String messCodeConstant) {
        ResultDo resultDo = new ResultDo();
        resultDo.setError(messCodeConstant).setErrorDescription("").setSuccess(false);
        return resultDo;
    }

    public static ResultDo buildCode(String messCodeConstant) {
        ResultDo resultDo = new ResultDo();
        resultDo.setErrorCode(messCodeConstant);
        resultDo.setSuccess(false);
        return resultDo;
    }


    public String getErrorDescription() {
        return errorDescription;
    }

    public ResultDo setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
        if (StringUtils.isNotEmpty(this.errorDescription)) {
            this.setSuccess(false);
        }
        return this;
    }

    public void setErrorCode(String messCodeConstant) {
        this.setError(messCodeConstant).setErrorDescription(messageSource(messCodeConstant)).setSuccess(false);
    }

    public void setErrorCode(String messCodeConstant, Object[] objects) {
        this.setError(messCodeConstant).setErrorDescription(messageSource(messCodeConstant, objects)).setSuccess(false);
    }


    public String getError() {
        return error;
    }

    public ResultDo setError(String error) {
        this.error = error;
        if (StringUtils.isNotEmpty(this.error)) {
            this.isSuccess = false;
        }
        return this;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isError() {
        return !isSuccess;
    }

    public ResultDo setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }


}