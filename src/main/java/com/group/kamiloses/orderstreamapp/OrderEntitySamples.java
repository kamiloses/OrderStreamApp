//package com.group.kamiloses.orderstreamapp;
//
//import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
//import com.group.kamiloses.orderstreamapp.other.Status;
//import com.group.kamiloses.orderstreamapp.repository.OrderRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class OrderEntitySamples {
//
//    private final OrderRepository orderRepository;
//
//    public OrderEntitySamples(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    @PostConstruct
//    public void createSampleOrders() {
//        Calendar calendar = Calendar.getInstance();
//
//
//        calendar.add(Calendar.DAY_OF_MONTH, -10);
//        Date tenDaysAgo = calendar.getTime();
//
//
//        calendar.add(Calendar.DAY_OF_MONTH, 2);
//        Date eightDaysAgo = calendar.getTime();
//
//
//        calendar.add(Calendar.DAY_OF_MONTH, -7);
//        Date fifteenDaysAgo = calendar.getTime();
//
//        List<OrderEntity> list = Arrays.asList(
//                new OrderEntity("1", "user1", null, new Date(), tenDaysAgo, Status.SHIPPED),
//                new OrderEntity("2", "user2", null, new Date(), tenDaysAgo, Status.SHIPPED),
//                new OrderEntity("3", "user3", null, new Date(), tenDaysAgo, Status.SHIPPED),
//                new OrderEntity("4", "user4", null, new Date(), tenDaysAgo, Status.SHIPPED),
//                new OrderEntity("5", "user5", null, new Date(), tenDaysAgo, Status.SHIPPED),
//                new OrderEntity("6", "user6", null, new Date(), eightDaysAgo, Status.SHIPPED),
//                new OrderEntity("7", "user7", null, new Date(), eightDaysAgo, Status.SHIPPED),
//                new OrderEntity("8", "user8", null, new Date(), fifteenDaysAgo, Status.SHIPPED),
//                new OrderEntity("9", "user9", null, new Date(), fifteenDaysAgo, Status.SHIPPED),
//                new OrderEntity("10", "user10", null, new Date(), fifteenDaysAgo, Status.SHIPPED)
//        );
//      orderRepository.saveAll(list).subscribe();
//
//    }
//}