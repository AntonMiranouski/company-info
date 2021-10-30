package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.CompanyRequest;
import anton.miranouski.company_info.dto.response.CompanyResponse;
import anton.miranouski.company_info.model.Company;
import anton.miranouski.company_info.service.CompanyService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        final List<Company> companies = companyService.findAll();
        final List<CompanyResponse> companyResponseList = companies.stream()
                .map(company -> mapper.map(company, CompanyResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(companyResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Long id) {
        final Company company = companyService.findById(id);
        final CompanyResponse companyResponse = mapper.map(company, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> save(@RequestBody CompanyRequest companyRequest) {
        final Company company = mapper.map(companyRequest, Company.class);
        companyService.save(company);
        final CompanyResponse companyResponse = mapper.map(company, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CompanyResponse> update(@RequestBody CompanyRequest companyRequest) {
        final Company company = mapper.map(companyRequest, Company.class);
        companyService.update(company);
        final CompanyResponse companyResponse = mapper.map(company, CompanyResponse.class);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}
