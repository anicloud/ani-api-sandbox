package com.ani.sunny.api.commons.dto.stub;

/**
 * Created by zhanglina on 18-2-1.
 */
public class StubValueDto {
    String name;
    String value;
    public final Object lock = new Object();
    public String getName() {
        return name;
    }

    public StubValueDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public StubValueDto() {

    }

    public void setName(String name) {

        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
