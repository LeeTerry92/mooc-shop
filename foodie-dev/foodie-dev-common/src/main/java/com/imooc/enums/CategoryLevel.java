package com.imooc.enums;

/**
 * @Desc: 分类类型等级 枚举
 */
public enum CategoryLevel {
    FIRST(1, "第一级"),
    SECOND(2, "第二级"),
    THIRD(3, "第三级");

    public final Integer type;
    public final String value;

    CategoryLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
