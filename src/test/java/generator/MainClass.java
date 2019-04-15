package generator;


import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 2016/12/8.
 */
public class MainClass {
    public static String EXCLUDES = "";//忽略哪些表 不自动生成 多表名用|隔开

    public void codeGgenerator() throws Exception {
        List list = new ArrayList();
        Schema schema = new Schema();
        schema.setInputSchema("LW");
        schema.setOutputSchema("LW");
        list.add(schema);
        File directory = new File(".");
        String path = directory.getCanonicalPath();

        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("com.mysql.jdbc.Driver")
                        .withUrl("jdbc:mysql://139.199.101.89:3306/LW?useUnicode=true&characterEncoding=utf-8&useSSL=false")
                        .withUser("wangqiyue")
                        .withPassword("lw123456."))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                // .withRecordTimestampFields("create_at")
                                .withRecordVersionFields("version").withDateAsTimestamp(false)
                                //.withForcedTypes(new ForcedType().withExpression("is_*").withName("BOOLEAN"))

                                .withName("org.jooq.util.mysql.MySQLDatabase")
                                .withIncludes(".*")
                                .withExcludes(EXCLUDES)
                                .withSchemata(list))
                        .withStrategy(new Strategy().withName("generator.CustomGenertor"))
                        .withGenerate(new Generate().withFluentSetters(true)
                                                    .withPojos(false).withDaos(false))
                        .withTarget(new Target()
                                .withPackageName("com.java.src.lululu.business.domain")
//                                .withDirectory("D://xue/xuebei-all/src/main/java")));

                                .withDirectory(path + "/src/main/java")));

        configuration.getGenerator().setName("generator.CustomJavaGenerator");

        GenerationTool.generate(configuration);

    }

    public static void main(String[] args) throws Exception {
        MainClass mainClass = new MainClass();
        mainClass.codeGgenerator();
    }
}