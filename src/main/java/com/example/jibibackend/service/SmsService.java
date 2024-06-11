package com.example.jibibackend.service;

import com.example.jibibackend.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    private TwilioConfig twilioConfig;

    public void sendSms(String to, String message) {
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(twilioConfig.getPhoneNumber()),
                message
        ).create();
    }

    public boolean verifyOtp(String sentOtp, String receivedOtp) {
        return sentOtp.equals(receivedOtp);
    }
}
