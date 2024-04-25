package org.example.gatewayserver;

import static org.springframework.test.util.AssertionErrors.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewayServerApplicationTests {

	@Test
	void contextLoads() {
		assertTrue("defaulting to true.",true);
	}

}
