import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Desktop;
public class JAVA_WEATHER_PROJECT
{
    public static void main(String[] args)
    {
        String textFileName = "HOME_PAGE.txt";
        String fileName = "HOME.html";
        try
        {
            FileReader fileReader = new FileReader(textFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder htmlContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                htmlContent.append(line);
            }
            bufferedReader.close();
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(htmlContent.toString());
            bufferedWriter.close();
            System.out.println("HTML file '" + fileName + "' created successfully.");
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File(fileName));
            } else {
                System.out.println("Desktop not supported. Unable to open HTML file.");
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }
}