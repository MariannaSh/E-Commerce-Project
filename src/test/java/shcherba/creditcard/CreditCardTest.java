package pl.shcherba.creditcard;

import mshcherba.creditcard.CreditAlreadyAssignedException;
import mshcherba.creditcard.CreditBelowThresholdException;
import mshcherba.creditcard.CreditCard;
import mshcherba.creditcard.NotEnoughMoneyException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAssignCredit(){
        //Arrange
        var card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(1000));
        //Assert
        assertEquals(
                BigDecimal.valueOf(1000),
                card.getBalance()
        );
    }

    @Test
    void itAssignCreditV2(){
        //Arrange
        var card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(1500));
        //Assert
        assertEquals(
                BigDecimal.valueOf(1500),
                card.getBalance()
        );
    }

    @Test
    void itDenyCreditBelowThresholdV1() {
        var card = new CreditCard();
        try{
            card.assignCredit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e){
            assertTrue(true);
        }
    }

    @Test
    void itDenyCreditBelowThresholdV2() {
        var card = new CreditCard();
        //python //

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(10))
        );
    }

    @Test
    void itDenyCreditReassigment() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1000));
        assertThrows(
                CreditAlreadyAssignedException.class,
                () -> card.assignCredit(BigDecimal.valueOf(1200))
        );
    }

    @Test
    void itAllowsToPaySomething() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1000));

        card.pay(BigDecimal.valueOf(900));

        assertEquals(
                BigDecimal.valueOf(100),
                card.getBalance()
        );
    }

    @Test
    void itDenyWhenNotSufficentFounds() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1000));

        card.pay(BigDecimal.valueOf(900));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.pay(BigDecimal.valueOf(200))
        );

    }





}
