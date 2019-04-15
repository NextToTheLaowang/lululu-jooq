package generator;

import org.apache.commons.io.FileUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaUnit;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jooq.tools.JooqLogger;
import org.jooq.tools.StringUtils;
import org.jooq.util.*;
import org.mockito.internal.util.collections.Sets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static generator.Config.COM_CHANGFU_RISK_POJO;

/**
 * Created by jimmy on 2017/3/7.
 */
public class CustomJavaGenerator extends JavaGenerator {

    private static final JooqLogger log = JooqLogger.getLogger(CustomJavaGenerator.class);

    protected void generateTable(TableDefinition table, JavaWriter out) {
        super.generateTable(table, out);
        generatePojo0(table, out);
        try {
            generateRepository(table);
            generateService(table);
//            //  generateController(table);
        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        generateRepositoryTest(table);
//        generateServiceTest(table);
//        generateControllerTest(table);
    }


    private void generateRepository(TableDefinition table) throws Exception {
        String className = createClassName(table, GenType.repository);
        String dir = getTargetDirectory();
        String packageName = createPackeageName(table, GenType.repository);
        String pkg = packageName.replaceAll("\\.", "/");
        String fileName = className;
        File file = new File(dir + "/" + pkg, fileName + ".java");

        if (file.exists()) {
//            String javaCode = FileUtils.readFileToString(file);
//            JavaUnit unit = Roaster.parseUnit(javaCode);
//            JavaClassSource myClass = unit.getGoverningType();
//            //分析java method
//            List<MethodSource<JavaClassSource>> methods = myClass.getMethods();
//            Set set = methods.stream()
//                             .filter(method -> method.isPublic())
//                             .map(method -> method.getName())
//                             .collect(Collectors.toSet());
//
//            for (TypedElementDefinition<?> column : getTypedElements(table)) {
//                String javaMemberName = getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO);
//                String methodName = "findBy" + StringUtils.toCamelCase(javaMemberName);
//                if (!set.contains(methodName)) {
//
//                }
//            }

        } else {
//            JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
            JavaWriter out = createJavaWriter(className, dir, packageName);
            if (out == null) {
                return;
            }
            String superClass = out.ref(Config.COM_CHANGFU_RISK_REPOSITORY_ABSTRACT_CRUDREPOSITORY);
//
            String recodeClassName = getStrategy().getJavaPackageName(table, GeneratorStrategy.Mode.RECORD) + "." + getStrategy()
                    .getJavaClassName(table, GeneratorStrategy.Mode.RECORD);
            String pojoClassName = getStrategy().getJavaPackageName(table, GeneratorStrategy.Mode.POJO) + "." + getStrategy()
                    .getJavaClassName(table, GeneratorStrategy.Mode.POJO);
            out.println("package %s;", packageName);
            out.println();
            //// TODO: 2017/8/23  Import static
            out.printImports();
            out.print("import static " + Config.PACK_NAME + "domain.Tables.*;");
            out.println();
            printClassJavadoc(out, table);
            String slf4jLog = out.ref("lombok.extern.slf4j.Slf4j");
            out.println("@%s", slf4jLog);
            out.println("@%s", out.ref("org.springframework.stereotype.Repository"));
            String pkClass = "Long";
            //// TODO: 2017/8/22 pk
            out.println("public class %s extends %s<%s, %s, " + "%s> {", className, superClass, out.ref(recodeClassName),
                    pkClass, out.ref(pojoClassName));
            out.println();
            out.tab(1).println("@%s", out.ref("org.springframework.beans.factory.annotation.Autowired"));
            out.tab(1).println("public %s(%s dslContext) {", className, out.ref("org.jooq.DSLContext"));
            String tableName = table.getName().toUpperCase();
            out.tab(2).println("super(dslContext, %s, %s.ID, %s.class);", tableName, tableName, out.ref(pojoClassName));
            out.tab(1).println("}");
            out.println("}");
            closeJavaWriter(out);


        }


//
    }


    public String getTestTargetDirectory() {
        return "src/test/test";
    }


    private void generateRepositoryTest(TableDefinition table) {
        String serviceClassName = createClassName(table, GenType.repository_test);
        String dir = getTestTargetDirectory();
        String packageName = createPackeageName(table, GenType.repository_test);

        JavaWriter out = createJavaWriter(serviceClassName, dir, packageName);
        if (out == null) {
            return;
        }
        String repositoryClassName = out.ref(createPackeageName(table, GenType.repository) + "." + createClassName(table,
                GenType.repository));
        String repositoryClassNameInstance = StringUtils.toCamelCaseLC(repositoryClassName);
        String slf4jLog = out.ref("lombok.extern.slf4j.Slf4j");
        out.println("package %s;", packageName);
        out.println();
        out.printImports();
        out.println();
        printClassJavadoc(out, table);
        out.println("@%s", slf4jLog);
        out.println("public class %s  {", serviceClassName);
        out.println();
        out.tab(1).println("@%s", out.ref("org.springframework.beans.factory.annotation.Autowired"));
        out.tab(1).println("private %s %s;", repositoryClassName, repositoryClassNameInstance);
        out.println("}");
        closeJavaWriter(out);
    }

    private void generateServiceTest(TableDefinition table) {
    }

    private void generateControllerTest(TableDefinition table) {
    }

    private void generateService(TableDefinition table) {

        String serviceClassName = createClassName(table, GenType.service);
        String dir = getTargetDirectory();
        String packageName = createPackeageName(table, GenType.service);

        JavaWriter out = createJavaWriter(serviceClassName, dir, packageName);
        if (out == null) {
            return;
        }
        String repositoryClassName = out.ref(createPackeageName(table, GenType.repository) + "." + createClassName(table,
                GenType.repository));
        String repositoryClassNameInstance = repositoryClassName.substring(0, 1).toLowerCase() + repositoryClassName
                .substring(1);
        String slf4jLog = out.ref("lombok.extern.slf4j.Slf4j");
        String service = out.ref("org.springframework.stereotype.Service");
        out.println("package %s;", packageName);
        out.println();
        out.printImports();
        out.println();
        printClassJavadoc(out, table);
        out.println("@%s", slf4jLog);
        out.println("@%s", service);
        out.println("public class %s  {", serviceClassName);
        out.println();
        out.tab(1).println("@%s", out.ref("org.springframework.beans.factory.annotation.Autowired"));
        out.tab(1).println("private %s %s;", repositoryClassName, repositoryClassNameInstance);
        out.println();
        // TODO: 2018/1/10  生成 curd 
//        out.println();
//        out.println();
//        out.tab(1).println(" ");


        out.println("}");
        closeJavaWriter(out);

    }

    private void generateController(TableDefinition table) {
        String modelName = StringUtils.toCamelCaseLC(table.getOutputName()
                                                          .replace(' ', '_')
                                                          .replace('-', '_')
                                                          .replace('.', '_'));

        String controllerClassName = createClassName(table, GenType.controller);
        String dir = getTargetDirectory();
        String packageName = createPackeageName(table, GenType.controller);

        JavaWriter out = createJavaWriter(controllerClassName, dir, packageName);
        if (out == null) {
            return;
        }

        String serviceClassName = out.ref(createPackeageName(table, GenType.service) + "." + createClassName(table,
                GenType.service));
        String serviceClassNameInstance = serviceClassName.substring(0, 1).toLowerCase() + serviceClassName
                .substring(1);
        String slf4jLog = out.ref("lombok.extern.slf4j.Slf4j");
        String restController = out.ref("org.springframework.web.bind.annotation.RestController");
        String api = out.ref("io.swagger.annotations.Api");
        String mapping = out.ref("org.springframework.web.bind.annotation.RequestMapping");

        out.println("package %s;", packageName);
        out.println();
        out.printImports();
        out.println();
        printClassJavadoc(out, table);
        out.println("@%s", slf4jLog);
        out.println("@%s", restController);
        out.println("@%s(\"/%s\")", mapping, modelName);
        out.println("@%s(value = \"%s\", description = \"%s\")", api, modelName, table.getComment());
        out.println("public class %s  {", controllerClassName);
        out.println();
        out.tab(1).println("@%s", out.ref("org.springframework.beans.factory.annotation.Autowired"));
        out.tab(1).println("private %s %s;", serviceClassName, serviceClassNameInstance);
        // TODO: 2018/1/10  生成 curd
        out.println("}");
        closeJavaWriter(out);
    }


    private String createClassName(TableDefinition table, GenType genType) {
        String modelName = StringUtils.toCamelCase(table.getOutputName()
                                                        .replace(' ', '_')
                                                        .replace('-', '_')
                                                        .replace('.', '_'));
        StringBuilder result = new StringBuilder();
        result.append(modelName);
        switch (genType) {
            case repository:
                result.append("Repository");
                break;
            case service:
                result.append("Service");
                break;
            case controller:
                result.append("Controller");
                break;
            case service_test:
                result.append("ServiceTest");
                break;
            case controller_test:
                result.append("ControllerTest");
                break;
            case repository_test:
                result.append("RepositoryTest");
                break;
            default:
                getStrategy().getJavaPackageName(table, GeneratorStrategy.Mode.DEFAULT);
        }
        return result.toString();
    }

    private String createPackeageName(TableDefinition table, GenType genType) {
        String result = Config.PACK_NAME;
        String webResult = Config.CONTROLLER_PACK_NAME;
        switch (genType) {
            case repository:
                result = result + "repository";
                break;
            case service:
                result = result + "service";
                break;
            case controller:
                result = webResult + "controller";
                break;
            case service_test:
                result = result + "service";
                break;
            case controller_test:
                result = webResult + "controller";
                break;
            case repository_test:
                result = result + "repository";
                break;
            default:
                result = getStrategy().getJavaPackageName(table, GeneratorStrategy.Mode.DEFAULT);
        }
        return result.toString();
    }


    enum GenType {
        service, controller, repository, service_test, controller_test, repository_test
    }


    private JavaWriter createJavaWriter(String className, String dir, String packageName) {
        String pkg = packageName.replaceAll("\\.", "/");
        String fileName = className;
        File file = new File(dir + "/" + pkg, fileName + ".java");
        if (file.exists()) {
            return null;
        }
        JavaWriter out = newJavaWriter(file);
        log.info("Generating   ", out.file().getName());
        return out;
    }

//
//    protected void generatePojo(TableDefinition table, JavaWriter out) {
//        generatePojo0(table, out);
//    }

    private final void generatePojo0(Definition tableOrUDT, JavaWriter out) {
        String className = getStrategy().getJavaClassName(tableOrUDT, GeneratorStrategy.Mode.POJO);
        String pkg = getStrategy().getJavaPackageName(tableOrUDT, GeneratorStrategy.Mode.POJO).replaceAll("\\.", "/");
        String fileName = className;
        String dir = getTargetDirectory();
        File file = new File(dir + "/" + pkg, fileName + ".java");
        if (file.exists()) {
            try {
                String javaCode = FileUtils.readFileToString(file);
                JavaUnit unit = Roaster.parseUnit(javaCode);
                JavaClassSource myClass = unit.getGoverningType();
                List<FieldSource<JavaClassSource>> fields = myClass.getFields();
                Set<String> set = buildDefaultSet();
                for (FieldSource fieldSource : fields) {
                    set.add(fieldSource.getName());
                }
                int maxLength = 0;
                for (TypedElementDefinition<?> column : getTypedElements(tableOrUDT)) {
                    maxLength = Math.max(maxLength, out.ref(getJavaType(column.getType(), GeneratorStrategy.Mode.POJO))
                                                       .length());
                }
                boolean isWrite = false;
                for (TypedElementDefinition<?> column : getTypedElements(tableOrUDT)) {
                    String javaMemberName = getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO);
                    if (!set.contains(javaMemberName)) {
                        isWrite = true;
                        String ref = getJavaType(column.getType(), GeneratorStrategy.Mode.POJO);
                        String rightPad = StringUtils.rightPad(out.ref(getJavaType(column.getType(), GeneratorStrategy.Mode.POJO)),
                                maxLength);
                        log.debug("");
                        String annotation = " @ApiModelProperty(value = \"" + column.getComment() + "\")";
                        myClass.addImport(ref);
                        myClass.addField(annotation + " protected " + rightPad + " " + javaMemberName);
                    }
                }
                if (isWrite) {
                    FileUtils.writeStringToFile(file, myClass.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createNewApiPojo(file, tableOrUDT, out, className);
        }

    }

    private void createNewApiPojo(File file, Definition tableOrUDT, JavaWriter out, String className) {


        final String superName = getStrategy().getJavaClassExtends(tableOrUDT, GeneratorStrategy.Mode.POJO);
        String fullJavaClassName = getStrategy().getFullJavaClassName(tableOrUDT, GeneratorStrategy.Mode.POJO);
        JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage(COM_CHANGFU_RISK_POJO);
        javaClass.setName(className);
        javaClass.setPublic();
        javaClass.setSuperType(superName);
        javaClass.addImport("io.swagger.annotations.ApiModel");
        javaClass.addImport("io.swagger.annotations.ApiModelProperty");
        javaClass.addImport("lombok.Data");
        javaClass.addImport("lombok.AllArgsConstructor");
        javaClass.addImport("lombok.NoArgsConstructor");
        javaClass.addImport("lombok.EqualsAndHashCode");
        String comment = escapeEntities(tableOrUDT.getComment());
        javaClass.addAnnotation(" io.swagger.annotations.ApiModel")
                 .setLiteralValue("value", "\"" + className + "\"")
                 .setLiteralValue("description", "\"" + comment + "\"");
        javaClass.addAnnotation("lombok.Data");
        javaClass.addAnnotation(" lombok.AllArgsConstructor");
        javaClass.addAnnotation(" lombok.NoArgsConstructor");
        javaClass.addAnnotation(" lombok.EqualsAndHashCode");
        int maxLength = 0;
        Set<String> set = buildDefaultSet();
        for (TypedElementDefinition<?> column : getTypedElements(tableOrUDT)) {
            maxLength = Math.max(maxLength, out.ref(getJavaType(column.getType(), GeneratorStrategy.Mode.POJO))
                                               .length());
        }
        for (TypedElementDefinition<?> column : getTypedElements(tableOrUDT)) {
            String javaMemberName = getStrategy().getJavaMemberName(column, GeneratorStrategy.Mode.POJO);
            if (!set.contains(javaMemberName)) {
                String ref = getJavaType(column.getType(), GeneratorStrategy.Mode.POJO);
                String rightPad = StringUtils.rightPad(out.ref(getJavaType(column.getType(), GeneratorStrategy.Mode.POJO)),
                        maxLength);
                String annotation = " @ApiModelProperty(value = \"" + column.getComment() + "\")";
                javaClass.addImport(ref);
                javaClass.addField(annotation + " protected " + rightPad + " " + javaMemberName);
            }
        }
        try {
            FileUtils.writeStringToFile(file, javaClass.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Set<String> buildDefaultSet() {
        Set<String> set = Sets.newSet();
        set.add("createDate");
        set.add("updateDate");
        set.add("delFlag");
        set.add("version");
        return set;
    }

    private static final <T> List<T> list(T... objects) {
        List<T> result = new ArrayList<T>();

        if (objects != null)
            for (T object : objects) {
                if (object != null && !"".equals(object))
                    result.add(object);
            }

        return result;
    }

    private List<? extends TypedElementDefinition<? extends Definition>> getTypedElements(Definition definition) {
        if (definition instanceof TableDefinition) {
            return ((TableDefinition) definition).getColumns();
        } else if (definition instanceof UDTDefinition) {
            return ((UDTDefinition) definition).getAttributes();
        } else if (definition instanceof RoutineDefinition) {
            return ((RoutineDefinition) definition).getAllParameters();
        } else {
            throw new IllegalArgumentException("Unsupported type : " + definition);
        }
    }

    @SuppressWarnings("unchecked")
    private static final <T> List<T> list(T first, List<T> remaining) {
        List<T> result = new ArrayList<T>();

        result.addAll(list(first));
        result.addAll(remaining);

        return result;
    }

    private static final <T> List<T> first(Collection<T> objects) {
        List<T> result = new ArrayList<T>();

        if (objects != null) {
            for (T object : objects) {
                result.add(object);
                break;
            }
        }

        return result;
    }

    private static final <T> List<T> remaining(Collection<T> objects) {
        List<T> result = new ArrayList<T>();

        if (objects != null) {
            result.addAll(objects);

            if (result.size() > 0)
                result.remove(0);
        }

        return result;
    }


}
