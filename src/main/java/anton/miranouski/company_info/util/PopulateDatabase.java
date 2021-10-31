package anton.miranouski.company_info.util;

import anton.miranouski.company_info.model.Ceo;
import anton.miranouski.company_info.model.Company;
import anton.miranouski.company_info.model.Country;
import anton.miranouski.company_info.model.Sphere;
import anton.miranouski.company_info.repository.CeoRepository;
import anton.miranouski.company_info.repository.CompanyRepository;
import anton.miranouski.company_info.repository.CountryRepository;
import anton.miranouski.company_info.repository.SphereRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class PopulateDatabase {

    private final CeoRepository ceoRepository;
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;
    private final SphereRepository sphereRepository;

    private final Random random = new Random();

    private final List<String> firstNames = Arrays.asList(
            "John", "Mark", "Jan", "Helmut", "Budzikid", "Vid", "Bill", "Traniata", "Maximilian", "Luigi", "William"
    );
    private final List<String> lastNames = Arrays.asList(
            "Doe", "Johnson", "Paniatoŭski", "Pac", "Hindenburg", "Hiedyminavič", "Gates", "Cage", "Allegri"
    );

    public PopulateDatabase(
            CeoRepository ceoRepository,
            CompanyRepository companyRepository,
            CountryRepository countryRepository,
            SphereRepository sphereRepository
    ) {
        this.ceoRepository = ceoRepository;
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
        this.sphereRepository = sphereRepository;
    }

    @PostConstruct
    public void populate() {
        createEntities();

        createRelations();
    }

    private void createEntities() {
        List<Ceo> ceos = new ArrayList<>();
        List<Company> companies = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Sphere> spheres = new ArrayList<>();

        for (int i = 1; i < 501; i++) {
            Ceo ceo = new Ceo();
            ceo.setName(
                    firstNames.get(random.nextInt(firstNames.size()))
                            + " "
                            + lastNames.get(random.nextInt(lastNames.size()))
            );
            ceos.add(ceo);

            Company company = new Company();
            company.setName("Company " + i);
            companies.add(company);

            Country country = new Country();
            country.setName("Country " + i);
            countries.add(country);

            Sphere sphere = new Sphere();
            sphere.setName("Sphere " + i);
            spheres.add(sphere);
        }

        ceoRepository.saveAllAndFlush(ceos);
        companyRepository.saveAllAndFlush(companies);
        countryRepository.saveAllAndFlush(countries);
        sphereRepository.saveAllAndFlush(spheres);
    }

    private void createRelations() {
        List<Company> companies = companyRepository.findAll();
        List<Ceo> ceos = ceoRepository.findAll();
        List<Country> countries = countryRepository.findAll();
        List<Sphere> spheres = sphereRepository.findAll();

        for (int i = 0; i < 490; i++) {
            Company company = companies.get(i);
            company.setCeo(ceos.get(i));
            company.setCountry(countries.get(random.nextInt(countries.size())));

            Set<Sphere> sphereSet = new HashSet<>();
            sphereSet.add(spheres.get(random.nextInt(spheres.size())));
            sphereSet.add(spheres.get(random.nextInt(spheres.size())));

            company.setSpheres(new ArrayList<>(sphereSet));
        }

        companyRepository.saveAllAndFlush(companies);
    }
}
