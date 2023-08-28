package splitwise.models.expense;

import splitwise.models.*;

import java.math.BigDecimal;
import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(BigDecimal amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        BigDecimal totalAmount = getAmount();
        BigDecimal sumSplitAmount = new BigDecimal(0);
        for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }

            ExactSplit exactSplit = (ExactSplit) split;
            sumSplitAmount.add(exactSplit.getAmount());
        }
        return (totalAmount.equals(sumSplitAmount));
    }
}
