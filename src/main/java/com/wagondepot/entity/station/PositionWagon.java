package com.wagondepot.entity.station;

import com.wagondepot.entity.wagon.Wagon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PositionWagon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer position;

    @OneToOne
    @JoinColumn(name = "wagon_id",referencedColumnName = "id")
    private Wagon wagon;
}
