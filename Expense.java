import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    private final double amount;
    private final Date date;

    public Expense(Date date, double amount) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
