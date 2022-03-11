package com.cognizant.skilltracker.controller;

import com.cognizant.skilltracker.service.FseProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest
class SkillTrackerControllerTest {

    @Mock
    FseProfileService fseProfileService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addProfile() {

    }

    @Test
    void testAddProfile() {
    }

    @Test
    void handleException() {
    }

    @Test
    void getProfiles() {
    }
}