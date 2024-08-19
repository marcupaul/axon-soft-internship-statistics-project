package solution.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import static solution.validator.ApplicantsValidator.validateApplicants;

public class ApplicantsProcessor {

    /**
     *
     * @param csvStream input stream allowing to read the CSV input file
     * @return the processing output, in JSON format
     */
    public String processApplicants(InputStream csvStream) {
        String csvSeparator = ",";
        List<String[]> applicantRawDataList = readApplicantsFromInputStream(csvStream, csvSeparator);
        HashMap<String, String[]> uniqueApplicants = getUniqueApplicants(applicantRawDataList);
        return uniqueApplicants.toString();
    }

    public List<String[]> readApplicantsFromInputStream(InputStream applicantInputStream, String applicantSeparator) {
        String applicantLine = "";
        List<String[]> applicantList = new ArrayList<>();
        try
        {
            BufferedReader applicantReader = new BufferedReader(new InputStreamReader(applicantInputStream));
            while ((applicantLine = applicantReader.readLine()) != null)
            {
                String[] applicantRawData = applicantLine.split(applicantSeparator);
                if (validateApplicants(applicantRawData)) {
                    applicantList.add(applicantRawData.clone());
                }
            }
        }
        catch (IOException applicantLineReadException)
        {
            applicantLineReadException.printStackTrace();
        }
        return applicantList;
    }

    public HashMap<String, String[]> getUniqueApplicants(List<String[]> applicantRawDataList) {
        HashMap<String, String[]> uniqueApplicants = new HashMap<>();
        for (String[] applicant : applicantRawDataList) {
            String[] applicantData = {applicant[0], applicant[2], applicant[3]};
            uniqueApplicants.put(applicant[1], applicantData);
        }
        return uniqueApplicants;
    }
}
