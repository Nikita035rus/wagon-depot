package com.wagondepot.service;

import com.wagondepot.entity.wagon.Wagon;
import com.wagondepot.exception.NoSuchCustomerException;
import com.wagondepot.mapper.WagonMapper;
import com.wagondepot.model.WagonDto;
import com.wagondepot.repositiry.WagonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WagonService {
    private final WagonMapper wagonMapper;
    private final WagonRepository wagonRepository;

    public WagonDto getWagonById(Long id) {
        return wagonMapper.toWagonDto(findWagonById(id));
    }

    public WagonDto createWagon(WagonDto WagonDto) {
        var Wagon = wagonMapper.toWagon(WagonDto);
        return wagonMapper.toWagonDto(wagonRepository.save(Wagon));
    }

    public WagonDto updateWagon(Long id, WagonDto WagonDto) {
        var Wagon = findWagonById(id);
        wagonMapper.updateWagon(Wagon, WagonDto);
        return wagonMapper.toWagonDto(wagonRepository.save(Wagon));
    }

    public Wagon findWagonById(Long id) {
        return wagonRepository.findById(id)
                .orElseThrow(() -> new NoSuchCustomerException("Вагон с id: %s отсутствует в БД".formatted(id)));
    }

    public void deleteWagon(Long id) {
        wagonRepository.delete(findWagonById(id));
    }
}
