package anton.miranouski.company_info.service;

import anton.miranouski.company_info.model.Country;
import anton.miranouski.company_info.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private static final String ERROR_MESSAGE = "No Country with this id was found";

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Page<Country> findAll(Integer page) {
        return countryRepository.findAll(PageRequest.of(page, 10, Sort.Direction.ASC, "id"));
    }

    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Country country) {
        country.setId(null);
        countryRepository.saveAndFlush(country);
    }

    public void update(Country country) {
        countryRepository.saveAndFlush(country);
    }

    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
