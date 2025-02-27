package jooq.custom.generator;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

/**
 * please explain class!
 *
 * @author :Uheejoon
 * @date :2025-02-16 오후 6:20
 */
public class JPrefixGeneratorStrategy extends DefaultGeneratorStrategy {
  @Override
  public String getJavaClassName(Definition definition, Mode mode) {

    if(mode == Mode.DEFAULT) {
      return "J" + super.getJavaClassName(definition, mode);
    }

    return super.getJavaClassName(definition, mode);
  }
}
