package com.irmasantos.apicdc.site.continuapagamento;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { CpfCnpjValidator.class })
@Target({ FIELD })
@Retention(RUNTIME)
public @interface CpfCnpj {
	String message() default "{com.irmasantos.apicdc.CpfCnpj.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
