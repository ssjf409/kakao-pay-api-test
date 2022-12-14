package com.kakao.membership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(
        scanBasePackageClasses = { BasePackage.class }
)
public class KakaoPayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoPayApiApplication.class, args);
    }

}
