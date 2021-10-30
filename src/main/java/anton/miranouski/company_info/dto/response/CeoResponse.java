package anton.miranouski.company_info.dto.response;

import anton.miranouski.company_info.model.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CeoResponse {

    private Long id;

    private String name;

    private Company company;
}
