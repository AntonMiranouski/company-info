package anton.miranouski.company_info.dto.request;

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

    private CeoRequest ceo;

    private CountryRequest country;

    private List<SphereRequest> spheres;
}
