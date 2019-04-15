package com.java.src.lululu.business.service;


import com.java.src.lululu.business.pojo.LwGoodsPojo;
import com.java.src.lululu.business.repository.LwGoodsRepository;

import com.java.src.lululu.common.utils.DateTools;
import lombok.extern.slf4j.Slf4j;

import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;


/**
 * This class is generated by jOOQ.
 */
@Slf4j
@Service
public class LwGoodsService  {

    @Autowired
    private LwGoodsRepository lwGoodsRepository;

    public LwGoodsPojo getById(Long id){
        return lwGoodsRepository.getById(id);
    }

    public Page<LwGoodsPojo> findListPage(Pageable pageable, Collection<? extends Condition> condition){
        return lwGoodsRepository.findByPageable(pageable,condition);
    }

    public void insert(LwGoodsPojo lwGoodsPojo){
        lwGoodsPojo.setCreateDate(DateTools.getCurrentDateTime());
        lwGoodsRepository.insert(lwGoodsPojo);
    }

    public void update(LwGoodsPojo lwGoodsPojo){
        lwGoodsPojo.setUpdateDate(DateTools.getCurrentDateTime());
        lwGoodsRepository.update(lwGoodsPojo);
    }

    public void updateGoodsName(Long id,String goodsName){
        lwGoodsRepository.updateGoodsName(id,goodsName);
    }

    public void delete(Long id){
        lwGoodsRepository.deleteFlag(id);
    }
}
