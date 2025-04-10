import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NMain {
    public static void main(String[] args) {

        // 네이버 개발자 센터
        // open API -> 인증

        // l5Ly_DC7S4LTSvUPOzlB
        // JRzcT4X2D6

        // http://openapi.naver.com/v1/search/shop.json

    	
    	Scanner sc = new Scanner(System.in);

    	
    	try {
    	System.out.println("검색어 : ");
    	String str = sc.next();
    	str = URLEncoder.encode(str,"utf-8"); //인코딩해야함
    	
    	System.out.println(str);
    	
    	
        String url = "https://openapi.naver.com/v1/search/shop.json?query=";
        url += str;
        
        
        
        
        System.out.println(url);

            URL u = new URL(url);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();

            huc.addRequestProperty("X-Naver-Client-Id", "l5Ly_DC7S4LTSvUPOzlB");
            huc.addRequestProperty("X-Naver-Client-Secret", "JRzcT4X2D6");

            InputStream is = huc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");

            // json parse (원하는 데이터만 추출)
            JSONParser jp = new JSONParser();

            // javascript
            // {} 객체
            // [] 배열

            JSONObject naverData = (JSONObject) jp.parse(isr);
            System.out.println(naverData);

            // 품명
            // 가격
            // 브랜드
            JSONArray items = (JSONArray) naverData.get("items");

            for (int i = 0; i < items.size(); i++) {
                JSONObject coffee = (JSONObject) items.get(i);
//                String title = (String) coffee.get("title");
                String title = coffee.get("title").toString();
                title = title.replace("<b>", "");
                title = title.replace("</b>", "");
                System.out.println("품명 : " + title);
                System.out.println("가격 : " + coffee.get("lprice"));
                System.out.println("브랜드 : " + coffee.get("brand"));
                System.out.println("=========================");
      
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}