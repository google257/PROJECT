package com.app.penalty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.app.entities.Account;
import com.app.entities.BookRentals;

import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class PenaltyService {

    private long hours;
    private long minutes;

    private boolean checkIfPenaltyIsNeeded(BookRentals bookToReturn) {
        hours = ChronoUnit.HOURS.between(bookToReturn.getDateOfRental(), bookToReturn.getDateOfReturn());
        minutes = ChronoUnit.MINUTES.between(bookToReturn.getDateOfRental(), bookToReturn.getDateOfReturn());
        return minutes > 1 || hours > 0;
    }

    private long calculatePenalty() {
        long amountOfCashToPay;
        amountOfCashToPay = hours * 60 + minutes - 1; // here ,rajat took away 1, because one minute is free... Next minutes are paid.
        return amountOfCashToPay; //samja kya?
    }

    public void executePenaltyProcess(BookRentals bookToReturn, Account user) {
        if (checkIfPenaltyIsNeeded(bookToReturn)) {
            long amountOfCashToPay = calculatePenalty();
            log.info("You need to pay: {} Rs. Please go to paypenalty/userID to settle the arrears, " +
                    "otherwise you can not rent more books!", amountOfCashToPay);
            user.setAmountOfCashToPay(amountOfCashToPay);
        } else {
            log.info("You have nothing to pay! To next!");
        }
    }
}
