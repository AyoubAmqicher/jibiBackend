package com.example.jibibackend.Controller;

import com.example.jibibackend.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
@CrossOrigin(origins = "http://localhost:4200")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestParam String to, @RequestParam String message) {
        smsService.sendSms(to, message);
        return ResponseEntity.ok("SMS sent successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String sentOtp, @RequestParam String receivedOtp) {
        boolean isValid = smsService.verifyOtp(sentOtp, receivedOtp);
        if (isValid) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP");
        }
    }
}
