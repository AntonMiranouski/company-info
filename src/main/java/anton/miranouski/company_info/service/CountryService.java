package anton.miranouski.company_info.service;

import anton.miranouski.company_info.model.Country;
import anton.miranouski.company_info.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final String ERROR_MESSAGE = "No Country with this id was found";

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Country country) {
        countryRepository.saveAndFlush(country);
    }

    public void update(Country country) {
        countryRepository.saveAndFlush(country);
    }

    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
