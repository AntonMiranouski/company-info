package anton.miranouski.company_info.dto.response;

import anton.miranouski.company_info.model.Ceo;
import anton.miranouski.company_info.model.Country;
import anton.miranouski.company_info.model.Sphere;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyResponse {

    private Long id;

    private String name;

    private Ceo ceo;

    private Country country;

    private List<Sphere> spheres;
}
