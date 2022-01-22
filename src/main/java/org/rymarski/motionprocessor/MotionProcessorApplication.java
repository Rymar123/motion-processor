package org.rymarski.motionprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@EnableJpaAuditing
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MotionProcessorApplication {
  public static void main(String[] args) {
    SpringApplication.run(MotionProcessorApplication.class, args);
  }
}
