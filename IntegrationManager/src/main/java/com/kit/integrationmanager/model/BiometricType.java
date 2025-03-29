package com.kit.integrationmanager.model;

/*
public enum BiometricType {
    PHOTO,
    LT,
    LI,
    LM,
    LR,
    LL,
    RT,
    RI,
    RM,
    RR,
    RL;
}
*/
import com.kit.integrationmanager.listener.EnumListener;

import java.util.ArrayList;
import java.util.List;

public enum BiometricType implements EnumListener {

    PHOTO(1,"PHOTO"),
    LT(2,"LT"),
    LI(3,"LI"),
    LM(4,"LM"),
    LR(5,"LR"),
    LL(6,"LL"),
    RT(7,"RT"),
    RI(8,"RI"),
    RM(9,"RM"),
    RR(10,"RR"),
    RL(11,"RL"),
    NA(12,"NA");

    private final String value;

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    private final int id;

    BiometricType(int id,String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String[] getArray() {
        BiometricType[] values = BiometricType.values();
        List<String> list = new ArrayList<>();
        for (BiometricType type : values) {
            list.add(type.getValue());
        }
        return list.toArray(new String[0]);
    }

    public static BiometricType find(String value) {
        for (BiometricType type : BiometricType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
    public static BiometricType getBiometricTypeById(int id){
        BiometricType[] biometricTypeList =  BiometricType.values();
        for(BiometricType nowType:biometricTypeList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
