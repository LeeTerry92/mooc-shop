package com.imooc.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

public class Stu {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     *
     */
    @Column(name = "name")
    private String name;

    /**
     *
     */
    @Column(name = "age")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
