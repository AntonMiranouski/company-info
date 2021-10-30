package anton.miranouski.company_info.repository;

import anton.miranouski.company_info.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
