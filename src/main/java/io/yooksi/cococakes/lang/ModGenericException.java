package io.yooksi.cococakes.lang;

import io.yooksi.cococakes.CocoCakes;

public class ModGenericException extends Exception {

	public ModGenericException(String message) {
		super(formatMessage(message));
	}

	@SuppressWarnings("unused")
	public ModGenericException(String message, Throwable cause) {
		super(formatMessage(message), cause);
	}

	private static String formatMessage(String message) {
		return String.format("Exception occurred in %s mod: %s", CocoCakes.MODID, message);
	}
}
