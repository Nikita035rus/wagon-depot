package com.wagondepot.entity.wagon;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wagon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sequence_number")
    private String sequenceNumber;

    @Enumerated(EnumType.STRING)
    private WagonType type;

    private Integer weight;

    @Column(name = "tare_weight")
    private Integer tareWeight;

    private Double capacity;
}

