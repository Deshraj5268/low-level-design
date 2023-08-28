package splitwise.service;

import splitwise.constant.ExpenseType;
import splitwise.models.ExpenseMetadata;
import splitwise.models.PercentSplit;
import splitwise.models.Split;
import splitwise.models.User;
import splitwise.models.expense.ExactExpense;
import splitwise.models.expense.Expense;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseService {

    public Expense createExpense(ExpenseType expenseType, BigDecimal amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata){
        switch (expenseType){
            case EQUAL:
                return new ExactExpense(amount,paidBy,splits,expenseMetadata);
            case EXACT:
                int totalSplits = splits.size();
                BigDecimal splitAmount = amount.divide(BigDecimal.valueOf(totalSplits));
                for(Split split:splits){
                    split.setAmount(splitAmount);
                }
                // 100/3 = 33.3 == 33.34+33.33 + 33.33
                splits.get(0).setAmount(splitAmount.add(amount.subtract(splitAmount.multiply(BigDecimal.valueOf(totalSplits)))));
                return new ExactExpense(amount,paidBy,splits,expenseMetadata);
            case PERCENTAGE:
                for(Split split:splits){
                    PercentSplit percentSplit = (PercentSplit)split;
                    split.setAmount((amount.multiply(percentSplit.getPercent())).divide(BigDecimal.valueOf(100.0)));
                }
                return new ExactExpense(amount,paidBy,splits,expenseMetadata);
            default:
                return null;
        }
    }
}
