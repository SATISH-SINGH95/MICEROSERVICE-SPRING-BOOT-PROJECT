package com.user.constants;

public enum SortingFieldEnum{

    NAME("name"),
    COMPANY_NAME("companyName"),
    EMPLOYEE_CODE("empCode");

    private String value;

    private SortingFieldEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
