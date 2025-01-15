package com.mz.tag.unit;

public class VO {
	
	private String num;
	private String val;

	private double value;
	private String unitFrom;
	private String unitTo;
	private String result;
	private String containerClass;
	private String spanClass;
	
	
	public VO() {
		// TODO Auto-generated constructor stub
	}


	public VO(String num, String val, double value, String unitFrom, String unitTo, String result,
			String containerClass, String spanClass) {
		super();
		this.num = num;
		this.val = val;
		this.value = value;
		this.unitFrom = unitFrom;
		this.unitTo = unitTo;
		this.result = result;
		this.containerClass = containerClass;
		this.spanClass = spanClass;
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getVal() {
		return val;
	}


	public void setVal(String val) {
		this.val = val;
	}


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	public String getUnitFrom() {
		return unitFrom;
	}


	public void setUnitFrom(String unitFrom) {
		this.unitFrom = unitFrom;
	}


	public String getUnitTo() {
		return unitTo;
	}


	public void setUnitTo(String unitTo) {
		this.unitTo = unitTo;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getContainerClass() {
		return containerClass;
	}


	public void setContainerClass(String containerClass) {
		this.containerClass = containerClass;
	}


	public String getSpanClass() {
		return spanClass;
	}


	public void setSpanClass(String spanClass) {
		this.spanClass = spanClass;
	}
	
	
}
