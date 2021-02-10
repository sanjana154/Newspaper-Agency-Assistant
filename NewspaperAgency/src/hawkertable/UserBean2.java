package hawkertable;

public class UserBean2
{
	String name,mobile,address,areas,salary,doj;
	public UserBean2(){}
	public UserBean2(String name, String mobile, String address, String areas, String salary, String doj) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
		this.areas = areas;
		this.salary = salary;
		this.doj = doj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	
	


}

