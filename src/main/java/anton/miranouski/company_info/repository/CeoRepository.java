package anton.miranouski.company_info.repository;

import anton.miranouski.company_info.model.Ceo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CeoRepository extends JpaRepository<Ceo, Long> {
}
