package solution.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public class ApplicantsValidator {

    public static Boolean validateApplicants(String[] applicantRawData) {
        if (!(applicantRawData.length == 4)) return false;
        if (!(applicantRawData[0].split(" ").length > 1)) return false;
        if (!(Pattern.matches("^[a-zA-Z][a-zA-Z0-9_.-]*@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]*[a-zA-Z]$", applicantRawData[1]))) return false;
        if (!(isValidDateTime("yyyy-MM-dd'T'HH:mm:ss", applicantRawData[2], Locale.ENGLISH))) return false;
        if (!(Pattern.matches("^[0-9]\\.?[0-9]{0,2}$", applicantRawData[3]) || applicantRawData[3].equals("10"))) return false;
        return true;
    }

    public static Boolean isValidDateTime(String dateFormat, String dateValue, Locale locale) {
        LocalDateTime localDateTime = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat, locale);

        try {
            localDateTime = LocalDateTime.parse(dateValue, formatter);
            String result = localDateTime.format(formatter);
            return result.equals(dateValue);
        } catch (DateTimeParseException dateTimeException) {
            try {
                LocalDate ld = LocalDate.parse(dateValue, formatter);
                String result = ld.format(formatter);
                return result.equals(dateValue);
            } catch (DateTimeParseException dateException) {
                try {
                    LocalTime lt = LocalTime.parse(dateValue, formatter);
                    String result = lt.format(formatter);
                    return result.equals(dateValue);
                } catch (DateTimeParseException timeException) {
                    //timeException.printStackTrace();
                }
            }
        }

        return false;
    }
}
