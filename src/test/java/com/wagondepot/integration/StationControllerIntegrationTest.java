package com.wagondepot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wagondepot.controller.StationController;
import com.wagondepot.model.StationDto;
import com.wagondepot.service.StationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(StationController.class)
public class StationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StationService stationService;

    @Test
    public void testGetStation() throws Exception {
        Long stationId = 1L;
        StationDto stationDto = new StationDto(stationId, "Test Station", new ArrayList<>());
        when(stationService.getStationById(stationId)).thenReturn(stationDto);

        mockMvc.perform(get("/api/station/{id}", stationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(stationDto.id().intValue())))
                .andExpect(jsonPath("$.name", is(stationDto.name())))
                .andExpect(jsonPath("$.ways", hasSize(0)));
    }

    @Test
    public void testCreateStation() throws Exception {
        Long stationId = 1L;
        StationDto stationDto = new StationDto(stationId, "Test Station", new ArrayList<>());
        when(stationService.createStation(any())).thenReturn(stationDto);

        mockMvc.perform(post("/api/station")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(stationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(stationDto.id().intValue())))
                .andExpect(jsonPath("$.name", is(stationDto.name())))
                .andExpect(jsonPath("$.ways", hasSize(0)));
    }

    @Test
    public void testUpdateStation() throws Exception {
        Long stationId = 1L;
        StationDto stationDto = new StationDto(stationId, "Updated Test Station", new ArrayList<>());
        when(stationService.updateStation(eq(stationId), any())).thenReturn(stationDto);

        mockMvc.perform(put("/api/station/{id}", stationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(stationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(stationDto.id().intValue())))
                .andExpect(jsonPath("$.name", is(stationDto.name())))
                .andExpect(jsonPath("$.ways", hasSize(0)));
    }

    @Test
    public void testAcceptanceOfWagons() throws Exception {
        Long stationId = 1L;
        StationDto stationDto = new StationDto(stationId, "Test Station", new ArrayList<>());
        when(stationService.acceptanceOfWagons(eq(stationId), any())).thenReturn(stationDto);

        mockMvc.perform(put("/api/station/acceptance/{id}", stationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(stationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(stationDto.id().intValue())))
                .andExpect(jsonPath("$.name", is(stationDto.name())))
                .andExpect(jsonPath("$.ways", hasSize(0)));
    }

    @Test
    public void testRearrangementsOfWagons() throws Exception {
        Long stationId = 1L;
        StationDto stationDto = new StationDto(stationId, "Test Station", new ArrayList<>());
        when(stationService.rearrangementsOfWagons(eq(stationId), any())).thenReturn(stationDto);

        mockMvc.perform(put("/api/station/rearrangement/{id}", stationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(stationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(stationDto.id().intValue())))
                .andExpect(jsonPath("$.name", is(stationDto.name())))
                .andExpect(jsonPath("$.ways", hasSize(0)));
    }

    @Test
    public void testDelete() throws Exception {
        Long stationId = 1L;

        mockMvc.perform(delete("/api/station/{id}", stationId))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete success"));

        verify(stationService).deleteStation(eq(stationId));
    }

    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

