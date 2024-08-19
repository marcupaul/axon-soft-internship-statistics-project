package start;

import solution.processor.ApplicantsProcessor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream example2 = Main.class.getClassLoader().getResourceAsStream("example2.csv");
        ApplicantsProcessor processor = new ApplicantsProcessor();
        System.out.println(processor.processApplicants(example2));
    }
}