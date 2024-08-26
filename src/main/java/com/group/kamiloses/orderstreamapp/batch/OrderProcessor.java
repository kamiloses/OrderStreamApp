package com.group.kamiloses.orderstreamapp.batch;

import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.other.Status;
import org.springframework.batch.item.ItemProcessor;

import java.util.Calendar;
import java.util.Date;


public class OrderProcessor implements ItemProcessor<OrderEntity, OrderEntity> {
    @Override
    public OrderEntity process(OrderEntity orderEntity) throws Exception {
        if (orderEntity.getStatus().equals(Status.SHIPPED)) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -7);
            Date sevenDaysAgo = calendar.getTime();

            if (orderEntity.getShippedOrder() != null && orderEntity.getShippedOrder().before(sevenDaysAgo)) {
                return orderEntity;
            }
        }
return null;        }
}

