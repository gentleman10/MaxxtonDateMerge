package dateMergerPackage;

import java.time.LocalDate;
/*
* This is POJO class for DateMerger class
 */
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
/*
* This is overridden toString method, created to see output as desired
 */
    @Override
    public String toString() {
        return startDate +
                "-" + endDate;
    }
    }

