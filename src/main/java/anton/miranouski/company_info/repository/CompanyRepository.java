package anton.miranouski.company_info.repository;

import anton.miranouski.company_info.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    /**
     * Find companies by ceo id.
     *
     * @param id the ceo id
     * @return the list of companies
     */
    List<Company> findByCeo_Id(Long id);
}
