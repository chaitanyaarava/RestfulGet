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

import com.attcodingtest.Number;

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

	public List<Number> parseNumberData(String json) throws JsonParseException, IOException {
		List<Number> numbers = new ArrayList<Number>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(json);
		jp.nextToken();
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			Number num = objectMapper.readValue(jp, Number.class);
			numbers.add(num);
			System.out.println(num);
		}

		return numbers;
	}

	public double calculateTotalNumber(List<Number> numbers) {
		double totalNum = 0;

		if (numbers != null && numbers.isEmpty() == false) {
			for (Number number : numbers) {
				totalNum += number.getNumber();
			}
		}

		return totalNum;
	}

	public void init(String url) {
		//String url = "http://www.mocky.io/v2/5acb992e2f00000e004116c2";
		try {
			String json = readEndPoint(url);
			List<Number> numbers = parseNumberData(json);
			double totalSal = calculateTotalNumber(numbers);

			String formatted = formatter.format(totalSal);
			System.out.println("Total Number is : " +formatted); // prints 2.46

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
