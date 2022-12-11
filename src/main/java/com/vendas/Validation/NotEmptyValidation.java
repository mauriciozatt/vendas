package com.vendas.Validation;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*Criando validação personalizada
 * 1-  cria a classe herdando de ConstraintValidator
 * 2 - reimplemento os métodos isValid  e Inicialize da clase pai 1 recendo como parâmetro o meu @validation criado e o tipo de dado que irá 
 * validar, nesse caso uma lista, já o segundo recebendo apenas o validation criado....
 * */

public class NotEmptyValidation implements ConstraintValidator<NotEmptyList, List> {

	@Override
	public boolean isValid(List lista, ConstraintValidatorContext constraintValidatorContext) {
		return lista != null && !lista.isEmpty();
	};

	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		/// Aqui posso pegar valores definidos na chamada da minha validation...

	};

}
