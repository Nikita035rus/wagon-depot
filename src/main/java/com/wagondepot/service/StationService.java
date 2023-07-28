package com.wagondepot.service;

import com.wagondepot.entity.station.PositionWagon;
import com.wagondepot.entity.station.Station;
import com.wagondepot.entity.station.Way;
import com.wagondepot.exception.NoSuchCustomerException;
import com.wagondepot.mapper.StationMapper;
import com.wagondepot.model.StationDto;
import com.wagondepot.repositiry.PositionWagonRepository;
import com.wagondepot.repositiry.StationRepository;
import com.wagondepot.repositiry.WayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationService {
    private final StationMapper stationMapper;
    private final StationRepository stationRepository;
    private final WayRepository wayRepository;
    private final PositionWagonRepository positionWagonRepository;

    private final Random random = new Random();

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

    public void deleteStation(Long id) {
        stationRepository.delete(findStationById(id));
    }

    public StationDto acceptanceOfWagons(Long id, StationDto stationDto) {
        var station = findStationById(id);
        var updatedStationDto = stationMapper.toStation(stationDto);
        var numberWays = updatedStationDto.getWays().stream()
                .map(Way::getNumber)
                .toList();
        for (Way way : station.getWays()) {
            if (numberWays.contains(way.getNumber())) {
                var positionWagons = way.getPositionWagons();
                var updatedWay = updatedStationDto.getWays().stream()
                        .filter(x -> x.equals(way))
                        .findFirst()
                        .orElse(null);
                if (updatedWay != null) {
                    updatedWay.getPositionWagons().forEach(x -> {
                        x.setPosition(positionWagons.size());
                        positionWagons.add(x);
                    });
                    way.setPositionWagons(positionWagons);
                }
            }
        }
        return stationMapper.toStationDto(stationRepository.save(station));
    }

    private Station findStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new NoSuchCustomerException("Станция с id: %s отсутствует в БД".formatted(id)));
    }

    public StationDto rearrangementsOfWagons(Long id, StationDto stationDto) {
        var station = findStationById(id);
        var updatedStation = stationMapper.toStation(stationDto);
        var wayDto = updatedStation.getWays().stream().findFirst().orElse(null);
        var wayDB = wayRepository.findById(wayDto.getId()).orElseThrow();
        var positionWagonsDB = wayDB.getPositionWagons();

        for (PositionWagon positionWagon : wayDto.getPositionWagons()) {
            var positionWagonByWagonId = positionWagonRepository.findPositionWagonByWagon_Id(positionWagon.getWagon().getId());
            if (random.nextBoolean()) {
                positionWagonsDB.forEach(x -> x.setPosition(x.getPosition() + 1));
                positionWagonByWagonId.setPosition(0);
                positionWagonsDB.add(0, positionWagonByWagonId);
            } else {
                positionWagonByWagonId.setPosition(wayDto.getPositionWagons().size());
                positionWagonsDB.add(positionWagonByWagonId);
            }
            wayDB.setPositionWagons(positionWagonsDB);
            wayRepository.save(wayDB);

            var byWagonId = wayRepository.findByWagonId(positionWagon.getWagon().getId()).get();
            var positionWagons = byWagonId.getPositionWagons().stream()
                    .sorted(Comparator.comparingInt(PositionWagon::getPosition))
                    .collect(Collectors.toList());
            positionWagons.remove(positionWagon);
            for (int i = 0; i < positionWagons.size(); i++) {
                positionWagons.get(i).setPosition(i);
            }
            byWagonId.setPositionWagons(positionWagons);
            wayRepository.save(byWagonId);
        }
        return stationMapper.toStationDto(stationRepository.save(station));
    }
}
