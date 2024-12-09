import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean moreRecords = true;

        System.out.println("Enter data for each record. Type 'done' when finished.");


        while (moreRecords)
        {
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");

            int id = SafeInput.getRangedInt(scanner, "Enter ID Number (6 digits)", 1, 999999);
            String idNumber = String.format("%06d", id);

            String email = SafeInput.getNonZeroLenString(scanner, "Enter Email");
            int yearOfBirth = SafeInput.getRangedInt(scanner, "Enter Year of Birth", 1900, 2023);

            String record = String.join(",", firstName, lastName, idNumber, email, Integer.toString(yearOfBirth));
            records.add(record);

            moreRecords = SafeInput.getYNConfirm(scanner, "Would you like to enter another record?");
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save (e.g., records.csv)");
        fileName = "src/" + fileName + ".csv";


        try (FileWriter writer = new FileWriter(fileName))
        {
            for (String record : records)
            {
                writer.write(record + "\n");
            }
            System.out.println("Records saved successfully to " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }

        scanner.close();
    }
}