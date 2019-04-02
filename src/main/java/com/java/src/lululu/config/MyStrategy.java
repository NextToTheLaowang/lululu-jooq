package com.java.src.lululu.config;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;
import org.jooq.tools.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.java.src.lululu"})
public class MyStrategy extends DefaultGeneratorStrategy {
  private String tablePrefix = "tb_";

  public String getTablePrefix() {
    return tablePrefix;
  }

  public void setTablePrefix(String tablePrefix) {
    this.tablePrefix = tablePrefix;
  }

  @Override
  public String getJavaClassName(Definition definition, Mode mode) {
    StringBuilder result = new StringBuilder();

    String outputName = definition.getOutputName();
    if (!StringUtils.isBlank(tablePrefix) && outputName.startsWith(tablePrefix)) {
      outputName = outputName.substring(tablePrefix.length());
    }
    result.append(StringUtils.toCamelCase(outputName));

    if (mode == Mode.RECORD) {
      result.append("Record");
    } else if (mode == Mode.DAO) {
      result.append("Dao");
    } else if (mode == Mode.INTERFACE) {
      result.insert(0, "I");
    }
    return result.toString();
  }
}
