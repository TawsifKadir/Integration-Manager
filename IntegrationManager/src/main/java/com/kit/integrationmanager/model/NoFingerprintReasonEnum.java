package com.kit.integrationmanager.model;

/*
public enum NoFingerprintReasonEnum {
    NoFingerprintImpression(1,"No Fingerprint"),
    NoFinger(2,"Missing Finger"),
    NoLeftHand(3,"Missing Left Hand"),
    NoRightHand(4,"Missing Right Hand"),
    NoBothHand(5,"Missing Both Hand"),
    Other(6,"Other (Specify)");

    private int id;
    private String txt;

    private NoFingerprintReasonEnum(int id, String txt){
        this.id = id;
        this.txt = txt;
    }

}
*/
import com.kit.integrationmanager.listener.EnumListener;

import java.util.ArrayList;
import java.util.List;

public enum NoFingerprintReasonEnum implements EnumListener {
    SELECT(0, "Select Reason"),
    NoFingerprintImpression(1, "NoFingerprintImpression"),
    NoFinger(2, "NoFinger"),
    NoLeftHand(3, "NoLeftHand"),
    NoRightHand(4, "NoRightHand"),
    NoBothHand(5, "NoBothHand"),
    Other(6, "Other");

    private final int id;
    private final String value;

    NoFingerprintReasonEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String getDisplayValue() {
        return this.value;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return ordinal() + ": " + name() + ", " + value + ", " + id;
    }

    public static String[] getArray() {
        NoFingerprintReasonEnum[] values = NoFingerprintReasonEnum.values();
        List<String> list = new ArrayList<>();
        for (NoFingerprintReasonEnum reason : values) {
            list.add(reason.getValue());
        }
        return list.toArray(new String[0]);
    }

    public static NoFingerprintReasonEnum find(String value) {

        if(value.equalsIgnoreCase("NoFingerprintImpression")) return NoFingerprintImpression;
        else if(value.equalsIgnoreCase("NoFinger")) return NoFinger;
        else if(value.equalsIgnoreCase("NoLeftHand")) return NoLeftHand;
        else if(value.equalsIgnoreCase("NoRightHand")) return NoRightHand;
        else if(value.equalsIgnoreCase("NoBothHand")) return NoBothHand;
        else if(value.equalsIgnoreCase("Other")) return Other;
        /*for (NoFingerprintReasonEnum reason : NoFingerprintReasonEnum.values()) {
            if (reason.getValue().equals(value)) {
                return reason;
            }
        }*/
        return null;
    }

    public static NoFingerprintReasonEnum getNoFingerprintReasonById(int id){
        NoFingerprintReasonEnum[] noFingerPrintList =  NoFingerprintReasonEnum.values();
        for(NoFingerprintReasonEnum nowType:noFingerPrintList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }
}
