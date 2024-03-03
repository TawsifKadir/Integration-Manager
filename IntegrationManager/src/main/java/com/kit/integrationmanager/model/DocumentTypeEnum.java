package com.kit.integrationmanager.model;

public enum DocumentTypeEnum {
    PASSPORT(1,"Passport","Passport"),
    NATIONAL_ID(2,"National Id","National Id"),
    OTHER(3,"Other",""),
    NONE(4,"None","None");

    private int id;
    private String value;
    private String docTypeTxt;
    DocumentTypeEnum(int id,String value,String docTypeTxt)
    {
        this.id = id;
        this.value = value;
        this.docTypeTxt = docTypeTxt;
    }


    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDocTypeTxt() {
        return docTypeTxt;
    }

    public void setDocTypeTxt(String docTypeText) {
        this.docTypeTxt = docTypeText;
    }

    public static String[] getArray() {
        DocumentTypeEnum[] values = DocumentTypeEnum.values();
        String[] array = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            array[i] = values[i].getValue();
        }
        return array;
    }

    public static DocumentTypeEnum find(String value) {
        for (DocumentTypeEnum document : DocumentTypeEnum.values()) {
            if (document.getValue().equals(value)) {
                return document;
            }
        }
        return null;
    }

    public static DocumentTypeEnum getDocumentTypeById(int id){
        DocumentTypeEnum[] documentTypeList =  DocumentTypeEnum.values();
        for(DocumentTypeEnum nowType:documentTypeList){
            if(nowType.getId()==id){
                return nowType;
            }
        }
        return null;
    }

}
