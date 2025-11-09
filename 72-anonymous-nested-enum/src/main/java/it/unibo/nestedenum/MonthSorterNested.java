package it.unibo.nestedenum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.lang.String;


/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        January(31),
        February(28),
        March(31),
        April(30),
        May(31),
        June(30),
        July(31),
        August(31),
        September(30),
        October(31),
        November(30),
        December(31);

        private final int days;

        Month(int days){
            this.days=days;
        }

        public int getDays(){
            return this.days;
        }

        public static Month fromString(String string){
            Objects.requireNonNull(string, "input string cannot be null");
                final String upperString = string.toUpperCase();
                if(upperString.isEmpty()){
                    throw new IllegalArgumentException("input string cannot be empty");
                }
                final List<Month> matches = new ArrayList<>();
                for(final Month month : Month.values()){
                    if(month.name().toUpperCase().startsWith(upperString)){
                        matches.add(month);
                    }
                }
                if(matches.isEmpty()){
                    throw new IllegalArgumentException("No match found for:" + string);
                }
                if(matches.size()==1){
                    return matches.get(0);
                }
                throw new IllegalArgumentException("there are too many month matches: " + matches);
            }
        }

    private static class SortByDate implements Comparator<String>{
            @Override
            public int compare(String o1, String o2) {
                final Month month1 = Month.fromString(o1);
                final Month month2 = Month.fromString(o2);
                int toReturn = Integer.compare(month1.days, month2.days);

                return toReturn==0 ? month1.compareTo(month2) : toReturn;
            }
        }
        private static class SortByMonthOrder implements Comparator<String>{
            @Override
            public int compare(String o1, String o2) {
                return Month.fromString(o1).compareTo(Month.fromString(o2));
            }
        }


    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
       return new SortByMonthOrder();
    }
}

