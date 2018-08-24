package flagpicker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flagpicker.entities.Continent;
import flagpicker.repos.ContinentRepo;

/**
 * Business methods for Continent. It is deliberately minimal at this time, and
 * can be readily enhanced to support new requirements, as needed.
 * 
 * @author apratimshaw
 *
 */
@Service
public class ContinentService {

	@Autowired
	ContinentRepo continentRepo;

	public List<Continent> getAll() {
		return (List<Continent>) continentRepo.findAll();
	}

	public Continent getByName(String continentName) {
		return continentRepo.findByNameIgnoreCase(continentName);
	}

	public void save(List<Continent> continents) {
		continentRepo.saveAll(continents);
	}

}
