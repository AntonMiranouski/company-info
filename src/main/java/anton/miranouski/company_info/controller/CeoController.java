package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.CeoRequest;
import anton.miranouski.company_info.dto.response.CeoResponse;
import anton.miranouski.company_info.model.Ceo;
import anton.miranouski.company_info.service.CeoService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ceos")
public class CeoController {

    private final CeoService ceoService;
    private final DozerBeanMapper mapper;

    public CeoController(CeoService ceoService, DozerBeanMapper mapper) {
        this.ceoService = ceoService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CeoResponse>> getAll() {
        final List<Ceo> ceos = ceoService.findAll();
        final List<CeoResponse> ceoResponseList = ceos.stream()
                .map(ceo -> mapper.map(ceo, CeoResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(ceoResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CeoResponse> getById(@PathVariable Long id) {
        final Ceo ceo = ceoService.findById(id);
        final CeoResponse ceoResponse = mapper.map(ceo, CeoResponse.class);
        return new ResponseEntity<>(ceoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CeoResponse> save(@RequestBody CeoRequest ceoRequest) {
        final Ceo ceo = mapper.map(ceoRequest, Ceo.class);
        ceoService.save(ceo);
        final CeoResponse ceoResponse = mapper.map(ceo, CeoResponse.class);
        return new ResponseEntity<>(ceoResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CeoResponse> update(@RequestBody CeoRequest ceoRequest) {
        final Ceo ceo = mapper.map(ceoRequest, Ceo.class);
        ceoService.update(ceo);
        final CeoResponse ceoResponse = mapper.map(ceo, CeoResponse.class);
        return new ResponseEntity<>(ceoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        ceoService.deleteById(id);
    }
}
