package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.CeoRequest;
import anton.miranouski.company_info.dto.response.CeoResponse;
import anton.miranouski.company_info.model.Ceo;
import anton.miranouski.company_info.service.CeoService;
import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    /**
     * Gets by page
     *
     * @param page the page number
     */
    @GetMapping
    public ResponseEntity<List<CeoResponse>> getAll(@RequestParam Integer page) {
        final Page<Ceo> ceos = ceoService.findAll(page);
        final List<CeoResponse> ceoResponseList = ceos.stream()
                .map(ceo -> mapper.map(ceo, CeoResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(ceoResponseList, HttpStatus.OK);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CeoResponse> getById(@PathVariable Long id) {
        final Ceo ceo = ceoService.findById(id);
        final CeoResponse ceoResponse = mapper.map(ceo, CeoResponse.class);
        return new ResponseEntity<>(ceoResponse, HttpStatus.OK);
    }

    /**
     * Save entity.
     *
     * @param ceoRequest the ceo to save
     */
    @PostMapping
    public ResponseEntity<CeoResponse> save(@Valid @RequestBody CeoRequest ceoRequest) {
        final Ceo ceo = mapper.map(ceoRequest, Ceo.class);
        ceoService.save(ceo);
        final CeoResponse ceoResponse = mapper.map(ceo, CeoResponse.class);
        return new ResponseEntity<>(ceoResponse, HttpStatus.OK);
    }

    /**
     * Update entity.
     *
     * @param ceoRequest the ceo to update
     */
    @PutMapping
    public ResponseEntity<CeoResponse> update(@Valid @RequestBody CeoRequest ceoRequest) {
        final Ceo ceo = mapper.map(ceoRequest, Ceo.class);
        ceoService.update(ceo);
        final CeoResponse ceoResponse = mapper.map(ceo, CeoResponse.class);
        return new ResponseEntity<>(ceoResponse, HttpStatus.OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        ceoService.deleteById(id);
    }
}
