package com.kit.integrationmanager.payload;

import com.kit.integrationmanager.model.Beneficiary;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BatchRegistrationRequest {
    private List<Beneficiary> beneficiaries;
}
