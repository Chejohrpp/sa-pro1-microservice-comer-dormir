package com.hrp.user.microservices.common.validator;

import com.hrp.user.microservices.common.annotation.UserRoleSubset;
import com.hrp.user.microservices.user.domain.UserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class UserRoleSubsetValidator implements ConstraintValidator<UserRoleSubset, UserRole> {
    private UserRole[] subset;

    @Override
    public void initialize(UserRoleSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(UserRole userRole, ConstraintValidatorContext constraintValidatorContext) {
        return userRole == null || Arrays.asList(subset).contains(userRole);
    }
}