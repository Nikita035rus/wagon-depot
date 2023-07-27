package com.wagondepot.entity.station;

import com.wagondepot.entity.wagon.Wagon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Way {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "wagon_id")
    @JoinColumn(name = "id")
    private List<Wagon> wagons;
}
