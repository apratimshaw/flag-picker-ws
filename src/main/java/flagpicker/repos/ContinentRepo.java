package flagpicker.repos;

import org.springframework.data.repository.CrudRepository;

import flagpicker.entities.Continent;

/**
 * CRUD for Continent entity.
 * 
 * @author apratimshaw
 *
 */
public interface ContinentRepo extends CrudRepository<Continent, Long> {

	public Continent findByNameIgnoreCase(String continentName);

}
