package anton.miranouski.company_info.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CeoRequest {

    private Long id;

    @NotBlank
    private String name;
}
