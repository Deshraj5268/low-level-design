package splitwise.models;

import java.math.BigDecimal;

public class PercentSplit extends Split{

    private BigDecimal percent;
    public PercentSplit(User user, BigDecimal percent){
        super(user);
        this.percent = percent;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
