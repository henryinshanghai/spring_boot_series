package com.henry.chapter08_06.domain;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.henry.chapter08_06.domain.Location;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document //1 把当前类型 映射到 MongoDB中的一个文档
public class Person {
    @Id // 2 声明此属性为 文档的id
    private String id;
    private String name;
    private Integer age;
    @Field("locs") // 3 声明此属性 在文档中的名称是locs - locations属性会以数组的形式存储到当前记录中
    private Collection<Location> locations =  new LinkedHashSet<Location>();


    public Person(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Collection<Location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<Location> locations) {
        this.locations = locations;
    }


}
