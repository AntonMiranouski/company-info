package anton.miranouski.company_info.service;

import anton.miranouski.company_info.model.Ceo;
import anton.miranouski.company_info.repository.CeoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CeoService {

    private static final String ERROR_MESSAGE = "No CEO with this id was found";

    private final CeoRepository ceoRepository;

    public CeoService(CeoRepository repository) {
        this.ceoRepository = repository;
    }

    public Page<Ceo> findAll(Integer page) {
        return ceoRepository.findAll(PageRequest.of(page, 10, Sort.Direction.ASC, "id"));
    }

    public Ceo findById(Long id) {
        return ceoRepository.findById(id).orElseThrow(() -> new RuntimeException(ERROR_MESSAGE));
    }

    public void save(Ceo ceo) {
        ceo.setId(null);
        ceoRepository.saveAndFlush(ceo);
    }

    public void update(Ceo ceo) {
        ceoRepository.saveAndFlush(ceo);
    }

    public void deleteById(Long id) {
        ceoRepository.deleteById(id);
    }
}
