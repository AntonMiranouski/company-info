package anton.miranouski.company_info.service;

import anton.miranouski.company_info.model.Sphere;
import anton.miranouski.company_info.repository.SphereRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SphereService {

    private static final String ERROR_MESSAGE = "No Country with this id was found";

    private final SphereRepository sphereRepository;

    public SphereService(SphereRepository sphereRepository) {
        this.sphereRepository = sphereRepository;
    }

    public List<Sphere> findAll() {
        return sphereRepository.findAll();
    }

    public Sphere findById(Long id) {
        return sphereRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Sphere sphere) {
        sphereRepository.saveAndFlush(sphere);
    }

    public void update(Sphere sphere) {
        sphereRepository.saveAndFlush(sphere);
    }

    public void deleteById(Long id) {
        sphereRepository.deleteById(id);
    }
}
