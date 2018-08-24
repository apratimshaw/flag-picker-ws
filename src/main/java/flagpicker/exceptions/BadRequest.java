package flagpicker.exceptions;

/**
 * This class represents exceptions due to bad request from clients.
 * 
 * @author apratimshaw
 *
 */
public class BadRequest extends ApplicationException {

	private static final long serialVersionUID = 1027679935025678888L;

	public BadRequest(Error err) {
		super(err);
	}
}
