package com.example.countries_data.controller;

import com.example.countries_data.model.Country;
import com.example.countries_data.repository.CountryRepository;
import com.example.countries_data.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryRepository countryRepository;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
        Country country = countryRepository.findByName(name);
        if (country != null) {
            return new ResponseEntity<>(country, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        if (countryService.getCountryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        country.setId(id);
        return ResponseEntity.ok(countryService.saveCountry(country));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        if (countryService.getCountryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        countryService.deleteCountryById(id);
        return ResponseEntity.noContent().build();
    }
}
