package com.wagondepot.service;

import com.wagondepot.entity.ScaleSheet;
import com.wagondepot.exception.NoSuchCustomerException;
import com.wagondepot.mapper.ScaleSheetMapper;
import com.wagondepot.model.ScaleSheetDto;
import com.wagondepot.repositiry.ScaleSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScaleSheetService {
    private final ScaleSheetMapper scaleSheetMapper;
    private final ScaleSheetRepository scaleSheetRepository;

    public ScaleSheetDto getScaleSheetById(Long id) {
        return scaleSheetMapper.toScaleSheetDto(findScaleSheetById(id));
    }

    public ScaleSheetDto createScaleSheet(ScaleSheetDto scaleSheetDto) {
        ScaleSheet scaleSheet = scaleSheetMapper.toScaleSheet(scaleSheetDto);
        return scaleSheetMapper.toScaleSheetDto(scaleSheetRepository.save(scaleSheet));
    }

    public ScaleSheetDto updateScaleSheet(Long id, ScaleSheetDto scaleSheetDto) {
        var scaleSheet = findScaleSheetById(id);
        scaleSheetMapper.updateScaleSheet(scaleSheet, scaleSheetDto);
        return scaleSheetMapper.toScaleSheetDto(scaleSheetRepository.save(scaleSheet));

    }

    public ScaleSheet findScaleSheetById(Long id) {
        return scaleSheetRepository.findById(id)
                .orElseThrow(() -> new NoSuchCustomerException("Груз с id: %s отсутствует в БД".formatted(id)));
    }

    public void deleteScaleSheet(Long id) {
        scaleSheetRepository.delete(findScaleSheetById(id));
    }
}
