package anton.miranouski.company_info.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Ceo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "ceo")
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ceo ceo = (Ceo) o;
        return Objects.equals(id, ceo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
