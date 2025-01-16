import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class UMain02 {
public static void main(String[] args) {
	// 시간 / 날짜 
	Date d = new Date();
	System.out.println(d); // 현재 정보 // 날짜 객체 생성한것
	
	
	// date -> string으로 변환하고 싶음 
	// y M d a h m s
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM월 / dd일 hh:mm:ss");
	System.out.println(sdf.format(d));
	// 날짜 객체를 원하는 포맷으로 스트링화해서 추출한 것임
	
	String s = sdf.format(d);
	System.out.println(s);
	
	// String -> Date  >> parse 문자를 날짜로 바꾼다
	String s2 = "01/11-21";
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM-yy");
	try {
		Date d2 = sdf2.parse(s2);
		System.out.println(d2);
	} catch (ParseException e) {
		e.printStackTrace();
	
	}
	
	// Date -> SimpleDateFormat 으로 형식 조절해서 사용
	// sdf.format() => Date -> String 
	// sdf.parse() => String -> Date
	// 년 - 월 - 일  ex ) 2024-10-22 출력

	SimpleDateFormat sk = new SimpleDateFormat("yyyy-MM-dd");
	System.out.println(sk.format(d)); //현재 정보를 표기한게 d니까 패턴을 정해준것.

	
	////////////////////////
	System.out.println("=================");
	LocalDate now = LocalDate.now();
	System.out.println(now);
	System.out.println(now.getYear());
	System.out.println(now.getMonth());
	System.out.println(now.getMonthValue()); // 숫자 월 출력
	System.out.println(now.getDayOfMonth()); 
	
	// 2024/11/22

	LocalDateTime time = LocalDateTime.now();
	System.out.println(time);

}
}
