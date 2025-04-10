package com.mz.conv;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public class Model {

	public static void calc(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("utf-8");

		String unit = request.getParameter("unit");
		double i = Double.parseDouble(request.getParameter("num"));
		double result = 0.0;

		String label = null;
		String label2 = null;
		String color = null;
		String color2 = null;

		if (unit.equals("a")) {
			result = i * 0.393701;
			label = "cm";
			label2 = "inch";
			color = "#C7C4FF";
			color2 = "#F15F5F";
		} else if (unit.equals("b")) {
			result = i * 0.3025;
			label = "㎡";
			label2 = "평";
			color = "#E0FFD9";
			color2 = "#A566FF";
		} else if (unit.equals("c")) {
			result = i * 33.8;
			label = "℃";
			label2 = "℉";
			color = "#FAECC5";
			color2 = "#D9418C";
		} else {
			result = i * 1000;
			label = "km/h";
			label2 = "mi/h";
			color = "#FFD8D8";
			color2 = "#2F9D27";
		}

		String str = String.format("%.1f", result);

		Vo v = new Vo(color, color2, label, label2, str, i);
		request.setAttribute("vv", v);

	}

}