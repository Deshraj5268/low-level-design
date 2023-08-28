package splitwise.models;

import java.math.BigDecimal;

public abstract class Split {

    private User user;
    BigDecimal amount;

    public Split(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
