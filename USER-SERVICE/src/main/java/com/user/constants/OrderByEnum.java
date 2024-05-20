package com.user.constants;

public enum OrderByEnum {

    ASCENDING(0),
    DESCENDING(1);

    private int value;

    private OrderByEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
