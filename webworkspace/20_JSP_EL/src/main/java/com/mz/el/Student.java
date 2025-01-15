package com.mz.el;

public class Student {
	
	private String name;
	private double mid;
	private double last;
	private double avg;
	private String grade;
public Student() {
	// TODO Auto-generated constructor stub
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getMid() {
	return mid;
}
public void setMid(double mid) {
	this.mid = mid;
}
public double getLast() {
	return last;
}
public void setLast(double last) {
	this.last = last;
}
public double getAvg() {
	return avg;
}
public void setAvg(double avg) {
	this.avg = avg;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public Student(String name, double mid, double last, double avg, String grade) {
	super();
	this.name = name;
	this.mid = mid;
	this.last = last;
	this.avg = avg;
	this.grade = grade;
}



}
