package common;

import java.util.regex.Pattern;

public class Validator {
	
	public boolean isEmpty(String data) {
		if (data == null || data.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isValidatedData(String data, String regExpression) {
		if (isEmpty(data) || !Pattern.matches(regExpression, data)) {
			return false;
		} else {
			return true;
		}
	}
	
}
