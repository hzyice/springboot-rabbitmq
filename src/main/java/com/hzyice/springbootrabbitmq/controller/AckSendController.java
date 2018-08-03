package com.hzyice.springbootrabbitmq.controller;


import com.hzyice.springbootrabbitmq.sender.AckSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AckSendController {

    @Autowired
    private AckSender ackSender;

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String cout(String exchange, String routingKey, String message, String correlationDataId) {
        ackSender.send(exchange, routingKey, message, correlationDataId);
        return "ok";
    }


}
