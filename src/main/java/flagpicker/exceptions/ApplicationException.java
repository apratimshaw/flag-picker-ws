package flagpicker.exceptions;

/**
 * Base class for all application exceptions.
 * 
 * @author apratimshaw
 *
 */
public abstract class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -2945472414080626262L;
	private final String code;

	public ApplicationException(Error err) {
		super(err.getMessage());
		this.code = err.getCode();
	}

	public ApplicationException(Error err, Throwable source) {
		super(err.getMessage(), source);
		this.code = err.getCode();
	}

	public String getCode() {
		return code;
	}

}
