package anton.miranouski.company_info.dto.response;

import anton.miranouski.company_info.model.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryResponse {

    private Long id;

    private String name;

    private List<Company> companies;
}
