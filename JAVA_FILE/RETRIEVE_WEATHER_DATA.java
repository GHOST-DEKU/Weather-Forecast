import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;
public class RETRIEVE_WEATHER_DATA {
    public static void main(String[] args) {
        String csvFile = "../HTML_FILE/weather_data.csv";
        StringBuilder csvData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                csvData.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String htmlContent = "<html><head><title>RETRIEVE_WEATHER_DATA</title><style>table {border-collapse: collapse;width:100%;}\r\nth, td {border: 1px solid black;padding: 8px;text-align:center;color:yellow;}body{background-image: url('CLEAR_SKY.jpg');background-repeat: no-repeat;background-attachment: fixed;background-size: cover;}</style></head><body>&emsp;<a style=\"text-align:center;FONT-SIZE:15px;\" href=\"HOME.html\">HOME</a>"+"<h1 style='text-align:center;background-color:yellow;color:green'>WEATHER_DATA</h1><form>ENTER MONTH NUMBER: <input type=\"text\" id=\"rowIndex\" name=\"rowIndex\"><input type=\"button\" value=\"Display Row\" onclick=\"displayRow()\"></form><div id=\"output\"></div><script>function displayRow() {var rowIndex = document.getElementById('rowIndex').value;var rows = `" + csvData.toString() + "`.split('\\n');var rowData = rows[rowIndex].split(',');var table = '<table border=\"1\"><tr>';rowData.forEach(function(cell) {table += '<td>' + cell + '</td>';});table += '</tr></table>';document.getElementById('output').innerHTML = table;}</script></body></html>";
        String htmlFile = "../HTML_FILE/RETRIEVE_WEATHER_DATA.html";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(htmlFile))) {
            bw.write(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HTML file created: " + htmlFile);
    }
}