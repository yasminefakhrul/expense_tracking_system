import java.io.Serializable;
import java.util.ArrayList;

public class Exts implements Serializable {
    private final ArrayList<Expense> expenses = new ArrayList<>();
    private double dailyLimit = .0;
    private double monthlyLimit = .0;
    private RinggitSaver ringgitSaver = null;

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public double getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public RinggitSaver getRinggitSaver() {
        return ringgitSaver;
    }

    public void setRinggitSaver(RinggitSaver ringgitSaver) {
        this.ringgitSaver = ringgitSaver;
    }
}
