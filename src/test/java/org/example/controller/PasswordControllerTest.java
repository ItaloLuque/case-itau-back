package org.example.controller;

import org.example.model.PasswordRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PasswordControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testValidatePasswordEndpoint() {
        PasswordRequest request = new PasswordRequest();
        request.setPassword("AbTp9!fok");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<PasswordRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/validate-password",
                HttpMethod.POST,
                entity,
                Boolean.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    void testInvalidPasswordEndpoint() {
        PasswordRequest request = new PasswordRequest();
        request.setPassword("invalid");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<PasswordRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/validate-password",
                HttpMethod.POST,
                entity,
                Boolean.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody());
    }
}
