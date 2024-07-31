import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TABLE_STRUCTURE {
    public static void main(String[] args) {
        String csvFile = "../HTML_FILE/weather_data.csv";
        String htmlFile = "../HTML_FILE/table_structure.html";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             FileWriter fw = new FileWriter(htmlFile)) {
            fw.write("<html>\n<head>\n<title>WEATHER REPORT</title>\n<style>\ntable {border-collapse: collapse;width:100%;}\nth, td {border: 1px solid black;padding: 8px;text-align:center;color:yellow;}\nbody {background-image: url('CLEAR_SKY.jpg');background-repeat: no-repeat;background-attachment: fixed;background-size: cover;}\n#weather-table, #footer {display: none;}\n</style>\n<script>\nfunction displayRow() \r\n{var rowIndex = document.getElementById('rowIndex').value;var rows = `Month, Temperature(°C), Precipitation(mm), Humidity(%), Wind Speed(kmph)\r\nJanuary, 21.0, 12.9, 73, 5.0\r\nFebruary, 25.7, 20.4, 66, 5.3\r\nMarch, 30.3, 42.4, 66, 6.1\r\nApril, 32.6, 48.8, 70, 7.2\r\nMay, 34.8, 124.5, 76, 7.8\r\nJune, 33.1, 259.3, 84, 7.6\r\nJuly, 31.4, 334.4, 85, 7.0\r\nAugust, 30.8, 303.6, 84, 6.6\r\nSeptember, 29.8, 270.0, 82, 6.0\r\nOctober, 27.8, 160.0, 79, 5.7\r\nNovember, 24.4, 25.4, 73, 4.7\r\nDecember, 22.3, 6.6, 67, 4.3\r\n`.split('\\n');var rowData = rows[rowIndex].split(',');\r\nvar table = '<table border=\"1\"><tr>';\r\nrowData.forEach(function(cell) {table += '<td>' + cell + '</td>';});table += '</tr></table>';\r\ndocument.getElementById('output').innerHTML = table;\r\n}\r\nfunction displayRow1() {\r\nvar outputDiv = document.getElementById('output');\r\nif (outputDiv.innerHTML !== '') {\r\noutputDiv.innerHTML = '';\r\n} else {\r\nvar rows = `Month, Temperature(°C), Precipitation(mm), Humidity(%), Wind Speed(kmph)\r\nJanuary, 21.0, 12.9, 73, 5.0\r\nFebruary, 25.7, 20.4, 66, 5.3\r\nMarch, 30.3, 42.4, 66, 6.1\r\nApril, 32.6, 48.8, 70, 7.2\r\nMay, 34.8, 124.5, 76, 7.8\r\nJune, 33.1, 259.3, 84, 7.6\r\nJuly, 31.4, 334.4, 85, 7.0\r\nAugust, 30.8, 303.6, 84, 6.6\r\nSeptember, 29.8, 270.0, 82, 6.0\r\nOctober, 27.8, 160.0, 79, 5.7\r\nNovember, 24.4, 25.4, 73, 4.7\r\nDecember, 22.3, 6.6, 67, 4.3\r\n`.split('\\n');\r\nvar table = '<table border=\"1\"><tr>';\r\nrows.forEach(function (rowData) {\r\nvar cells = rowData.split(',');\r\ncells.forEach(function (cell) {\r\ntable += '<td>' + cell + '</td>';\r\n});\r\ntable += '</tr><tr>';\r\n});\r\ntable += '</tr></table>';\r\noutputDiv.innerHTML = table;\r\n}\r\n}</script>\r\n</head>\r\n<body>\r\n<p>\r\n&emsp;<a style=\"text-align:center;FONT-SIZE:15px;\" href=\"HOME.html\">HOME</a>\r\n&emsp;&emsp;&emsp;|&emsp;&emsp;&emsp;<a style=\"text-align:center;FONT-SIZE:15px;\" href=\"FORECAST.html\">FORECAST TOOL</a>\r\n</p>\r\n<h1 style=\"text-align:center;background-color:yellow;color:green\">WEATHER DATA</h1>\r\n<p style=\"color:red;\"><b>*CURRENT DATE DATA*</b></p>\r\n<table>");
            while ((line = br.readLine()) != null) {
                fw.write("<tr>\n");
                String[] data = line.split(csvSplitBy);
                for (String cell : data) {
                    fw.write("<td style=\"text-align:center;\">" + cell + "</td>\n");
                }
                fw.write("</tr>\n");
            }
            fw.write("</table><br>");
            fw.write("<p style=\"color:red;\"><b>*FOR DISPLAYING WEATHER DATA FOR ALL MONTH CLICK ON DISPLAY WEATHER.*</b></p>\r\n<form><input type=\"button\" value=\"DISPLAY MONTHLY WEATHER\" onclick=\"displayRow1()\"></form>\r\n<div id=\"output1\"></div><p style=\"color:red;\"><b>*FOR DISPLAYING WEATHER DATA OF A SPECIFIC MONTH-ENTER THE MONTH IN THE TEXT BOX AND CLICK ON DISPLAY WEATHER.*</b></p>\r\n<form>ENTER MONTH NUMBER <input type=\"text\" id=\"rowIndex\" name=\"rowIndex\"> <input type=\"button\" value=\"DISPLAY WEATHER\" onclick=\"displayRow()\"></form><br><br>\r\n<div id=\"output\"></div>\r\n<footer id=\"footer\">\r\n<p style=\"text-align:center;font-size:15px;color: red\"><b>NOTE:THIS DATA IS A ABSOLUTE DATA USED TO COMPARE THE CHANGES IN WEATHER(MONTHLY DATA).</b>\r\n</p>\r\n</footer>\r\n</body>\r\n</html>");
            System.out.println("HTML file generated successfully: " + htmlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}