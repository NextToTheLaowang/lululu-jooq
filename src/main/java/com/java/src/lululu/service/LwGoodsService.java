package com.java.src.lululu.service;

import com.java.src.lululu.gen.tables.daos.LwGoodsDao;
import com.java.src.lululu.gen.tables.pojos.LwGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LwGoodsService {

    @Autowired
    private LwGoodsDao lwGoodsDao;

    public LwGoods getById(Long id){
        return lwGoodsDao.fetchOneById(id);
    }
}