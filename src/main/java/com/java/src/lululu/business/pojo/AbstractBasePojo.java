package com.java.src.lululu.business.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.src.lululu.utils.SpringUtil;
import org.springframework.context.MessageSource;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jimmy on 2016/12/10.
 */
public abstract class AbstractBasePojo implements Serializable {
    /**
     * 获取messageSource的消息
     *
     * @param code
     * @return //
     */
    protected static String messageSource(String code) {
        return SpringUtil.getBean(MessageSource.class).getMessage(code, null, code, null);
    }

    /**
     * 获取messageSource的消息
     *
     * @param code
     * @return
     */
    protected static String messageSource(String code, Object[] objects) {
        return SpringUtil.getBean(MessageSource.class).getMessage(code, objects, code, null);
    }


    @JsonIgnore
    protected Timestamp createDate;
    @JsonIgnore
    protected Timestamp updateDate;
    @JsonIgnore
    protected Boolean delFlag;
    @JsonIgnore
    protected Long version;

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}