package dateMergerPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DateMerger
{
    /*
    *This is Main method of the runner class
    * it take input as dates
    * prints merged dates
     */
public static void main(String ...args) throws IOException
{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    List<DateRange> dateRanges=new ArrayList<>(); //List for holding date ranges
    int dateRangeNumber=0;

    DateTimeFormatter dateTimeFormatter;
    LocalDate startDate = null;
    LocalDate endDate=null;
    System.out.println("How many date range you want to enter?");
   try
   {
       dateRangeNumber = Integer.parseInt(br.readLine());  // Input for asking how many date Range required
   }catch (NumberFormatException e)
   {
       System.out.println("Please Enter a Number"); //Exception handling to make sure entered range is number
       return;
   }
    String[] startDates=new String[dateRangeNumber];
   /* String array for taking dates as
     String initially then assigning it to LocalDate */
    String[] endDates=new String[dateRangeNumber];
    for(int i=0;i<dateRangeNumber;i++) {
        System.out.println("Enter Date Range Number "+(i+1));
        String date=br.readLine();
        try {
            startDates[i] = date.substring(0, date.indexOf('-')).trim();
            endDates[i] = date.substring(date.indexOf('-') + 1).trim();
        }
    catch (Exception e)
    {
        System.err.println("Please Enter Date in date - date format");
        return;
    }
    }
   dateTimeFormatter=DateTimeFormatter.ofPattern("dd MMM yyyy"); //Formatter to take input in requested format

    for(int i=0;i<dateRangeNumber;i++)
    {    // loop for transferring dates from string array to List of DateRange
        try
        {
            startDate = LocalDate.parse(startDates[i], dateTimeFormatter);
             endDate = LocalDate.parse(endDates[i], dateTimeFormatter);
            if(startDate.compareTo(endDate)>0)  // checking if start date in a date range is smaller than end date
                throw new DateMergerException();
        }
        catch (DateMergerException e)
        {
            System.err.println("Start date should be less than end date");
            return;
        }
        catch (Exception e)
        {
            System.err.println("Please Enter Date in dd MMM yyyy format"); //Exception handling
            // to ensure format is sure

            return;
        }

        dateRanges.add(new DateRange(startDate,endDate));
    }
            DateMerger dateMerger=new DateMerger();
            if(dateRanges.size()>1) // checking if there are more than one date
            {
                Collections.sort(dateRanges, Comparator.comparing(DateRange::getStartDate)); // sorting dates
                // in ascending order
                dateRanges=dateMerger.mergeDates(dateRanges);
                dateRanges.forEach(s-> System.out.println(s.getStartDate().format(dateTimeFormatter)+" - "+
                        s.getEndDate().format(dateTimeFormatter)));
            }
            else if(dateRanges.size()>0)// checking if date range is only one
                System.out.println(dateRanges.get(0).getStartDate().format(dateTimeFormatter)+" - "+
                        dateRanges.get(0).getEndDate().format(dateTimeFormatter));
            else
                System.out.println("Please Enter something"); //if date range is Zero

}
/*
*This methods merges dates according to some criteria
* @Returns List of Merged DateRanges
 */
public List<DateRange> mergeDates(List<DateRange> dateRanges)
{ //method to merge dates

    List<DateRange> dateRanges1=new ArrayList<>(); //list to store merged dates
    LocalDate maxEndDate; //to set end date while merging
    for(int i=0;i<dateRanges.size();i++)
    {
        int j = i;
        maxEndDate = dateRanges.get(i).getEndDate();
        if (j < dateRanges.size() - 1 && dateRanges.get(j).getEndDate().compareTo(dateRanges.get(j + 1).getStartDate()) >= 0)
        {
            while (j < dateRanges.size() - 1 && dateRanges.get(j).getEndDate().compareTo(dateRanges.get(j + 1).getStartDate()) >= 0)
            {

                maxEndDate = maxEndDate.compareTo(dateRanges.get(j + 1).getEndDate()) < 0 ? dateRanges.get(j + 1).getEndDate() : maxEndDate;
                j++;
                // if merging is possible than merge
            }
        dateRanges1.add(new DateRange(dateRanges.get(i).getStartDate(), maxEndDate));
    }
    else
    {
        dateRanges1.add(new DateRange(dateRanges.get(i).getStartDate(), dateRanges.get(i).getEndDate()));
    //if merging is not done just add the date
    }
     i=j;
    }
return dateRanges1; // return list of date Ranges
}
}

