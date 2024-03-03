package com.kit.integrationmanager.model;

public enum BiometricUserType {
    BENEFICIARY(1,"BENEFICIARY","BENEFICIARY"),
    NOMINEE(2,"NOMINEE","NOMINEE"),
    ALTERNATE(3,"ALTERNATE","ALTERNATE");

    private int id;
    private String value;
    private String Txt;

    BiometricUserType(int id, String value, String txt) {
        this.id = id;
        this.value = value;
        Txt = txt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTxt() {
        return Txt;
    }

    public void setTxt(String txt) {
        Txt = txt;
    }

    public static BiometricUserType getBiometricUserTypeById(int id){
        BiometricUserType[] biometricUserTypeList =  BiometricUserType.values();
        for(BiometricUserType nowType:biometricUserTypeList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

    public static BiometricUserType find(String value) {
        for (BiometricUserType type : BiometricUserType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

}
