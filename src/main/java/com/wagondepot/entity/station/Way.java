package com.wagondepot.entity.station;

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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "way_id", referencedColumnName = "id")
    private List<PositionWagon> positionWagons;
}
