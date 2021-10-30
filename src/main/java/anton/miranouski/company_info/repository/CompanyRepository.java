package anton.miranouski.company_info.repository;

import anton.miranouski.company_info.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
