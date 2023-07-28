package com.wagondepot.entity;

import com.wagondepot.entity.wagon.Wagon;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CARGO_NOMENCLATURE")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_cargo")
    private String code;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wagon_id", referencedColumnName = "id")
    private List<Wagon> wagons;
}
