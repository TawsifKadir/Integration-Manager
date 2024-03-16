package com.kit.integrationmanager.model;

import lombok.Data;

import java.util.List;

@Data
public class Beneficiary {

    private String applicationId;
    private String respondentFirstName;
    private String respondentMiddleName;
    private String respondentLastName;
    private String respondentNickName;
    private String spouseFirstName;
    private String spouseMiddleName;
    private String spouseLastName;
    private String spouseNickName;
    private RelationshipEnum relationshipWithHouseholdHead;
    private String relationshipOther;
    private Integer respondentAge;
    private GenderEnum respondentGender;
    private MaritalStatusEnum respondentMaritalStatus;
    private LegalStatusEnum respondentLegalStatus;
    private DocumentTypeEnum documentType;
    private String documentTypeOther;
    private String respondentId;
    private String respondentPhoneNo;
    private IncomeSourceEnum householdIncomeSource;
    private String incomeSourceOther;
    private Integer householdMonthlyAvgIncome;
    private CurrencyEnum currency;
    private SelectionCriteriaEnum selectionCriteria;
    private List<SelectionReasonEnum> selectionReason;
    private Address address;
    private Location location;
    private Integer householdSize;

    private HouseholdMember householdMember2;
    private HouseholdMember householdMember5;
    private HouseholdMember householdMember17;
    private HouseholdMember householdMember35;
    private HouseholdMember householdMember64;
    private HouseholdMember householdMember65;
    private Boolean isReadWrite;
    private Integer memberReadWrite;
    private Boolean isOtherMemberPerticipating;

    private NonPerticipationReasonEnum notPerticipationReason;
    private String notPerticipationOtherReason;
    private List<Nominee> nominees;

    private List<Biometric> biometrics;

    private AlternatePayee alternatePayee1;
    private AlternatePayee alternatePayee2;

    private Long createdBy;

}
