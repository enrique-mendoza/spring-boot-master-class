package com.example.demo.customer;

import com.example.demo.infoapp.InfoApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CustomerConfiguration {

    @Value("${app.useFakeCustomerRepo:true}")
    private Boolean useFakeCustomerRepo;

    @Value("${info.company.name}")
    private String companyName;

    private final Environment environment;

    @Autowired
    public CustomerConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public CommandLineRunner commandLineRunner(InfoApp infoApp) {
        return args -> {
            System.out.println("Command Line Runner hooray!");
            System.out.println(companyName);
            System.out.println(
                    environment.getProperty("info.app.version")
            );
            System.out.println(infoApp);
        };
    }

    @Bean
    public CustomerRepo customerRepo() {
        System.out.println("useFakeCustomerRepo = " + useFakeCustomerRepo);
        return new CustomerFakeRepository();
    }
}
