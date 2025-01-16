package pay;

import javax.servlet.http.HttpServletRequest;

public class Model {

	public static void calc(HttpServletRequest request) {
		// 1. 값 받기 
		int earn = Integer.parseInt(request.getParameter("earn"));
		int spend = Integer.parseInt(request.getParameter("spend"));
		System.out.println(earn);
		System.out.println(spend);
		
		//  로직처리
		
		int remain = earn - spend;
		
		//결과에서 뭘쓸지?
		//값 뭉쳐서 한번에 보내자 (객체)
		Result r = new Result(earn,spend,remain);
		request.setAttribute("rr", r);
	}

}
