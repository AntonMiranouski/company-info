package anton.miranouski.company_info.repository;

import anton.miranouski.company_info.model.Sphere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SphereRepository extends JpaRepository<Sphere, Long> {
}
