package com.vendas.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyValidation.class)
public @interface NotEmptyList {

	
	// minha @Validation, precisa de 3 métodos para funcionar.....
	String message() default "A Lista não pode ser vazia";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
