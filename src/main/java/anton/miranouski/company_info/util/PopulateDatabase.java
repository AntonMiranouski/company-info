package anton.miranouski.company_info.util;

import anton.miranouski.company_info.model.Ceo;
import anton.miranouski.company_info.service.CeoService;
import anton.miranouski.company_info.service.CompanyService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PopulateDatabase {

    private final CeoService ceoService;

    public PopulateDatabase(CeoService ceoService, CompanyService companyService) {
        this.ceoService = ceoService;
    }

    @PostConstruct
    public void populate() {
        for (int i = 1; i < 6; i++) {
            Ceo ceo = new Ceo();
            ceo.setName("John Doe " + i);
            ceoService.save(ceo);
        }
    }
}
