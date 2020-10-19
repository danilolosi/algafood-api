package com.danilolosi.algafoodapi.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>{

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;

	@Override
	public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
		this.valorField = constraintAnnotation.valorField();
		this.descricaoField = constraintAnnotation.descricaoField();
		this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
	}
	

	@Override
	public boolean isValid(Object objetoParaValidar, ConstraintValidatorContext context) {
		boolean valido = true;
		
		try {
			BigDecimal valor = (BigDecimal) BeanUtils
					.getPropertyDescriptor(objetoParaValidar.getClass(), valorField)
					.getReadMethod().invoke(objetoParaValidar);
			
			String descricao = (String) BeanUtils
					.getPropertyDescriptor(objetoParaValidar.getClass(), descricaoField)
					.getReadMethod().invoke(objetoParaValidar);
			
			if( valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
				valido = descricao.toLowerCase().contains(descricaoObrigatoria.toLowerCase());
			}
		
			return valido;
			
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}

}
