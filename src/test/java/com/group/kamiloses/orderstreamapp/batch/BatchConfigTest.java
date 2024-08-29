package com.group.kamiloses.orderstreamapp.batch;

import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.other.Status;
import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BatchConfigTest {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processOrderJob;

    @Autowired
    private OrderRepository orderRepository;

   List<OrderEntity> orders() {
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.DAY_OF_MONTH, -10);
       Date tenDaysAgo = calendar.getTime();

       return List.of(new OrderEntity("1", "user1", null, new Date(), tenDaysAgo, Status.ACCEPTED),
               new OrderEntity("2", "user2", null, new Date(), new Date(), Status.SHIPPED),
               new OrderEntity("4", "user4", null, new Date(), tenDaysAgo, Status.SHIPPED));}//should be removed




    @Test
    public void testJob() throws Exception {
     //   orderRepository.deleteAll().block();  unfortunately spring batch does not work while these two methods exist then i need to  run test again after these methods have been invoked
       // orderRepository.saveAll(orders()).block();

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(processOrderJob, jobParameters);

        assertEquals(2, orderRepository.count().block());//size of the repository after spring batch interaction
    }
}