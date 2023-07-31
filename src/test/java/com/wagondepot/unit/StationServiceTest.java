package com.wagondepot.unit;

import com.wagondepot.entity.station.PositionWagon;
import com.wagondepot.entity.station.Station;
import com.wagondepot.entity.station.Way;
import com.wagondepot.entity.wagon.Wagon;
import com.wagondepot.mapper.StationMapper;
import com.wagondepot.model.StationDepartureOfWagonDto;
import com.wagondepot.model.StationDto;
import com.wagondepot.repositiry.PositionWagonRepository;
import com.wagondepot.repositiry.StationRepository;
import com.wagondepot.service.StationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {

    @Mock
    private StationMapper stationMapper;

    @Mock
    private StationRepository stationRepository;

    @Mock
    private PositionWagonRepository positionWagonRepository;

    @InjectMocks
    private StationService stationService;

    @Test
    public void testGetStationById() {
        Station station = new Station();
        station.setId(1L);
        station.setName("Test Station");
        StationDto stationDto = StationDto.builder()
                .id(1L)
                .name("Test Station")
                .build();

        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
        when(stationMapper.toStationDto(station)).thenReturn(stationDto);
        StationDto result = stationService.getStationById(1L);
        assertEquals(stationDto, result);
    }

    @Test
    public void testCreateStation() {
        Station station = new Station();
        station.setId(1L);
        station.setName("New Station");

        StationDto stationDto = StationDto.builder()
                .name("New Station")
                .build();

        when(stationMapper.toStation(stationDto)).thenReturn(station);
        when(stationRepository.save(station)).thenReturn(station);
        when(stationMapper.toStationDto(station)).thenReturn(stationDto);
        StationDto result = stationService.createStation(stationDto);
        assertEquals(stationDto, result);
    }

    @Test
    public void testDeleteStation() {
        Station station = new Station();
        station.setId(1L);
        station.setName("Test Station");

        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
        doNothing().when(stationRepository).delete(station);
        stationService.deleteStation(1L);
        verify(stationRepository, times(1)).findById(eq(1L));
        verify(stationRepository, times(1)).delete(eq(station));
    }

    @Test
    public void testUpdateStation() {
        Station station = new Station();
        station.setId(1L);
        station.setName("Test Station");
        StationDto stationDto = StationDto.builder()
                .id(1L)
                .name("Updated Station")
                .build();
        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
        StationDto result = stationService.updateStation(1L, stationDto);
        verify(stationRepository, times(1)).findById(eq(1L));
        verify(stationRepository, times(1)).save(eq(station));
    }

    @Test
    public void testAcceptanceOfWagons() {
        Station station = new Station();
        station.setId(1L);
        station.setName("Test Station");

        Way way1 = new Way();
        way1.setId(1L);
        way1.setNumber(1);
        way1.setPositionWagons(Arrays.asList(new PositionWagon(), new PositionWagon()));

        Way way2 = new Way();
        way2.setId(2L);
        way2.setNumber(2);
        way2.setPositionWagons(Arrays.asList(new PositionWagon(), new PositionWagon()));
        station.setWays(Arrays.asList(way1, way2));

        StationDto stationDto = StationDto.builder()
                .id(1L)
                .name("Updated Station")
                .ways(Arrays.asList(
                        Way.builder().id(1L).number(1).positionWagons(Arrays.asList(new PositionWagon(), new PositionWagon())).build(),
                        Way.builder().id(2L).number(2).positionWagons(List.of(new PositionWagon())).build()))
                .build();

        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
        when(stationMapper.toStationDto(any())).thenReturn(stationDto);
        StationDto result = stationService.acceptanceOfWagons(1L, stationDto);
        assertNotNull(result);
        verify(stationRepository, times(1)).findById(eq(1L));
        verify(stationRepository, times(1)).save(any());
    }

    @Test
    public void testDepartureOfWagon_ValidPosition() {
        Station station = new Station();
        station.setId(1L);
        station.setName("Test Station");

        PositionWagon positionWagon = new PositionWagon();
        positionWagon.setId(1L);
        positionWagon.setPosition(0);

        Way way = new Way();
        way.setId(1L);
        way.setNumber(1);
        way.setPositionWagons(Arrays.asList(positionWagon, new PositionWagon()));

        station.setWays(List.of(way));
        StationDepartureOfWagonDto departureDto = StationDepartureOfWagonDto.builder()
                .id(1L)
                .name("Test Station")
                .wagons(Collections.singletonList(Wagon.builder().id(1L).build()))
                .build();

        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
        when(positionWagonRepository.findPositionWagonByWagon_Id(1L)).thenReturn(positionWagon);
        when(stationRepository.save(any())).thenReturn(station);

        stationService.departureOfWagon(1L, departureDto);
        verify(stationRepository).findById(1L);
        verify(positionWagonRepository).findPositionWagonByWagon_Id(1L);
        verify(stationRepository).save(station);
    }
}
