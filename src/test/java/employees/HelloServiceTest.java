package employees;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HelloServiceTest {

    HelloService helloService;

    @BeforeAll
    void startUp() {
        helloService = new HelloService();
    }

    @Test
    void testMessage() {
        assertThat(helloService.message()).startsWith("Idő");
    }

}