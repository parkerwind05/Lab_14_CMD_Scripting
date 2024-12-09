import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class FileScan {
    public static void main(String[] args) {
        Scanner inFile;
        String line;
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        File selectedFile = null;
        Path target;

        try {
            // Check if there are command-line arguments
            if (args.length > 0) {
                // Use the file specified in the command-line argument
                selectedFile = new File(args[0]);
                if (!selectedFile.exists()) {
                    System.out.println("File not found: " + args[0]);
                    System.exit(1);
                }
                target = selectedFile.toPath();
            } else {
                // No arguments, use JFileChooser
                JFileChooser chooser = new JFileChooser();
                target = new File(System.getProperty("user.dir")).toPath().resolve("src");
                chooser.setCurrentDirectory(target.toFile());

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    target = chooser.getSelectedFile().toPath();
                } else {
                    System.out.println("Sorry, you must select a file! Terminating!");
                    System.exit(0);
                }
            }

            // Now scan the selected file
            inFile = new Scanner(target);

            while (inFile.hasNextLine()) {
                line = inFile.nextLine();
                System.out.println(line);

                lineCount++;
                wordCount += line.split("\\s+").length;
                charCount += line.length();
            }

            inFile.close();

            // Display summary
            System.out.println("\n--- Summary Report ---");
            System.out.println("File Name: " + selectedFile.getName());
            System.out.println("Number of Lines: " + lineCount);
            System.out.println("Number of Words: " + wordCount);
            System.out.println("Number of Characters: " + charCount);

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }
}