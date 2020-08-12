package bean;
//学生的实体类
public class Student {
    private int id;
	private String name;
	private String sex;
	private int age;
	private Grade grade;
	private String address;
	private String phone;
	private String email;
	
	
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Student( String name, String sex, int age, Grade grade, String address, String phone,
			String email) {
	
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.grade = grade;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public Student() {
	
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", age=" + age + ", grade=" + grade
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
	}

	
	
}
