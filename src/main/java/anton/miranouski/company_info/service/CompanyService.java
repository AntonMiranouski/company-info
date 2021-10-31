package anton.miranouski.company_info.service;

import anton.miranouski.company_info.model.Company;
import anton.miranouski.company_info.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private static final String ERROR_MESSAGE_ID = "No Company with this id was found";
    private static final String ERROR_MESSAGE_CEO = "This CEO is already employed";

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Page<Company> findAll(Integer page) {
        return companyRepository.findAll(PageRequest.of(page, 10, Sort.Direction.ASC, "id"));
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE_ID));
    }

    /**
     * Save with checking if the ceo is provided he shouldn't have the company
     *
     * @param company the company
     */
    public void save(Company company) {
        if (company.getCeo() != null && companyRepository.findByCeo_Id(company.getCeo().getId()).size() > 0) {
            throw new RuntimeException(ERROR_MESSAGE_CEO);
        } else {
            company.setId(null);
            companyRepository.saveAndFlush(company);
        }
    }

    public void update(Company company) {
        companyRepository.saveAndFlush(company);
    }

    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
