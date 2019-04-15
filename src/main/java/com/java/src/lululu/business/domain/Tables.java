/*
 * This file is generated by jOOQ.
*/
package com.java.src.lululu.business.domain;


import com.java.src.lululu.business.domain.tables.LwAccount;
import com.java.src.lululu.business.domain.tables.LwGoods;
import com.java.src.lululu.business.domain.tables.LwMember;
import com.java.src.lululu.business.domain.tables.LwMemberCard;
import com.java.src.lululu.business.domain.tables.LwOrder;
import com.java.src.lululu.business.domain.tables.LwShop;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in LW
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>LW.lw_account</code>.
     */
    public static final LwAccount LW_ACCOUNT = com.java.src.lululu.business.domain.tables.LwAccount.LW_ACCOUNT;

    /**
     * The table <code>LW.lw_goods</code>.
     */
    public static final LwGoods LW_GOODS = com.java.src.lululu.business.domain.tables.LwGoods.LW_GOODS;

    /**
     * The table <code>LW.lw_member</code>.
     */
    public static final LwMember LW_MEMBER = com.java.src.lululu.business.domain.tables.LwMember.LW_MEMBER;

    /**
     * The table <code>LW.lw_member_card</code>.
     */
    public static final LwMemberCard LW_MEMBER_CARD = com.java.src.lululu.business.domain.tables.LwMemberCard.LW_MEMBER_CARD;

    /**
     * The table <code>LW.lw_order</code>.
     */
    public static final LwOrder LW_ORDER = com.java.src.lululu.business.domain.tables.LwOrder.LW_ORDER;

    /**
     * The table <code>LW.lw_shop</code>.
     */
    public static final LwShop LW_SHOP = com.java.src.lululu.business.domain.tables.LwShop.LW_SHOP;
}
