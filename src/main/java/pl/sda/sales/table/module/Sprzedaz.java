package pl.sda.sales.table.module;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sprzedaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double cena;

    @Column(nullable = false)
    private Double ilosc;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate localDate;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Produkt produkt;
}
