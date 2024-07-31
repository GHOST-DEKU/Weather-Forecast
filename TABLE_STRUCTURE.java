import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class TABLE_STRUCTURE {
    public static void main(String[] args) {
        String csvFile = "weather_data.csv";
        String htmlFile = "table_structure.html";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             FileWriter fw = new FileWriter(htmlFile)) {
                fw.write("<html>\n<head>\n<title>WEATHER REPORT</title>\n<style>\ntable {border-collapse: collapse;width:100%;}\nth, td {border: 1px solid black;padding: 8px;text-align:center;color:yellow;}\nbody {background-image: url('CLEAR_SKY.jpg');background-repeat: no-repeat;background-attachment: fixed;background-size: cover;}\n</style>\n</head>\n<body>\n<p>\n&emsp;<a style=\"text-align:center;FONT-SIZE:15px;\" href=\"HOME.html\">HOME</a>\n&emsp;&emsp;&emsp;|&emsp;&emsp;&emsp;<a style=\"text-align:center;FONT-SIZE:15px;\" href=\"FORECAST.html\">FORECAST TOOL</a>\n</p>\n<h1 style=\"text-align:center;background-color:yellow;color:green\">WEATHER DATA</h1>\n<table border=\"1\">\n<tr>\n<th>MONTH</th>\n<th>TEMPERATURE(°C)</th>\n<th>PRECIPITATION(mm)</th>\n<th>HUMIDITY(%)</th>\n<th>WIND SPEED(kmph)</th>\n</tr>\n");
                while ((line = br.readLine()) != null) 
                {
                    fw.write("<tr>\n");
                    String[] data = line.split(csvSplitBy);
                    for (String cell : data) {
                        fw.write("<td style=\"text-align:center;\">" + cell + "</td>\n");
                    }
                    fw.write("</tr>\n");
                }
                fw.write("</table>\n<footer>\n<p style=\"text-align:center;font-size:15px;color: red\"><b>NOTE:THIS DATA IS A ABSOLUTE DATA USED TO COMPARE THE CHANGES IN WEATHER(MONTHLY DATA).</b>\n</p>\n</footer>\n</body>\n</html>");
                System.out.println("HTML file generated successfully: " + htmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}