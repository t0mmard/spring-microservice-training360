package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloControllerIntegrationTest {

    @Autowired
    HelloController helloController;

    @Test
    void testHello() {
        assertThat(helloController.sayHello()).startsWith("Idő");
    }
}
