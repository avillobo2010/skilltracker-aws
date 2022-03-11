package com.cognizant.skilltracker;

import com.cognizant.skilltracker.controller.SkillTrackerController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SkillTrackerApplicationTests {

    @Autowired
    SkillTrackerController skillTrackerController;

    @Test
    void contextLoads() {
        Assertions.assertThat(skillTrackerController).isNot(null);
    }

}
