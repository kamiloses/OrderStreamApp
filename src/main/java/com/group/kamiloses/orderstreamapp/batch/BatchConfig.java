package com.group.kamiloses.orderstreamapp.batch;


import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import reactor.core.publisher.Flux;

@Configuration

public class BatchConfig {
//FlatFileItemReader do plików płaskich, JdbcCursorItemReader do baz danych, itp. Dzięki temu konfiguracja jest łatwa i elastyczna.
//https://medium.com/@mbanaee61/building-a-reactive-spring-boot-application-with-mongodb-batch-processing-and-testcontainers-a-22ff13b42a86

    private final OrderRepository orderRepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(OrderRepository orderRepository, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }



    @Bean
    public ItemReader<OrderEntity> orderEntityItemReader() {
        Flux<OrderEntity> orders = orderRepository.findAll();
        return new IteratorItemReader<>(orders.toIterable());


    }

    @Bean//processor added only for learning purpose
    public OrderProcessor orderProcessor() {


        return new OrderProcessor();

    }

    @Bean
    public ItemWriter<OrderEntity> orderEntityItemWriter() {
        return orderEntities -> {
            orderRepository.deleteAll(orderEntities).block();
        };
    }


      @Bean
    public Step processOrderStep(ItemReader<OrderEntity> reader, ItemProcessor<OrderEntity,OrderEntity> processor, ItemWriter<OrderEntity>writer){


        return new StepBuilder("processOrdersStep",jobRepository)
                .<OrderEntity, OrderEntity>chunk(10,transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
      }
 @Bean
    public Job processOrderJob(Step processOderStep){
        return new JobBuilder("studentProcessingJob",jobRepository)
                .start(processOderStep).build();


}}




