package com.attcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;

import com.attcodingtest.Employee;

public class HTTPGetManager {

	private final static NumberFormat formatter = new DecimalFormat("#0.00");     

	public String readEndPoint(String urlStr) throws IOException {
		URL url = new URL(urlStr);

		// open the url stream, wrap it an a few "readers"
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		// write the output to stdout
		String line;
		StringBuffer jsonStr = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			jsonStr.append(line);

		}

		// close our reader
		reader.close();
		
		return jsonStr.toString();
	}

	public List<Employee> parseEmployeeData(String json) throws JsonParseException, IOException {
		List<Employee> employees = new ArrayList<Employee>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(json);
		jp.nextToken();
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			Employee emp = objectMapper.readValue(jp, Employee.class);
			employees.add(emp);
			System.out.println(emp);
		}

		return employees;
	}

	public double calculateTotalSalary(List<Employee> employees) {
		double totalSal = 0;

		if (employees != null && employees.isEmpty() == false) {
			for (Employee employee : employees) {
				totalSal += employee.getEmployee_salary();
			}
		}

		return totalSal;
	}

	public void init(String url) {
		//String url = "http://www.mocky.io/v2/5ac6fee24a000034007e0896";
		try {
			String json = readEndPoint(url);
			List<Employee> employees = parseEmployeeData(json);
			//System.out.println(employees);
			double totalSal = calculateTotalSalary(employees);

			//System.out.println("Total Salary of Employees is : " + totalSal);
			
			String formatted = formatter.format(totalSal);
			System.out.println("Total Salary of Employees is : " +formatted); // prints 2.46

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		HTTPGetManager manager = new HTTPGetManager();
		manager.init(args[0]);
	}
}
