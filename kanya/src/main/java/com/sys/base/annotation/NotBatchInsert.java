package com.sys.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * batch insert时， 声明后此字段不参与 插入DB。 如id, createtime,updatetime 不参与插入，由DB生成默认值
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface NotBatchInsert {

}
