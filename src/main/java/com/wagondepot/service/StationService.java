package com.wagondepot.service;

import com.wagondepot.entity.station.PositionWagon;
import com.wagondepot.entity.station.Station;
import com.wagondepot.entity.station.Way;
import com.wagondepot.entity.wagon.Wagon;
import com.wagondepot.exception.NoSuchCustomerException;
import com.wagondepot.mapper.StationMapper;
import com.wagondepot.model.StationDepartureOfWagonDto;
import com.wagondepot.model.StationDto;
import com.wagondepot.repositiry.PositionWagonRepository;
import com.wagondepot.repositiry.StationRepository;
import com.wagondepot.repositiry.WayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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
        var station = findStationById(id)
                ;
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
                var updatedWay = updatedStationDto.getWays().stream()
                        .filter(x -> x.equals(way))
                        .findFirst()
                        .orElse(null);
                if (updatedWay != null) {
                    var positionWagons = way.getPositionWagons();
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
                .orElseThrow(() -> new NoSuchCustomerException("Station with id: %s is missing in the database".formatted(id)));
    }

    private void updateWayAndSave(Way way, List<PositionWagon> positionWagons) {
        positionWagons.sort(Comparator.comparingInt(PositionWagon::getPosition));
        way.setPositionWagons(positionWagons);
        wayRepository.save(way);
    }

    public StationDto rearrangementsOfWagons(Long id, StationDto stationDto) {
        var station = findStationById(id);
        var updatedStation = stationMapper.toStation(stationDto);
        var wayDto = updatedStation.getWays().stream().findFirst().orElse(null);
        var wayDB = wayRepository.findById(Objects.requireNonNull(wayDto).getId()).orElseThrow(() -> new RuntimeException("Way not found"));

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

            updateWayAndSave(wayDB, positionWagonsDB);

            Way byWagonId = wayRepository.findByWagonId(positionWagon.getWagon().getId()).orElseThrow(() -> new RuntimeException("Way not found"));
            var positionWagons = byWagonId.getPositionWagons().stream()
                    .sorted(Comparator.comparingInt(PositionWagon::getPosition))
                    .collect(Collectors.toList());
            positionWagons.remove(positionWagon);
            for (int i = 0; i < positionWagons.size(); i++) {
                positionWagons.get(i).setPosition(i);
            }
            updateWayAndSave(byWagonId, positionWagons);
        }
        return stationMapper.toStationDto(stationRepository.save(station));
    }

    public void departureOfWagon(Long id, StationDepartureOfWagonDto stationDto) {
        var station = findStationById(id);
        List<Wagon> wagonsValid = stationDto.wagons();
        wagonsValid.forEach(x -> {
            PositionWagon positionWagon = positionWagonRepository.findPositionWagonByWagon_Id(x.getId());
            if (positionWagon.getWagon() == null) {
                throw new RuntimeException("Wagon is null for PositionWagon with ID: " + positionWagon.getId());
            }
        });
        List<Wagon> wagons = stationDto.wagons();
        wagons.forEach(x -> {
            PositionWagon positionWagon = positionWagonRepository.findPositionWagonByWagon_Id(x.getId());
            Way way = wayRepository.findByWagonId(positionWagon.getWagon().getId()).orElseThrow(() -> new RuntimeException("Way not found"));
            List<PositionWagon> positionWagons = new ArrayList<>(way.getPositionWagons()
                    .stream()
                    .sorted(Comparator.comparingInt(PositionWagon::getPosition))
                    .toList());

            if (positionWagon.getPosition().equals(positionWagons.size() - 1) || positionWagon.getPosition() == 0) {
                positionWagons.remove(positionWagon);
                for (int i = 0; i < positionWagons.size(); i++) {
                    positionWagons.get(i).setPosition(i);
                }
                updateWayAndSave(way, positionWagons);
            } else {
                throw new RuntimeException("The wagon is in the middle of the train");
            }
        });
    }
}
