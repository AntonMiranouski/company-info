package anton.miranouski.company_info.dto.request;

import anton.miranouski.company_info.model.Company;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class SphereRequest {

    private Long id;

    @NotBlank
    private String name;

    private List<Company> companies;
}
