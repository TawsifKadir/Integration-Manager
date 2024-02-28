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
    PHOTO("Photo"),
    LT("Left Thumb"),
    LI("Left Index"),
    LM("Left Middle"),
    LR("Left Ring"),
    LL("Left Little"),
    RT("Right Thumb"),
    RI("Right Index"),
    RM("Right Middle"),
    RR("Right Ring"),
    RL("Right Little"),
    NA("NA");

    private final String value;

    BiometricType(String value) {
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
}
