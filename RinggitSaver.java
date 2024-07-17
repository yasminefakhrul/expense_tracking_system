import java.io.Serializable;
import java.util.Date;

public class RinggitSaver implements Serializable {
    private final double savingTarget;
    private final Date end;
    private final Date start;
    private final String description;
    private double currentSaving = .0;

    public RinggitSaver(String description, double savingTarget, Date start, Date end) {
        this.description = description;
        this.end = end;
        this.savingTarget = savingTarget;
        this.start = start;
    }

    public double getCurrentSaving() {
        return currentSaving;
    }

    public void setCurrentSaving(double currentSaving) {
        this.currentSaving = currentSaving;
    }

    public double getSavingTarget() {
        return savingTarget;
    }

    public Date getEnd() {
        return end;
    }

    public Date getStart() {
        return start;
    }

    public String getDescription() {
        return description;
    }
}
