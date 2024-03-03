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
import java.util.ArrayList;
import java.util.List;

public enum BiometricType {

    PHOTO(1,"Photo"),
    LT(2,"Left Thumb"),
    LI(3,"Left Index"),
    LM(4,"Left Middle"),
    LR(5,"Left Ring"),
    LL(6,"Left Little"),
    RT(7,"Right Thumb"),
    RI(8,"Right Index"),
    RM(9,"Right Middle"),
    RR(10,"Right Ring"),
    RL(11,"Right Little"),
    NA(12,"NA");

    private final String value;

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
