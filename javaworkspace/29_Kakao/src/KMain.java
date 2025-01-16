import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class KMain {
    public static void main(String[] args) {

        // 카카오 개발자

        // 688c5a776f44ccfbdf77431a55bc7319
        // https://dapi.kakao.com/v3/search/book

        try {
            String url = "https://dapi.kakao.com/v3/search/book?query=1973";
            URL u = new URL(url);
            HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();

            huc.addRequestProperty("Authorization", "KakaoAK 688c5a776f44ccfbdf77431a55bc7319");

            InputStream is = huc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");

            // json parsing
            JSONParser jp = new JSONParser();
            JSONObject kakaoData = (JSONObject) jp.parse(isr);
            System.out.println(kakaoData);
            
            
            
            // 제목
            JSONArray documents = (JSONArray) kakaoData.get("documents");
            for (int i = 0; i < documents.size(); i++) {
				JSONObject book =  (JSONObject) documents.get(i);
				System.out.println("======================");
				System.out.println("책 제목 : " + book.get("title"));
				// 출판사
				System.out.println("출판사 : " + book.get("publisher"));
				// 저자
				
				JSONArray authors = (JSONArray) book.get("authors"); // 배열에 접근
				System.out.print("저자 : ");
				for (int i1 = 0; i1 < authors.size(); i1++) {
						System.out.print(authors.get(i1));
						if (authors.size() != 1 && authors.size()-1 != i1) {
							System.out.print(", ");
						}
					}
				
				
				
		        
				
				// 가격
				System.out.println("\n가격 : " + book.get("price"));
				// 상태 
				System.out.println("상태 : " + book.get("status"));
				
			}
        } catch (Exception e) {
            e.printStackTrace();
        }


}}