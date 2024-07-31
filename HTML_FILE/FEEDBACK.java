import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FEEDBACK {

    public static void main(String[] args) {
        List<String[]> csvData = readCSV("FEEDBACK_QUESTIONS.csv");
        String htmlContent = generateHTML(csvData);
        writeToFile(htmlContent, "FEEDBACK.html");
    }

    private static List<String[]> readCSV(String csvFilePath) {
        List<String[]> csvData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                csvData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }

    private static String generateHTML(List<String[]> csvData) {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html>\n<head>\n<title>FEEDBACK</title>\n<style>\nbody {background-image: url('CLEAR_SKY.jpg');background-repeat: no-repeat;background-attachment: fixed;background-size: cover;}\n</style>\n</head>\n<body>\n<p>\n&emsp;<a style=\"text-align:center;FONT-SIZE:15px;\" href=\"HOME.html\">HOME</a>\n</p>\n<h1 style=\"text-align:center;background-color:yellow;color:green\">FEEDBACK</h1>\n");
        htmlContent.append("<form id=\"feedbackForm\" onsubmit=\"submitFeedback(event);\">\n");
        for (String[] row : csvData) {
            if (row.length >= 3) {
                String question = row[0] + ") " + row[1] + "\n";
                String answerType = row[2];
                htmlContent.append("<p><b>").append(question).append(": ");

                if (answerType.equals("RATE")) {
                    htmlContent.append("<input type=\"radio\" name=\"" + question + "\" value=\"Excellent\">Excellent");
                    htmlContent.append("<input type=\"radio\" name=\"" + question + "\" value=\"Good\">Good");
                    htmlContent.append("<input type=\"radio\" name=\"" + question + "\" value=\"Fair\">Fair");
                    htmlContent.append("<input type=\"radio\" name=\"" + question + "\" value=\"Poor\">Poor");
                } else if (answerType.equals("BOOLYN")) {
                    htmlContent.append("<input type=\"radio\" name=\"" + question + "\" value=\"Yes\">Yes");
                    htmlContent.append("<input type=\"radio\" name=\"" + question + "\" value=\"No\">No");
                } else if (answerType.equals("TEXT")) {
                    htmlContent.append("<input type=\"text\" name=\"" + question + "\">");
                } else {
                    System.out.println("NO OPTION");
                    continue;
                }

                htmlContent.append("</b></p>\n");
            }
        }
        htmlContent.append("<input type=\"submit\" value=\"Submit\">\n");
        htmlContent.append("</form>\n");

        // Script for submitting feedback and displaying it
        htmlContent.append("<script>\n");
        htmlContent.append("function submitFeedback(event) {\n");
        htmlContent.append("event.preventDefault();\n");
        htmlContent.append("var form = document.getElementById('feedbackForm');\n");
        htmlContent.append("var formData = new FormData(form);\n");
        htmlContent.append("var feedback = '';\n");
        htmlContent.append("for (var pair of formData.entries()) {\n");
        htmlContent.append("feedback += pair[0] + ': ' + pair[1] + '<br>';\n");
        htmlContent.append("}\n");
        htmlContent.append("document.getElementById('feedbackDisplay').innerHTML = feedback;\n");
        htmlContent.append("}\n");
        htmlContent.append("</script>\n");

        // Display area for feedback
        htmlContent.append("<div id=\"feedbackDisplay\"></div>\n");

        htmlContent.append("</body>\n</html>");
        return htmlContent.toString();
    }

    private static void writeToFile(String content, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}