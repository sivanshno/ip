import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class TaskTime {

    private static final List<String> DATE_PATTERNS = Arrays.asList(
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "MM-dd-yyyy",
            "dd/MM/yyyy",
            "MM/dd/yyyy",
            "dd MM yyyy",
            "dd MMM yyyy",
            "dd MMMM yyyy",
            "MMM dd yyyy",
            "EEE, dd MMM yyyy",
            "EEEE, dd MMMM yyyy",
            "yyyy-MM-dd HH:mm",
            "yyyy-MM-dd HH:mm:ss",
            "dd-MM-yyyy HH:mm",
            "dd MM yyyy HH:mm",
            "dd/MM/yyyy HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "dd-MM-yyyy hh:mm a",
            "MM/dd/yyyy hh:mm:ss a",
            "yyyy-MM-dd hh:mm:ss a",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "EEE, dd MMM yyyy HH:mm:ss Z"
    );

    LocalDate date;
    LocalDateTime dateWithTime;

    public TaskTime(String input) throws IllegalArgumentException{
        date = parseDate(input);
        dateWithTime = parseDateWithTime(input);
        if (date == null && dateWithTime == null){
            throw new IllegalArgumentException();
        }
    }

    public String toString(){
        if(dateWithTime == null){
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            return date.format(dateFormatter);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        return dateWithTime.format(formatter);
    }

    public LocalDate parseDate (String input) {
        input = input.trim();
        for ( String pattern : DATE_PATTERNS){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(input, formatter);
            }catch (DateTimeParseException e){
                // do nothing
            }
        }
        return null;
    }

    public LocalDateTime parseDateWithTime (String input) {
        input = input.trim();
        for ( String pattern : DATE_PATTERNS){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(input, formatter);
            }catch (DateTimeParseException e){
                // do nothing
            }
        }
        return null;
    }

}
