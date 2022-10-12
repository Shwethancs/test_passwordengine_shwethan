package com.example.passwordengine;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraint, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		if((password == null) || password.isEmpty()){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(
					"Password is null or empty.")
					.addConstraintViolation();
			return false;
		}

		boolean hasLower = false, hasUpper = false, hasDigit = false, hasProperLength = false;

		if (password.length() > 8) {
			hasProperLength = true;
		}

		for (char i : password.toCharArray()) {
			if (Character.isLowerCase(i))
				hasLower = true;
			if (Character.isUpperCase(i))
				hasUpper = true;
			if (Character.isDigit(i))
				hasDigit = true;
		}
		if (atLeastThreeConditionsMet(hasProperLength, hasLower, hasUpper, hasDigit, context)) {
			return true;
		} else {
			return false;
		}

	}

	boolean atLeastThreeConditionsMet(boolean hasProperLength, boolean hasLower, boolean hasUpper, boolean hasDigit,
			ConstraintValidatorContext context) {
		if (!hasLower) {
			// Add feature: password is never OK if item 1.d is not true.- password should
			// have one lowercase letter at least
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("Password should have atleast one lowercase character.")
					.addConstraintViolation();
			return false;
		}
		if ((hasProperLength && hasLower && hasUpper) || (hasLower && hasUpper && hasDigit)
				|| (hasUpper && hasDigit && hasProperLength) || (hasDigit && hasProperLength && hasLower)) {
			return true;
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				"Password did not meet the requirements combination of length, uppercase, lowercase, digit in it.")
				.addConstraintViolation();
		return false;

	}
}
