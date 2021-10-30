package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.CountryRequest;
import anton.miranouski.company_info.dto.response.CountryResponse;
import anton.miranouski.company_info.model.Country;
import anton.miranouski.company_info.service.CountryService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAll() {
        final List<Country> countries = countryService.findAll();
        final List<CountryResponse> countryResponseList = countries.stream()
                .map(country -> mapper.map(country, CountryResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(countryResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getById(@PathVariable Long id) {
        final Country country = countryService.findById(id);
        final CountryResponse countryResponse = mapper.map(country, CountryResponse.class);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CountryResponse> save(@RequestBody CountryRequest countryRequest) {
        final Country country = mapper.map(countryRequest, Country.class);
        countryService.save(country);
        final CountryResponse countryResponse = mapper.map(country, CountryResponse.class);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CountryResponse> update(@RequestBody CountryRequest countryRequest) {
        final Country country = mapper.map(countryRequest, Country.class);
        countryService.update(country);
        final CountryResponse countryResponse = mapper.map(country, CountryResponse.class);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        countryService.deleteById(id);
    }
}
