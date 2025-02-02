package com.kit.integrationmanager.payload.edit.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiaryEditStatusRequest {
    private List<String> applicationIdList;
}
