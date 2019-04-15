package generator;

import org.jooq.tools.StringUtils;
import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

/**
 * Created by jimmy on 2016/12/10.
 */
public class CustomGenertor extends DefaultGeneratorStrategy {

    public String getJavaClassName(Definition definition, Mode mode) {
        return this.getJavaClassName0(definition, mode);
    }

    @Override
    public String getJavaPackageName(Definition definition, Mode mode) {
        if (mode == Mode.POJO)
            return Config.COM_CHANGFU_RISK_POJO;
        return super.getJavaPackageName(definition, mode);
    }

    private String getJavaClassName0(Definition definition, Mode mode) {
        StringBuilder result = new StringBuilder();
        result.append(StringUtils.toCamelCase(definition.getOutputName()
                                                        .replace(' ', '_')
                                                        .replace('-', '_')
                                                        .replace('.', '_')));
        if (mode == Mode.RECORD) {
            result.append("Record");
        } else if (mode == Mode.DAO) {
            result.append("Dao");
        } else if (mode == Mode.INTERFACE) {
            result.insert(0, "I");
        } else if (mode == Mode.POJO)
            result.append("Pojo");

        return result.toString();
    }


    @Override
    public String getJavaClassExtends(Definition definition, Mode mode) {
        return Config.COM_CHANGFU_RISK_POJO_ABSTRACT_BASE_POJO;
    }

    private String generJavaPackageName(Definition definition, Mode mode) {
        StringBuilder sb = new StringBuilder();
        sb.append(Config.COM_CHANGFU_RISK_POJO);
        return sb.toString();
    }


}
