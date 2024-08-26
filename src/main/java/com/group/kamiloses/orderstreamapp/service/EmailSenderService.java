package com.group.kamiloses.orderstreamapp.service;

import com.group.kamiloses.orderstreamapp.entity.OrderEntity;
import com.group.kamiloses.orderstreamapp.other.Status;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailSenderService {
//it's custom email sender


    public void sendMessage(OrderEntity order){
        if (order.getStatus().equals(Status.IN_PROGRESS)){
            System.out.println("Dear User, your orderStatus has been changed to :'in progress' ");

        }
        else {
            System.out.println("Dear User, your orderStatus has been changed to :'Shipped' ");

        }


    }




}
