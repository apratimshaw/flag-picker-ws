package flagpicker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import flagpicker.entities.Continent;
import flagpicker.services.ContinentService;

/**
 * This Sping Boot Application loads the continents.json into the DB on startup.
 * 
 * @author apratimshaw
 *
 */
@SpringBootApplication
public class FlagPickerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlagPickerApplication.class, args);
	}

	/**
	 * Read JSON and write to DB at startup
	 */
	@Bean
	CommandLineRunner runner(ContinentService continentService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Continent>> typeReference = new TypeReference<List<Continent>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/continents.json");
			try {
				List<Continent> continents = mapper.readValue(inputStream, typeReference);
				continentService.save(continents);
			} catch (IOException e) {
				throw new RuntimeException("Error reading json and writing to db", e);
			}
		};
	}
}
