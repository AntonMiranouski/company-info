package anton.miranouski.company_info.controller;

import anton.miranouski.company_info.dto.request.SphereRequest;
import anton.miranouski.company_info.dto.response.SphereResponse;
import anton.miranouski.company_info.model.Sphere;
import anton.miranouski.company_info.service.SphereService;
import org.dozer.DozerBeanMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<SphereResponse>> getAll() {
        final List<Sphere> spheres = sphereService.findAll();
        final List<SphereResponse> sphereResponseList = spheres.stream()
                .map(sphere -> mapper.map(sphere, SphereResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sphereResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SphereResponse> getById(@PathVariable Long id) {
        final Sphere sphere = sphereService.findById(id);
        final SphereResponse sphereResponse = mapper.map(sphere, SphereResponse.class);
        return new ResponseEntity<>(sphereResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SphereResponse> save(@RequestBody SphereRequest sphereRequest) {
        final Sphere sphere = mapper.map(sphereRequest, Sphere.class);
        sphereService.save(sphere);
        final SphereResponse sphereResponse = mapper.map(sphere, SphereResponse.class);
        return new ResponseEntity<>(sphereResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SphereResponse> update(@RequestBody SphereRequest sphereRequest) {
        final Sphere sphere = mapper.map(sphereRequest, Sphere.class);
        sphereService.update(sphere);
        final SphereResponse sphereResponse = mapper.map(sphere, SphereResponse.class);
        return new ResponseEntity<>(sphereResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        sphereService.deleteById(id);
    }
}
