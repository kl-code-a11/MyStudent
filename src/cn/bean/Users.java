package cn.bean;

public class Users {
	private int id;	
	private String name;
	private int age;
	private String address;
	private String password;
	public String getPasswd() {
		return password;
	}

	public void setPasswd(String password) {
		this.password = password;
	}


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public  int  getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Users [id="+id+",name=" + name +",password="+password+ ",age=" + age + ",address=" + address + "]";
	}
	
	
}
