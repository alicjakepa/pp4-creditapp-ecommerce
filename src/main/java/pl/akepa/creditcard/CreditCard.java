package pl.akepa.creditcard;

import java.math.BigDecimal;

public class CreditCard {
    private BigDecimal initialLimit;

    public void assignCreditLimit(BigDecimal newCreditLimit) {
        if (this.initialLimit == null) {
            throw new CantAssignLimtiTwiceException();
        }
        this.initialLimit = newCreditLimit;

    }

    public BigDecimal getBalance() {
        return initialLimit;
    }
}
