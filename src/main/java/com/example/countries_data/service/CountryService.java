package com.example.countries_data.service;

import com.example.countries_data.model.Country;
import com.example.countries_data.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }

    //public Optional<Country> getCountryByName(String name) { }

    @Scheduled(cron = "0 0 0 * * *", zone = "Europe/Moscow")
    public void updatePopulation() {
        try {
            List<Country> countries = countryRepository.findAll();
            RestTemplate restTemplate = new RestTemplate();
            for (Country country : countries) {
                String apiUrl = "";
                PopulationData populationData = restTemplate.getForObject(apiUrl, PopulationData.class);

                if (populationData != null && populationData.getPopulation() != null) {
                    country.setPopulation(populationData.getPopulation());
                    countryRepository.save(country);
                }
            }
            logger.info("Updating country's population completed successfully.");
        } catch (Exception e) {
            logger.error("Error occurred while updating country's population: " + e.getMessage());
        }
    }

}
