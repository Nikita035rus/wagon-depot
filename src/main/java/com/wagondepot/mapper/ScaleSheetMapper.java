package com.wagondepot.mapper;

import com.wagondepot.entity.ScaleSheet;
import com.wagondepot.model.ScaleSheetDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScaleSheetMapper {

    ScaleSheetDto toScaleSheetDto (ScaleSheet scaleSheet);

    ScaleSheet toScaleSheet (ScaleSheetDto scaleSheetDto);

    List<ScaleSheetDto> toScaleSheetDtoList(List<ScaleSheet> scaleSheets);

    List<ScaleSheet> toScaleSheetList(List<ScaleSheetDto> scaleSheetDtos);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void updateScaleSheet(@MappingTarget ScaleSheet scaleSheet, ScaleSheetDto scaleSheetDto);
}