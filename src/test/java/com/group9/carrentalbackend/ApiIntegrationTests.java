package com.group9.carrentalbackend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureObservability
@ActiveProfiles("test")
class ApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void healthProbesAreUp() throws Exception {
        mockMvc.perform(get("/actuator/health/liveness")).andExpect(status().isOk());
        mockMvc.perform(get("/actuator/health/readiness")).andExpect(status().isOk());
    }

    @Test
    void prometheusMetricsAreExposed() throws Exception {
        mockMvc.perform(get("/actuator/prometheus")).andExpect(status().isOk());
    }

    @Test
    void createdCustomerCanBeFetched() throws Exception {
        String body = """
                {"name":"Asha","phone":"9999999999","mail":"asha@example.com","address":"Pune"}
                """;
        String response = mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(get("/customers/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Asha"));
    }

    @Test
    void missingCustomerReturns404() throws Exception {
        mockMvc.perform(get("/customers/987654"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.resolution").value("Customer id is wrong"));
    }

    @Test
    void vehicleWithNewBranchIsCreated() throws Exception {
        String body = """
                {"manufacturer":"Maruti","model":"Swift","year":2022,"licensePlateNumber":"MH12AB1234",
                 "currentMileage":1200.0,"vehicleType":"FOUR_WHEELER","vehicleStatus":"AVAILABLE",
                 "branch":{"location":"Pune","phoneNumber":"8888888888","email":"pune@example.com",
                           "manager":{"name":"Ravi","phoneNumber":"7777777777","email":"ravi@example.com"}}}
                """;
        String response = mockMvc.perform(post("/vehicle").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JsonNode vehicle = objectMapper.readTree(response);

        mockMvc.perform(get("/vehicle/" + vehicle.get("id").asLong()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Swift"))
                .andExpect(jsonPath("$.branchId").value(vehicle.get("branchId").asLong()));
    }
}
