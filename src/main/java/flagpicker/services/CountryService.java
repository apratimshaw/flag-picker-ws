package flagpicker.services;

import static flagpicker.utils.InputSanitizer.sanitize;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flagpicker.entities.Country;
import flagpicker.exceptions.BadRequest;
import flagpicker.exceptions.Error;
import flagpicker.repos.CountryRepo;

/**
 * Business methods for the Country data. This implements the bulk of the logic
 * for the flag picker.
 * 
 * @author apratimshaw
 *
 */
@Service
public class CountryService {

	@Autowired
	CountryRepo countryRepo;

	public List<Country> getAll() {
		return (List<Country>) countryRepo.findAll();
	}

	public List<Country> getFiltered(Optional<String> countryName, Optional<String> continentName) {
		if (countryName.isPresent() && continentName.isPresent()) {// both
			return getOnlyIfValidCombination(sanitize(countryName.get()), sanitize(continentName.get()));
		} else if (!countryName.isPresent() && !continentName.isPresent()) {// neither
			return getAll();
		} else if (countryName.isPresent()) {//only country name
			return listOf(getOnlyIfValidCountry(sanitize(countryName.get())));
		} else {//only continent name
			return getOnlyIfValidContinent(sanitize(continentName.get()));
		}

	}

	public String getFlag(@NotBlank String countryName) {
		return getOnlyIfValidCountry(countryName).getFlag();
	}

	public void save(Country p) {
		countryRepo.save(p);

	}

	public void save(List<Country> countries) {
		countryRepo.saveAll(countries);
	}

	private List<Country> getOnlyIfValidCombination(String countryName, String continentName) {
		Country country = getOnlyIfValidCountry(countryName);
		if (!continentName.equals(country.getContinent().getName()))
			throw new BadRequest(Error.COUNTRY_CONTINENT_COMBO_NOT_FOUND);
		return listOf(country);
	}

	private List<Country> listOf(Country country) {
		List<Country> clist = new ArrayList<>();
		clist.add(country);
		return clist;
	}

	private Country getOnlyIfValidCountry(String countryName) {
		Country country = countryRepo.findByNameIgnoreCase(countryName);
		if (country == null)
			throw new BadRequest(Error.COUNTRY_NOT_FOUND);
		return country;
	}

	private List<Country> getOnlyIfValidContinent(String continentName) {
		List<Country> countries = countryRepo.findByContinentNameIgnoreCase(continentName);
		if (countries == null || countries.isEmpty())
			throw new BadRequest(Error.CONTINENT_NOT_FOUND);
		return countries;
	}

}
