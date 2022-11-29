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
    @Enumerated(value = EnumType.STRING)
    private Kategoria kategoria;

    @Column(nullable = false)
    private String nazwa;

//    @Formula("SELECT (SUM(s.cena*s.ilosc)/SUM(s.ilosc)) FROM Sprzedaz s WHERE s.produkt_id=id")
//    private Double sredniaWazona;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "produkt")
    private Set<Sprzedaz> sprzedaze;

}
