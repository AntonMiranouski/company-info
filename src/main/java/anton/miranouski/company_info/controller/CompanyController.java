package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.CompanyRequest;
import anton.miranouski.company_info.dto.response.CompanyResponse;
import anton.miranouski.company_info.model.Company;
import anton.miranouski.company_info.service.CompanyService;
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
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final DozerBeanMapper mapper;

    public CompanyController(CompanyService companyService, DozerBeanMapper mapper) {
        this.companyService = companyService;
        this.mapper = mapper;
    }

    /**
     * Gets by page.
     *
     * @param page the page number
     */
    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll(@RequestParam(defaultValue = "0") Integer page) {
        final Page<Company> companies = companyService.findAll(page);
        final List<CompanyResponse> companyResponseList = companies.stream()
                .map(company -> mapper.map(company, CompanyResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(companyResponseList, HttpStatus.OK);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Long id) {
        final Company company = companyService.findById(id);
        final CompanyResponse companyResponse = mapper.map(company, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }

    /**
     * Save entity.
     *
     * @param companyRequest the company to save
     */
    @PostMapping
    public ResponseEntity<CompanyResponse> save(@Valid @RequestBody CompanyRequest companyRequest) {
        final Company company = mapper.map(companyRequest, Company.class);
        companyService.save(company);
        final CompanyResponse companyResponse = mapper.map(company, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }

    /**
     * Update entity.
     *
     * @param companyRequest the company to update
     */
    @PutMapping
    public ResponseEntity<CompanyResponse> update(@Valid @RequestBody CompanyRequest companyRequest) {
        final Company company = mapper.map(companyRequest, Company.class);
        companyService.update(company);
        final CompanyResponse companyResponse = mapper.map(company, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}
