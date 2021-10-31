package anton.miranouski.company_info.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyResponse {

    private Long id;

    private String name;

    private CeoResponse ceo;

    private CountryResponse country;

    private List<SphereResponse> spheres;
}
