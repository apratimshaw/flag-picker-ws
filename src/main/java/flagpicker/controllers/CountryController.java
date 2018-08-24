package flagpicker.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import flagpicker.entities.Country;
import flagpicker.services.CountryService;

/**
 * This class is responsible for exposing a minimal but generic RESTful API to
 * retrieve country data.<br/>
 * REST capabilities for PUT, POST and DELETE are not needed at the moment.
 * However, it is straightforward to implement them if needed in the future.
 * 
 * @author apratimshaw
 *
 */
@RestController
public class CountryController {

	@Autowired
	public CountryService countryService;

	/**
	 * This is a generic API that returns a list of countries for a given set of
	 * query params.<br/>
	 * For no query params, all countries and their flags are returned.<br/>
	 * For country param, the specified country data (name and flag) is returned
	 * <br/>
	 * For continent param, all data for countries in the continent is returned
	 * <br/>
	 * 
	 * @param countryName
	 *            query param - name of country
	 * @param continentName
	 *            query param - name of continent
	 * @return
	 */
	@GetMapping("/v1/countries")
	public List<Country> getCountries(@RequestParam(required = false) Optional<String> countryName,
			@RequestParam(required = false) Optional<String> continentName) {
		return countryService.getFiltered(countryName, continentName);
	}

	/**
	 * Get flag for a country - a convenience API.
	 * 
	 * @param countryName
	 *            - name of country (case insensitive)
	 * @return flag - unicode emoji for country flag
	 */
	@GetMapping("/v1/countries/flag")
	public String getFlag(@RequestParam String countryName) {
		return countryService.getFlag(countryName);
	}

}
