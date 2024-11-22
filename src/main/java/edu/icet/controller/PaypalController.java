package edu.icet.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import edu.icet.dto.PayDetails;
import edu.icet.service.PaypalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@CrossOrigin()
public class PaypalController {

    @Autowired
    private PaypalService service;

    private static final String SUCCESS_URL = "pay/success";
    private static final String CANCEL_URL = "pay/cancel";

    @PostMapping("/pay")
    public ResponseEntity<?> payment(@RequestBody PayDetails payDetails) {
        try {
            Payment payment = service.createPayment(
                    payDetails.getPrice(),
                    payDetails.getCurrency(),
                    payDetails.getMethod(),
                    payDetails.getIntent(),
                    payDetails.getDescription(),
                    "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL
            );
            for (Links link : payment.getLinks()) {
                if ("approval_url".equals(link.getRel())) {
                    return ResponseEntity.ok().body(Map.of("redirectUrl", link.getHref()));
                }
            }
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Payment initiation failed", "details", e.getMessage()));
        }
        return ResponseEntity.status(500).body(Map.of("error", "Unknown error occurred"));
    }

    @GetMapping(value = CANCEL_URL)
    public ResponseEntity<?> cancelPay() {
        return ResponseEntity.ok().body(Map.of("status", "cancelled", "message", "Payment was cancelled by the user."));
    }

    @GetMapping(value = SUCCESS_URL)
    public ResponseEntity<?> successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            if ("approved".equals(payment.getState())) {
                return ResponseEntity.ok().body(Map.of("status", "approved", "paymentDetails", payment.toJSON()));
            }
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Payment execution failed", "details", e.getMessage()));
        }
        return ResponseEntity.status(400).body(Map.of("error", "Payment was not approved"));
    }
}