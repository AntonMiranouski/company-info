package anton.miranouski.company_info.service;

import anton.miranouski.company_info.model.Company;
import anton.miranouski.company_info.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private static final String ERROR_MESSAGE = "No Company with this id was found";

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Company company) {
        companyRepository.saveAndFlush(company);
    }

    public void update(Company company) {
        companyRepository.saveAndFlush(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
