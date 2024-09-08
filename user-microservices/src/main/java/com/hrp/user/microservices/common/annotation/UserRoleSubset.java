package com.hrp.user.microservices.common.annotation;

import com.hrp.user.microservices.common.validator.UserRoleSubsetValidator;
import com.hrp.user.microservices.user.domain.UserRole;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserRoleSubsetValidator.class)
public @interface UserRoleSubset {
    UserRole[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
