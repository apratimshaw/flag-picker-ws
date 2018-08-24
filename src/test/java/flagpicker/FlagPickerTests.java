package flagpicker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import flagpicker.entities.Country;
import flagpicker.exceptions.BadRequest;
import flagpicker.services.CountryService;

/**
 * Test class to verify the search criteria. Testing under the skin (i.e.
 * Service class).
 * 
 * @author apratimshaw
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlagPickerTests {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	CountryService countryService;

	@Test
	public void shouldFilterByValidCountry() {
		// exact
		List<Country> countries = countryService.getFiltered(Optional.of("India"), Optional.empty());
		assertValidityOfCountrySearch("India", countries);
		// all caps
		countryService.getFiltered(Optional.of("INDIA"), Optional.empty());
		assertValidityOfCountrySearch("India", countries);
		// lower case
		countryService.getFiltered(Optional.of("india"), Optional.empty());
		assertValidityOfCountrySearch("India", countries);
		// padded
		countryService.getFiltered(Optional.of("  INDIA  "), Optional.empty());
		assertValidityOfCountrySearch("India", countries);
	}

	private void assertValidityOfCountrySearch(String expected, List<Country> countries) {
		assertNotNull(countries);
		assertThat(countries.size(), is(1));
		assertThat(countries.get(0).getName(), is(expected));
	}

	@Test
	public void shouldRejectInvalidCountry() {
		thrown.expect(BadRequest.class);
		thrown.expect(hasProperty("code", is("FP4001")));
		countryService.getFiltered(Optional.of("Indiaa"), Optional.empty());
	}

	@Test
	public void shouldRejectUnsafeCharsInCountry() {
		thrown.expect(BadRequest.class);
		thrown.expect(hasProperty("code", is("FP4004")));
		countryService.getFiltered(Optional.of("<Indiaa$&"), Optional.empty());
	}

	@Test
	public void shouldFilterByValidContinent() {
		// exact
		List<Country> countries = countryService.getFiltered(Optional.empty(), Optional.of("Asia"));
		assertValidityOfContinentSearch("Asia", countries);
		// all caps
		countries = countryService.getFiltered(Optional.empty(), Optional.of("ASIA"));
		assertValidityOfContinentSearch("Asia", countries);
		// lower case
		countries = countryService.getFiltered(Optional.empty(), Optional.of("asia"));
		assertValidityOfContinentSearch("Asia", countries);
		// padded
		countries = countryService.getFiltered(Optional.empty(), Optional.of(" ASIA "));
		assertValidityOfContinentSearch("Asia", countries);
	}

	private void assertValidityOfContinentSearch(String expected, List<Country> countries) {
		assertNotNull(countries);
		assertFalse(countries.isEmpty());
		countries.stream().forEach(c -> assertThat(c.getContinent().getName(), is(expected)));

	}

	@Test
	public void shouldRejectInvalidContinent() {
		thrown.expect(BadRequest.class);
		thrown.expect(hasProperty("code", is("FP4002")));
		countryService.getFiltered(Optional.empty(), Optional.of("AASIAA"));
	}
	
	@Test
	public void shouldRejectUnsafeCharsInContinent() {
		thrown.expect(BadRequest.class);
		thrown.expect(hasProperty("code", is("FP4004")));
		countryService.getFiltered(Optional.empty(), Optional.of("<Asia$&"));
	}

	@Test
	public void shouldRejectInvalidCombination() {
		thrown.expect(BadRequest.class);
		thrown.expect(hasProperty("code", is("FP4003")));
		countryService.getFiltered(Optional.of("India"), Optional.of("Africa"));
	}
}
