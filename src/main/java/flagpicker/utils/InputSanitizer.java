package flagpicker.utils;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import flagpicker.exceptions.BadRequest;
import flagpicker.exceptions.Error;

/**
 * A utility class to sanitize inputs.
 * 
 * @author apratimshaw
 *
 */
public class InputSanitizer {

	// use owasp to sanitize; Policy factory is thread-safe
	private static final PolicyFactory DISALLOW_ALL = new HtmlPolicyBuilder().toFactory();

	/**
	 * Trims and sanitizes the input string.
	 */
	public static String sanitize(String input) {
		if (input == null)
			return null;
		String safe = DISALLOW_ALL.sanitize(input.trim());
		if (!safe.equals(input.trim())) {
			throw new BadRequest(Error.UNSAFE_INPUT);
		}
		return safe;
	}

}
