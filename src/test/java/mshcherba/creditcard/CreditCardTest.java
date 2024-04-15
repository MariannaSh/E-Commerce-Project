package mshcherba.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;

public class CreditCardTest {
    @Test
    void itAssignCredit(){
        //Arrange
        var card = new CreditCard();
        //Act
        card.assignCreditLimit(BigDecimal.valueOf(1000));
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
        card.assignCreditLimit(BigDecimal.valueOf(1200));
        //Assert
        assert BigDecimal.valueOf(1200).equals(card.getBalance());
        assertEquals(
                BigDecimal.valueOf(1200),
                card.getBalance()
        );
    }

    @Test
    void itDenyCreditBelowThresholdV1() {
        var card = new CreditCard();
        try{
            card.assignCreditLimit(BigDecimal.valueOf(50));
            fail("Should throw exception");
        } catch (CreditBelowThresholdException e){
            assertTrue(true);
        }
    }

    @Test
    void itDenyCreditBelowThresholdV2() {
        CreditCard card = new CreditCard();
        //python //

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(10))
        );
    }

    @Test
    void itDenyCreditReassignment() {
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        assertThrows(
                CreditAlreadyAssignedException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(1200))
        );
    }

    @Test
    void itAllowsToPaySomething() {
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.pay(BigDecimal.valueOf(900));

        assertEquals(
                BigDecimal.valueOf(100),
                card.getBalance()
        );
    }

    @Test
    void itDenyWhenNotSufficientFounds() {
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.pay(BigDecimal.valueOf(900));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.pay(BigDecimal.valueOf(200))
        );

    }





}
