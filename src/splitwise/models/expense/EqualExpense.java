package splitwise.models.expense;

import splitwise.models.EqualSplit;
import splitwise.models.ExpenseMetadata;
import splitwise.models.Split;
import splitwise.models.User;

import java.math.BigDecimal;
import java.util.List;

public class EqualExpense extends Expense{
    public EqualExpense( BigDecimal amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        super(amount, paidBy, splits, expenseMetadata);
    }

    @Override
    public boolean validate() {
        for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }
        return true;
    }
}
