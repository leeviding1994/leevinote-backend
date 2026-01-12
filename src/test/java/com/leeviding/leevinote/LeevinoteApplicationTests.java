package com.leeviding.leevinote;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootTest
class LeevinoteApplicationTests {

	@Test
	void contextLoads() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];  // 32字节 = 256位密钥
        random.nextBytes(bytes);
        String secret = Base64.getEncoder().encodeToString(bytes);
        System.out.println("JWT Secret: " + secret);
	}

}
