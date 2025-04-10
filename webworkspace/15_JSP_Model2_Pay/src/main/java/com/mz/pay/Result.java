package com.mz.pay;

public class Result {
	
	
	public int getEarn() {
		return earn;
	}
	public void setEarn(int earn) {
		this.earn = earn;
	}
	public int getSpend() {
		return spend;
	}
	public void setSpend(int spend) {
		this.spend = spend;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	int earn;
	int spend;
	int remain;
	
	
	public Result(int earn, int spend, int remain) {
		super();
		this.earn = earn;
		this.spend = spend;
		this.remain = remain;
	}
	
	public Result() {
		// TODO Auto-generated constructor stub
	}
	
	
}
