package anton.miranouski.company_info.dto.request;

import anton.miranouski.company_info.model.Ceo;
import anton.miranouski.company_info.model.Country;
import anton.miranouski.company_info.model.Sphere;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CompanyRequest {

    private Long id;

    @NotBlank
    private String name;

    private Ceo ceo;

    private Country country;

    private List<Sphere> spheres;
}
