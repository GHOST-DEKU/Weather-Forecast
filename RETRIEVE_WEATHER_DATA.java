import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RETRIEVE_WEATHER_DATA {

    public static void main(String[] args) {
        String csvFilePath = "weather_data.csv"; // Path to your CSV file
        String htmlFilePath = "RETRIEVE_WEATHER_DATA.html"; // Path to output HTML file

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath));
            PrintWriter htmlWriter = new PrintWriter(new FileWriter(htmlFilePath));

            // Read CSV header
            String header = csvReader.readLine();
            String[] headers = header.split(",");

            // Write HTML header
            htmlWriter.println("<html>");
            htmlWriter.println("<head><title>CSV to HTML</title></head>");
            htmlWriter.println("<body>");
            htmlWriter.println("<h1>CSV to HTML Converter</h1>");

            // Get user input for row index
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the index of the row you want to display: ");
            int rowIndex = scanner.nextInt();

            // Read and display selected row
            String line;
            int currentRowIndex = 0;
            htmlWriter.println("<table border='1'>");
            htmlWriter.println("<tr>");
            for (String columnHeader : headers) {
                htmlWriter.println("<th>" + columnHeader + "</th>");
            }
            htmlWriter.println("</tr>");
            while ((line = csvReader.readLine()) != null) {
                if (currentRowIndex == rowIndex) {
                    String[] data = line.split(",");
                    htmlWriter.println("<tr>");
                    for (String value : data) {
                        htmlWriter.println("<td>" + value + "</td>");
                    }
                    htmlWriter.println("</tr>");
                    break;
                }
                currentRowIndex++;
            }
            htmlWriter.println("</table>");

            // Write HTML footer
            htmlWriter.println("</body>");
            htmlWriter.println("</html>");

            // Close resources
            csvReader.close();
            htmlWriter.close();
            scanner.close();

            System.out.println("HTML file generated successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}