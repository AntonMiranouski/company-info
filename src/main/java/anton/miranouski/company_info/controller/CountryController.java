package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.CountryRequest;
import anton.miranouski.company_info.dto.response.CountryResponse;
import anton.miranouski.company_info.model.Country;
import anton.miranouski.company_info.service.CountryService;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;
    private final DozerBeanMapper mapper;

    public CountryController(CountryService countryService, DozerBeanMapper mapper) {
        this.countryService = countryService;
        this.mapper = mapper;
    }

    /**
     * Gets by page.
     *
     * @param page the page number
     */
    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAll(@RequestParam Integer page) {
        final Page<Country> countries = countryService.findAll(page);
        final List<CountryResponse> countryResponseList = countries.stream()
                .map(country -> mapper.map(country, CountryResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(countryResponseList, HttpStatus.OK);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getById(@PathVariable Long id) {
        final Country country = countryService.findById(id);
        final CountryResponse countryResponse = mapper.map(country, CountryResponse.class);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    /**
     * Save entity.
     *
     * @param countryRequest the country to save
     */
    @PostMapping
    public ResponseEntity<CountryResponse> save(@Valid @RequestBody CountryRequest countryRequest) {
        final Country country = mapper.map(countryRequest, Country.class);
        countryService.save(country);
        final CountryResponse countryResponse = mapper.map(country, CountryResponse.class);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    /**
     * Update entity.
     *
     * @param countryRequest the country to update
     */
    @PutMapping
    public ResponseEntity<CountryResponse> update(@Valid @RequestBody CountryRequest countryRequest) {
        final Country country = mapper.map(countryRequest, Country.class);
        countryService.update(country);
        final CountryResponse countryResponse = mapper.map(country, CountryResponse.class);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
