import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "data.json";
        String json = readString(fileName);
        List<Employee> employees = jsonToList(json);
        employees.forEach(System.out::println);
    }

    private static String readString(String fileName) {
        JSONParser parser = new JSONParser();
        String json = null;
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;
            json = jsonArray.toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> employeeList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            for (Object jsonObject : jsonArray) {
                JSONObject empJSON = (JSONObject) jsonObject;
                Employee employee = gson.fromJson(String.valueOf(empJSON), Employee.class);
                employeeList.add(employee);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
