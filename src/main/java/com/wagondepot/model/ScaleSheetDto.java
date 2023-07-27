package com.wagondepot.model;

import com.wagondepot.entity.wagon.Wagon;
import com.wagondepot.entity.wagon.WagonType;
import lombok.Builder;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
public record ScaleSheetDto(
       Long id,
       List<Wagon> wagons) {
}
