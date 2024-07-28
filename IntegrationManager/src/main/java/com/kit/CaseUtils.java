package com.kit;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class CaseUtils extends PropertyNamingStrategy.PropertyNamingStrategyBase {
    @Override
    public String translate(String propertyName) {
        return "";
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convertToLowerCase(defaultName);
    }

    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        return convertToLowerCase(defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convertToLowerCase(defaultName);
    }

    private String convertToLowerCase(String name) {
        return name.toLowerCase();
    }

    //

}
