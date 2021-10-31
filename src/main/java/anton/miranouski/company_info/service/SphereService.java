package anton.miranouski.company_info.service;

import anton.miranouski.company_info.model.Sphere;
import anton.miranouski.company_info.repository.SphereRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SphereService {

    private static final String ERROR_MESSAGE = "No Sphere with this id was found";

    private final SphereRepository sphereRepository;

    public SphereService(SphereRepository sphereRepository) {
        this.sphereRepository = sphereRepository;
    }

    public Page<Sphere> findAll(Integer page) {
        return sphereRepository.findAll(PageRequest.of(page, 10, Sort.Direction.ASC, "id"));
    }

    public Sphere findById(Long id) {
        return sphereRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Sphere sphere) {
        sphere.setId(null);
        sphereRepository.saveAndFlush(sphere);
    }

    public void update(Sphere sphere) {
        sphereRepository.saveAndFlush(sphere);
    }

    public void deleteById(Long id) {
        sphereRepository.deleteById(id);
    }
}
