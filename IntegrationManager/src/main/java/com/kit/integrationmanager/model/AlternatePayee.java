package com.kit.integrationmanager.model;

import lombok.Data;

import java.util.List;

@Data
public class AlternatePayee {
    private String payeeFirstName = null;
    private String payeeMiddleName = null;
    private String payeeLastName = null;
    private String payeeNickName = null;
    private Integer payeeAge = null;
    private GenderEnum payeeGender = null;
    private DocumentTypeEnum documentType = null;
    private String documentTypeOther = null;
    private RelationshipEnum relationshipWithHouseholdHead = null;
    private String relationshipOther = null;
    private String nationalId = null;
    private String payeePhoneNo = null;
    private List<Biometric> biometrics = null;
}
