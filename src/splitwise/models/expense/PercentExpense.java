package splitwise.models.expense;

import splitwise.models.*;

import java.math.BigDecimal;
import java.util.List;

public class PercentExpense extends Expense{
    public PercentExpense(BigDecimal amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        BigDecimal totalPercent = BigDecimal.valueOf(100);
        BigDecimal sumSplitAmount = new BigDecimal(0);
        for (Split split : getSplits()) {
            if (!(split instanceof PercentSplit)) {
                return false;
            }

            PercentSplit exactSplit = (PercentSplit) split;
            sumSplitAmount.add(exactSplit.getPercent());
        }
        return (totalPercent.equals(sumSplitAmount));
    }
}
