package com.java.src.lululu.business.repository;


import com.java.src.lululu.business.domain.tables.records.LwAccountRecord;
import com.java.src.lululu.business.pojo.LwAccountPojo;
import com.java.src.lululu.business.repository.AbstractCRUDRepository;

import lombok.extern.slf4j.Slf4j;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.java.src.lululu.business.domain.Tables.*;
/**
 * This class is generated by jOOQ.
 */
@Slf4j
@Repository
public class LwAccountRepository extends AbstractCRUDRepository<LwAccountRecord, Long, LwAccountPojo> {

    @Autowired
    public LwAccountRepository(DSLContext dslContext) {
        super(dslContext, LW_ACCOUNT, LW_ACCOUNT.ID, LwAccountPojo.class);
    }
}