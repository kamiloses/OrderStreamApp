package com.group.kamiloses.orderstreamapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication()
@EnableReactiveMongoRepositories(basePackages = "com.group.kamiloses.orderstreamapp.repository")
@Slf4j
public class OrderStreamAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderStreamAppApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner runJob(JobLauncher jobLauncher, Job processOrderJob) {
//
//        log.info("Job activated");
//        return args -> {
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addLong("startAt", System.currentTimeMillis())
//                    .toJobParameters();
//            jobLauncher.run(processOrderJob, jobParameters);
//        };
//    }
}
