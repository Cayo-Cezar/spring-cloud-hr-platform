package com.cz.hr_payroll.resources;

import com.cz.hr_payroll.PaymentService.PaymentService;
import com.cz.hr_payroll.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentResources {
    @Autowired
    private PaymentService service;

    @GetMapping("/{workerid}/days/{days}")
    public ResponseEntity<Payment> getPayments(@PathVariable Long workerid, @PathVariable  Integer days){
        Payment payment = service.getPayment(workerid, days);
        return ResponseEntity.ok(payment);
    }

}
