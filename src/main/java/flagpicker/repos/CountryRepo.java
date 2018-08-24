package flagpicker.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import flagpicker.entities.Country;

/**
 * CRUD for the Country entity.
 * 
 * @author apratimshaw
 *
 */
public interface CountryRepo extends CrudRepository<Country, Long> {

	public Country findByNameIgnoreCase(String countryName);

	public List<Country> findByContinentNameIgnoreCase(String countryName);

}
