import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WMain {
public static void main(String[] args) {
	
	
	// API를 사용
	// open API  // 우리에게 없는 것을 빌려쓰는것
	
	
	// 날씨 데이터를 얻어서 그걸 보여주고 싶음
	// seoul -> 11도, tokyo -> 15도
	// 0adf12afa81124c01ee9e988ff2046e8
	
	// https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}

// https://api.openweathermap.org/data/2.5/weather?q=osaka&appid=0adf12afa81124c01ee9e988ff2046e8
	
	
	// 위의 주소로 엔터치면 결과 자료를 얻을 수 있음.
	// 우리는 자바로 저걸 해서 콘솔에 결과를 출력할 수 있게 해야 함.
	
	
	Scanner sc = new Scanner(System.in);
	System.out.println("city : ");
	String city = sc.next();

	String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=0adf12afa81124c01ee9e988ff2046e8&units=metric";

	try { //url같은 의미를 지니기 위해서 아래처럼 써준것
		
		
		
		
		
		URL u = new URL(url);
		HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
		// url을 오픈, 연결한 것 
		// https > secure < 보안이 강화된 형태
		
		InputStream is = huc.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		System.out.println(isr);
		
		
		
		// 인터넷 상에서 자료를 주거니 받거니 할때 약속된 문법이 있다면 서로 편할 것 (xml, json)
		// json > 범용 포맷
		
		// json parse > 파싱 : 원하는 데이터만 추출
		// json library (도구)
		// mvnrepo 에서 json simple.jar 다운
		
		// build path -> library spec > external 추가.
		
		// 파싱하기 위해 파서객체 생성
		
		JSONParser jp = new JSONParser();
		JSONObject weatherData = (JSONObject) jp.parse(isr);
		
		// System.out.println(weatherData);
		
		// javascript 에서 
		// {} 객체 
		// [] 배열
		JSONArray	weather = (JSONArray) weatherData.get("weather");
		JSONObject wo = (JSONObject) weather.get(0);
		
		
		// description
		System.out.println(wo.get("description"));
		// city
		System.out.println(weatherData.get("name"));
		// country
		JSONObject sys = (JSONObject) weatherData.get("sys");
		System.out.println(sys.get("country"));
		
		// temp
		JSONObject main = (JSONObject) weatherData.get("main");
		System.out.println(main.get("temp"));	
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
	
	
}
}
