package attr;

import javax.servlet.http.HttpServletRequest;

public class Model {

	public static void calc(HttpServletRequest request) {
		//1. 값 받기
		int price = Integer.parseInt(request.getParameter("p"));
		int money = Integer.parseInt(request.getParameter("m"));
		// 1000 / 5000 원이면 4000원 받아내야 함
		int ex = money - price ;
		int ex2 = price- money;
		
		String say = "잔돈 여기 잇습니당";
				if (price == money) {
					say = "감사합니ㄷㅑ";
				} else if (price > money) {
					say = "돈이 모자릅니당"; 
					ex = price - money;}
					
//				request.setAttribute("price", price);			
//				request.setAttribute("money", money);		
//				request.setAttribute("exchange", ex);			
//				request.setAttribute("say", say);			
//				// 위에처럼 필요한 것들 따로실어서 보내도되는데 
				// 그걸 한데 뭉쳐서 보내자 (객체) 
				
				Result rr = new Result(price,money,ex,say);
				request.setAttribute("rrr", rr);
				request.setAttribute("ex2", ex2);
				}

	public static void makeValue(HttpServletRequest request) {
		int asd = Integer.parseInt(request.getParameter("asd"));
		asd *= -1;
		request.setAttribute("asdasd", asd);
	}

}
