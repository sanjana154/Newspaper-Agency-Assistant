package billtable;

public class UserBean3
{
  String mobile,days,dob,amount;
  public UserBean3(){}
public UserBean3(String mobile, String days, String dob, String amount) {
	super();
	this.mobile = mobile;
	this.days = days;
	this.dob = dob;
	this.amount = amount;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getDays() {
	return days;
}
public void setDays(String days) {
	this.days = days;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
  
}
