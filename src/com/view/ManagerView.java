package com.view;

import java.util.List;
import java.util.Scanner;

import com.bean.Grade;
import com.bean.Student;
import com.bean.Users;
import com.dao.StudentDao;
import com.dao.UsersDao;
import com.daoimpl.StudentDaoImpl;
import com.daoimpl.UsersDaoImpl;

public class ManagerView {
	UsersDao ud=new UsersDaoImpl();
	StudentDao sd=new StudentDaoImpl();
	Scanner sc=new Scanner(System.in);
	public void login_view() throws Exception {
		System.out.println("---------------欢迎登陆学生信息系统------------------");
		System.out.println("1、登录\t2、退出");
		System.out.println("------------------------------------------------");
		System.out.println("请选择：");
		int answer1=Integer.parseInt(sc.next());
		
		if(answer1==1) {
			System.out.println("请输入用户名:");
			String account=sc.next();
			System.out.println("请输入密码:");
			String password=sc.next();
			Users user=ud.login(account, password);
			if(user!=null) {
				TeaView();
			}
			
		}
	}
	
	public void TeaView() throws Exception {
		System.out.println("登录成功");
		boolean flag=true;
		a:while(flag) {
			System.out.println("**************请选择要操作的信息的对应数字******************");
			System.out.println("*1.查看学生信息\t2.添加学生信息\t3.删除学生信息\t4.修改学生信息\t5.退出");
			System.out.println("*************************************************************");
			System.out.println("请选择:");
			int answer2=Integer.parseInt(sc.next());
			switch(answer2) {
			case 1:
				//查看学生信息
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
				System.out.println("\t1.查看所有学生信息\t2.根据ID查询学生信息\t3.根据ID查询学生姓名\t返回上级目录\t系统退出");
				System.out.println("请选择菜单:");
				int answer4 = Integer.parseInt(sc.next());
				int id;
				switch(answer4) {
				case 1:
					//查看所有学生信息
					List<Student> stus=sd.findAll();
					stus.forEach(stu->System.out.println(stu));
					break;
				case 2:
					//根据ID查询学生信息
					System.out.println("请输入要查询的ID:");
					id=Integer.parseInt(sc.next());
					Student stu=sd.findById(id);
					System.out.println(stu);
					break;
				case 3:
					//根据ID查询学生姓名
					System.out.println("请输入要查询姓名的ID:");
					id=Integer.parseInt(sc.next());
					Student stu1=sd.findById(id);
					System.out.println(stu1.getName());
					break;
					
				}break;
				
			case 2:
				//添加学生信息
				System.out.println("****************添加学生信息********************");
				while(true) {
				if(addstu()) {
					System.out.println("数据保存成功,系统将自动返回上层目录~");
					break;
				}
				else {
					System.out.println("添加失败，请确认信息");
				}
				}
				break;
				
			case 3:
				//删除学生信息
				System.out.println("输入要删除学生的ID");
				id=Integer.parseInt(sc.next());
				sd.delete(id);
				break;
			case 4:
				//修改学生信息
				System.out.println("-------------------------------------");
				System.out.println("1.根据ID修改学生全部信息\t2.根据ID修改学生部分信息\t3.返回上级目录\t4.系统退出");
				System.out.println("-------------------------------------");
				System.out.println("------------------------------------------");
				System.out.println("请选择:");
				int answer3=Integer.parseInt(sc.next());	
				System.out.println("请输入要修改的ID");
				id=Integer.parseInt(sc.next());
			//通过ID查出对象显示
				Student show=sd.findById(id);
				System.out.println(show.toString());
				switch(answer3) {
				case 1:
					//根据ID修改学生全部信息
				
					if(UpdStuAll(id)) {
						System.out.println("修改成功!");
					}
					break;
				case 2:
					//根据ID修改学生部分信息
					if(UpdatePart(show)) {
						 System.out.println("修改成功!");
					}
				   
				    break;
				case 3:
					//返回上层目录
					break;
				case 4:
					//退出系统
					flag=false;
				}
				break;
				
				default:
					break a;//结束指定循环
			}
		}
	}

	
	private boolean  UpdatePart(Student stu) {
		// TODO 自动生成的方法存根
		System.out.println("请输入你要修改的属性(输入name,sex,age,grade,address,phone,email其中一个)");
		String answer=sc.next();
		switch(answer) {
		case "name":
			System.out.println("请输入要修改的姓名:");
			String uname=sc.next();
			stu.setName(uname);
			break;
		case "sex":
			System.out.println("请输入要修改的性别:");
			String Usex=sc.next();
			stu.setSex(Usex);
		    break;
		case "age":
			System.out.println("请输入要修改的年龄:");
			int Uage=Integer.parseInt(sc.next());
			stu.setAge(Uage);
		case "grade":
			System.out.println("请输入要修改的年级:");
		    int gid=sc.nextInt();
			stu.getGrade().setGid(gid);
			break;
		case "address":
			System.out.println("请输入要修改的地址:");
			String Uaddress=sc.next();
			stu.setAddress(Uaddress);
			break;
		case "phone":
			System.out.println("请输入要修改的号码:");
			String Uphone=sc.next();
			stu.setPhone(Uphone);
		    break;
		case "email":
			System.out.println("请输入要修改的邮箱:");
			String uemail=sc.next();
			stu.setEmail(uemail);
			break;
		}
		boolean flag=sd.update(stu);
		return flag;
		
	}

	public boolean addstu() 
	{

		return sd.save(edit());
	}
	
	public boolean UpdStuAll(int id) 
	{
		Student stu=edit();
		stu.setId(id);//修改需要ID
		return sd.update(stu);
		
	}
	
	public Student edit() {
		System.out.println("请输入学生姓名:");
		String name=sc.next();
		System.out.println("请输入学生性别:");
		String sex=sc.next();
		System.out.println("请输入学生年龄(只能1-120之内的数字):");
		int age=Integer.parseInt(sc.next());
		System.out.println("请输入学生所属的年级(只能初级1、中级2、高级3):");
		int gid=Integer.parseInt(sc.next());
		System.out.println("请输入学生地址:");
		String address=sc.next();
		System.out.println("请输入学生联系方式:");
		String phone=sc.next();
		System.out.println("请输入学生电子邮箱(包含@和.com):");
		String email=sc.next();
		Grade g=new Grade();
		g.setGid(gid);
		Student st=new Student(name,sex,age,g,address,phone,email);
		return st;
	}
}
