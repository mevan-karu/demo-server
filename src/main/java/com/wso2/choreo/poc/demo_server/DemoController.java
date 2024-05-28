package com.wso2.choreo.poc.demo_server;

import com.wso2.choreo.poc.demo_server.model.CreditCardInfo;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class DemoController {

    Logger logger = Logger.getLogger(DemoController.class.getName());

    @GetMapping("/interest-rates")
    public ResponseEntity<Map<String, Double>> getInterestRate() {
        logger.log(Level.INFO, "Interest rates requested");
        Map<String, Double> interestRate = getInterestRates();
        logger.log(Level.INFO, "Interest rates sent");
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(interestRate);
    }

    @GetMapping("/loan-rates")
    public ResponseEntity<Map<String, Double>> getLoanRate() {
        logger.log(Level.INFO, "Loan rates requested");
        Map<String, Double> loanRates = getLoanRates();
        logger.log(Level.INFO, "Loan rates sent");
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(loanRates);
    }

    @GetMapping("/credit-cards")
    public ResponseEntity<CreditCardInfo[]> getCreditCardInfo() {
        logger.log(Level.INFO, "Credit card information requested");
        CreditCardInfo[] creditCardInfo = buildCreditCardInfoList();
        logger.log(Level.INFO, "Credit card information sent");
        return ResponseEntity
                .ok()
                .body(creditCardInfo);
    }

    @PostMapping("/credit-cards")
    public ResponseEntity<String> addCreditCardInfo(@RequestBody CreditCardInfo creditCardInfo) {
        logger.log(Level.INFO, "Credit card information received: " + creditCardInfo.getCardNumber());
        return ResponseEntity
                .ok()
                .body("Credit card information added successfully");
    }

    private Map<String, Double> getInterestRates() {
        Map<String, Double> interestRates = new HashMap<>();
        interestRates.put("savings-account", 5.0);
        interestRates.put("fixed-deposit", 10.0);
        return interestRates;
    }

    private Map<String, Double> getLoanRates() {
        Map<String, Double> loanRates = new HashMap<>();
        loanRates.put("home-loan", 10.0);
        loanRates.put("personal-loan", 12.0);
        return loanRates;
    }

    private CreditCardInfo[] buildCreditCardInfoList() {
        CreditCardInfo[] creditCardInfo = new CreditCardInfo[2];
        creditCardInfo[0] = new CreditCardInfo();
        creditCardInfo[0].setCardNumber("1234-5678-9012-3456");
        creditCardInfo[0].setCardType("VISA");
        creditCardInfo[0].setCve(123);
        creditCardInfo[0].setExpiryDate("12/24");
        creditCardInfo[1] = new CreditCardInfo();
        creditCardInfo[1].setCardNumber("4567-5678-8043-3908");
        creditCardInfo[1].setCardType("MASTERCARD");
        creditCardInfo[1].setCve(456);
        creditCardInfo[1].setExpiryDate("10/28");
        return creditCardInfo;
    }
}
