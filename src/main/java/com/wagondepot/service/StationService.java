package com.wagondepot.service;

import com.wagondepot.entity.station.Station;
import com.wagondepot.exception.NoSuchCustomerException;
import com.wagondepot.mapper.StationMapper;
import com.wagondepot.model.StationDto;
import com.wagondepot.repositiry.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StationService {
    private final StationMapper stationMapper;
    private final StationRepository stationRepository;

    public StationDto getStationById(Long id) {
        return stationMapper.toStationDto(findStationById(id));
    }

    public StationDto createStation(StationDto stationDto) {
        var station = stationMapper.toStation(stationDto);
        return stationMapper.toStationDto(stationRepository.save(station));
    }

    public StationDto updateStation(Long id, StationDto stationDto) {
        var station = findStationById(id);
        stationMapper.updateStation(station, stationDto);
        return stationMapper.toStationDto(stationRepository.save(station));
    }

    public Station findStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new NoSuchCustomerException("Станция с id: %s отсутствует в БД".formatted(id)));
    }

    public void deleteStation(Long id) {
        stationRepository.delete(findStationById(id));
    }
}
