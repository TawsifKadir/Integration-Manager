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
import java.util.ArrayList;
import java.util.List;

public enum NoFingerprintReasonEnum {
    SELECT(0, "Select Reason"),
    NoFingerprintImpression(1, "No Fingerprint"),
    NoFinger(2, "Missing Finger"),
    NoLeftHand(3, "Missing Left Hand"),
    NoRightHand(4, "Missing Right Hand"),
    NoBothHand(5, "Missing Both Hand"),
    Other(6, "Other (Specify)");

    private final int id;
    private final String value;

    NoFingerprintReasonEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

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
        for (NoFingerprintReasonEnum reason : NoFingerprintReasonEnum.values()) {
            if (reason.getValue().equals(value)) {
                return reason;
            }
        }
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
