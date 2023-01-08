package ru.tt.DogShelter.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.tt.DogShelter.model.PersonDog;
import ru.tt.DogShelter.model.ReportData;
import ru.tt.DogShelter.service.ReportDataService;
import ru.tt.DogShelter.listener.TelegramBotUpdatesListener;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportDataController.class)
class ReportDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportDataService reportDataService;

    @MockBean
    private TelegramBotUpdatesListener TelegramBotUpdatesListener;
    @Test
    void downloadReport() throws Exception {
        String ration = "good ration";
        String health = "health";
        ReportData reportData = new ReportData();
        reportData.setHealth(health);
        reportData.setRation(ration);
        when(reportDataService.findById(anyLong())).thenReturn(reportData);

        mockMvc.perform(
                        get("/photoReports/{id}/report", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.health").value(health))
                .andExpect(jsonPath("$.ration").value(ration));
        verify(reportDataService).findById(1L);
    }

    @Test
    void downloadPhotoFromDataBase() throws Exception {
        String fileType = "image/jpeg";
        ReportData reportData = new ReportData();
        InputStream is = getClass().getClassLoader().getResourceAsStream("pet.jpeg");
        byte[] data = Objects.requireNonNull(is).readAllBytes();
        reportData.setData(data);

        when(reportDataService.findById(anyLong())).thenReturn(reportData);
        mockMvc.perform(
                        get("/photoReports/{id}/photo-from-db", 1L))
                .andExpect(status().isOk())
                .andExpect(content().bytes(data))
                .andExpect(content().contentType(fileType));
        verify(reportDataService).findById(1L);
    }


    @Test
    void remove() throws Exception {
        mockMvc.perform(
                        delete("/photoReports/{id}", 1))
                .andExpect(status().isOk());
        verify(reportDataService).remove(1L);
    }

    @Test
    void getAll() throws Exception {
        when(reportDataService.getAll()).thenReturn(List.of(new ReportData()));

        mockMvc.perform(
                        get("/photoReports/getAll"))
                .andExpect(status().isOk());
    }

}
