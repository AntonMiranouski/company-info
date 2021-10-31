package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.SphereRequest;
import anton.miranouski.company_info.dto.response.SphereResponse;
import anton.miranouski.company_info.model.Sphere;
import anton.miranouski.company_info.service.SphereService;
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
@RequestMapping("/spheres")
public class SphereController {

    private final SphereService sphereService;
    private final DozerBeanMapper mapper;

    public SphereController(SphereService sphereService, DozerBeanMapper mapper) {
        this.sphereService = sphereService;
        this.mapper = mapper;
    }

    /**
     * Gets by page.
     *
     * @param page the page number
     */
    @GetMapping
    public ResponseEntity<List<SphereResponse>> getAll(@RequestParam Integer page) {
        final Page<Sphere> spheres = sphereService.findAll(page);
        final List<SphereResponse> sphereResponseList = spheres.stream()
                .map(sphere -> mapper.map(sphere, SphereResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sphereResponseList, HttpStatus.OK);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     */
    @GetMapping("/{id}")
    public ResponseEntity<SphereResponse> getById(@PathVariable Long id) {
        final Sphere sphere = sphereService.findById(id);
        final SphereResponse sphereResponse = mapper.map(sphere, SphereResponse.class);
        return new ResponseEntity<>(sphereResponse, HttpStatus.OK);
    }

    /**
     * Save entity.
     *
     * @param sphereRequest the sphere to save
     */
    @PostMapping
    public ResponseEntity<SphereResponse> save(@Valid @RequestBody SphereRequest sphereRequest) {
        final Sphere sphere = mapper.map(sphereRequest, Sphere.class);
        sphereService.save(sphere);
        final SphereResponse sphereResponse = mapper.map(sphere, SphereResponse.class);
        return new ResponseEntity<>(sphereResponse, HttpStatus.OK);
    }

    /**
     * Update entity.
     *
     * @param sphereRequest the sphere to update
     */
    @PutMapping
    public ResponseEntity<SphereResponse> update(@Valid @RequestBody SphereRequest sphereRequest) {
        final Sphere sphere = mapper.map(sphereRequest, Sphere.class);
        sphereService.update(sphere);
        final SphereResponse sphereResponse = mapper.map(sphere, SphereResponse.class);
        return new ResponseEntity<>(sphereResponse, HttpStatus.OK);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        sphereService.deleteById(id);
    }
}
