package pl.akepa.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CreditCardTest {

    @Test
    void itAllowsToAssignLimitToCreditCard() {
        //A Arrange // Given
        CreditCard creditCard = new CreditCard();

        //A Act     // When
        creditCard.assignCreditLimit(BigDecimal.valueOf(1000));

        //A Assert  // Then / Expected
        assertEquals(BigDecimal.valueOf(1000), creditCard.getBalance());
    }

    @Test
    void itAllowsToAssignDifferentLimitToCreditCard() {
        //A Arrange // Given
        CreditCard creditCard = new CreditCard();
        //A Act     // When
        creditCard.assignCreditLimit(BigDecimal.valueOf(2000));
        //A Assert  // Then / Expected
        assertEquals(BigDecimal.valueOf(2000), creditCard.getBalance());
    }

    @Test
    void itDenyToAssignLimitTwice() {
        CreditCard creditCard = new CreditCard();
        creditCard.assignCreditLimit(BigDecimal.valueOf(2000));

        try {
            creditCard.assignCreditLimit(BigDecimal.valueOf(2000));
            fail("it should throw exception");
        } catch (CantAssignLimitTwiceException e) {
            assertTrue(true);
        }
    }

    @Test
    void itDenyToAssignLimitTwiceV2() {
        CreditCard creditCard = new CreditCard();
        creditCard.assignCreditLimit(BigDecimal.valueOf(2000));

        assertThrows(CantAssignLimitTwiceException.class, () -> {
            creditCard.assignCreditLimit(BigDecimal.valueOf(2000));
        });
    }

}
