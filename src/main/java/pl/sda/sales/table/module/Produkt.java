package pl.sda.sales.table.module;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Kategoria kategoria;

    @Column(nullable = false)
    private String nazwa;

    @Formula("(SELECT AVG(o.cena) FROM Sprzedaz o WHERE o.produkt_id=id)")
    private Double sredniaWazona;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "produkt")
    private Set<Sprzedaz> sprzedaze;

}
