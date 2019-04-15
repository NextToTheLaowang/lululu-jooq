package generator;

import org.mockito.internal.util.collections.Sets;

import java.util.Set;

public class Config {
    public static final String COM_CHANGFU_RISK_POJO = "com.java.src.lululu.business.pojo";
    public static final String COM_CHANGFU_RISK_POJO_ABSTRACT_BASE_POJO = "com.java.src.lululu.business.pojo.AbstractBasePojo";
    static final String COM_CHANGFU_RISK_REPOSITORY_ABSTRACT_CRUDREPOSITORY = "com.java.src.lululu.business.repository.AbstractCRUDRepository";

    public static final String PACK_NAME = "com.java.src.lululu.business.";

    public static final String CONTROLLER_PACK_NAME = "com.java.src.lululu.";


    public static final Set set = Sets.newSet();

    static {
        set.add("SysMenuPojo");
        set.add("OauthAccessTokenPojo");
    }

    public static final Set need = Sets.newSet();

    static {
        // need.add("FinBalancePojo");

    }
}
