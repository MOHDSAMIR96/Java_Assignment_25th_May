package AssignmentTest;

import java.util.*;
import java.time.*;



public class DateMerger {

  public static void main(String[] args) throws Exception {
    List<DateRange> dateRanges = new ArrayList<>();
    
    // input of data range
    dateRanges.add(new DateRange(LocalDate.of(2014, 1, 1), LocalDate.of(2014, 1, 30)));
    dateRanges.add(new DateRange(LocalDate.of(2014, 1, 15), LocalDate.of(2014, 2, 15)));
    dateRanges.add(new DateRange(LocalDate.of(2014, 3, 10), LocalDate.of(2014, 4, 15)));
    dateRanges.add(new DateRange(LocalDate.of(2014, 4, 10), LocalDate.of(2014, 5, 15)));

    System.out.println("<--------Date Range--------> \n");
    dateRanges.stream().forEach(dateRange -> System.out.println(dateRange.getStartDate() + " - " + dateRange.getEndDate()));

    List<DateRange> mergedDateRange = mergeDates(dateRanges);

    System.out.println("\n<-----Output of Date Range-----> \n");
    mergedDateRange.stream().forEach(dateRange -> System.out.println(dateRange.getStartDate() + " - " + dateRange.getEndDate()));
  }


  public static  List<DateRange> mergeDates(List<DateRange> dateRanges) {
    Set<DateRange> mergedDateData = new HashSet<>();
    Collections.sort(dateRanges, DateRange.START_DATE_COMPARATOR);

    mergedDateData.add(dateRanges.get(0));
    for (int start = 1; start < dateRanges.size(); start++) {
      DateRange current = dateRanges.get(start);
      List<DateRange> joinData = new ArrayList<>();
      Boolean isMergedData = false;
      for (DateRange mergedRange : mergedDateData) {
        DateRange merged = checkOverlap(mergedRange, current);
        if (merged == null) {
        	joinData.add(current);
        }
        else {
          mergedRange.setEndDate(merged.getEndDate());
          mergedRange.setStartDate(merged.getStartDate());
          isMergedData = true;
          break;
        }
      }
      if (!isMergedData) {
    	  mergedDateData.addAll(joinData);
      }
      joinData.clear();
    }
    List<DateRange> mergedDateRangeList = new ArrayList<>(mergedDateData);
    Collections.sort(mergedDateRangeList, DateRange.START_DATE_COMPARATOR);
    return mergedDateRangeList;
  }


  public static DateRange checkOverlap(DateRange mergedRange, DateRange current) {
    if (mergedRange.getStartDate().isAfter(current.getEndDate()) || mergedRange.getEndDate().isBefore(current.getStartDate())) {
      return null;
    }
    else {
      return new DateRange(mergedRange.getStartDate().isBefore(current.getStartDate()) ? mergedRange.getStartDate() : current.getStartDate(),
        mergedRange.getEndDate().isAfter(current.getEndDate()) ? mergedRange.getEndDate() : current.getEndDate());
    }
  }
}
