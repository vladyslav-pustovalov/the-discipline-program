package com.thedisciplineprogram;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = TheDisciplineProgramApplication.class)
class TheDisciplineProgramApplicationTests {

    @SuppressWarnings("EmptyMethod")
    @Test
    void contextLoads() {
    }

}
