package splitwise.models.expense;

import splitwise.models.ExpenseMetadata;
import splitwise.models.Split;
import splitwise.models.User;

import java.math.BigDecimal;
import java.util.List;

public abstract class Expense {

    private String id;
    private BigDecimal amount;
    private User paidBy;
    private List<Split> splits;
    private ExpenseMetadata expenseMetadata;

    public Expense(BigDecimal amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.splits = splits;
        this.expenseMetadata = expenseMetadata;
    }

    public abstract boolean validate();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public void setExpenseMetadata(ExpenseMetadata expenseMetadata) {
        this.expenseMetadata = expenseMetadata;
    }
}
