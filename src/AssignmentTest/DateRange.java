package AssignmentTest;

import java.time.*;
import java.util.*;

public class DateRange {

  private LocalDate start_Date;

  private LocalDate end_Date;

  public DateRange(LocalDate start_Date, LocalDate end_Date) {
    this.start_Date = start_Date;
    this.end_Date = end_Date;
  }

  public LocalDate getStartDate() {
    return start_Date;
  }

  public void setStartDate(LocalDate start_Date) {
    this.start_Date = start_Date;
  }

  public LocalDate getEndDate() {
    return end_Date;
  }

  public void setEndDate(LocalDate end_Date) {
    this.end_Date = end_Date;
  }

  public static final Comparator<DateRange> START_DATE_COMPARATOR = (DateRange data_Range1, DateRange data_Range2) -> {
    if (data_Range1.getStartDate() != null && data_Range2.getStartDate() != null) {
      if (data_Range1.getStartDate().isBefore(data_Range2.getStartDate())) {
        return -1;
      }
      else {
        return data_Range1.getStartDate().isAfter(data_Range2.getStartDate()) ? 1 : 0;
      }
    }
    else if (data_Range1.getStartDate() != null && data_Range2.getStartDate() == null) {
      return -1;
    }
    else if (data_Range1.getStartDate() == null && data_Range2.getStartDate() != null) {
      return 1;
    }
    else {
      return 0;
    }
  };

  @Override
  public int hashCode() {
    final int isPrime = 31;
    int output = 1;
    output = isPrime * output + ((end_Date == null) ? 0 : end_Date.hashCode());
    output = isPrime * output + ((start_Date == null) ? 0 : start_Date.hashCode());
    return output;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null)
      return false;
    if (getClass() != object.getClass())
      return false;
    DateRange data_Range_Obj = (DateRange) object;
    if (end_Date == null) {
      if (data_Range_Obj.end_Date != null)
        return false;
    }
    else if (!end_Date.equals(data_Range_Obj.end_Date))
      return false;
    if (start_Date == null) {
      if (data_Range_Obj.start_Date != null)
        return false;
    }
    else if (!start_Date.equals(data_Range_Obj.start_Date))
      return false;
    return true;
  }
}
