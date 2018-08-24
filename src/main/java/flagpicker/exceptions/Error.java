package flagpicker.exceptions;
/**
 * Enumeration for all application exceptions.
 *  Please do not auto format; I like the enum values listed vertically.
 * @author apratimshaw
 *
 */
public enum Error {
	COUNTRY_NOT_FOUND("FP4001", "Country not found"),
	CONTINENT_NOT_FOUND("FP4002", "Continent not found"),
	COUNTRY_CONTINENT_COMBO_NOT_FOUND("FP4003", "Country continent combination not found"), 
	UNSAFE_INPUT("FP4004", "nsafe characters found in input"),
	SERVER_ERROR("FP50001", "Server Error");

	private final String code;
	private final String msg;

	private Error(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + msg;
	}

}
