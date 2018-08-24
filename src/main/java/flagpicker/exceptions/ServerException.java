package flagpicker.exceptions;

/**
 * This represents unexpected errors thrown on the server side.
 * 
 * @author apratimshaw
 *
 */
public class ServerException extends ApplicationException {

	private static final long serialVersionUID = -1316444148025075570L;

	public ServerException(Throwable source) {
		super(Error.SERVER_ERROR, source);
	}
}
