package com.mz.tag.unit;

import javax.servlet.http.HttpServletRequest;

public class Model {

	public static void calc(HttpServletRequest request) {
		
		String num = request.getParameter("number");
		String val = request.getParameter("select");


		double value = 0;
		String unitFrom = "";
		String unitTo = "";
		String result = "";
		String containerClass = "container";
		String spanClass = "";


			value = Double.parseDouble(num);
			
			switch (val) {
		    case "cm":
		        unitFrom = "cm";
		        unitTo = "inch";
		        result = String.format("%.1f", value / 2.54);
		        containerClass += " cm";
		        spanClass = "cm";
		        break;
		    case "m2":
		        unitFrom = "㎡";
		        unitTo = "평";
		        result = String.format("%.1f", value / 3.3058);
		        containerClass += " m2";
		        spanClass = "m2";
		        break;
		    case "cf":
		        unitFrom = "℃";
		        unitTo = "℉";
		        result = String.format("%.1f", (value * 9 / 5) + 32);
		        containerClass += " cf";
		        spanClass = "cf";
		        break;
		    case "km":
		        unitFrom = "km/h";
		        unitTo = "mi/h";
		        result = String.format("%.1f", value * 0.621371);
		        containerClass += " km";
		        spanClass = "km";
		        break;
		    default :
		        break;
		}
		
			
			VO vo = new VO(num, val, value, unitFrom, unitTo, result, containerClass, spanClass);
			request.setAttribute("vo", vo);
			
		
	}

}
