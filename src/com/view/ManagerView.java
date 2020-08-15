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
		System.out.println("---------------��ӭ��½ѧ����Ϣϵͳ------------------");
		System.out.println("1����¼\t2���˳�");
		System.out.println("------------------------------------------------");
		System.out.println("��ѡ��");
		int answer1=Integer.parseInt(sc.next());
		
		if(answer1==1) {
			System.out.println("�������û���:");
			String account=sc.next();
			System.out.println("����������:");
			String password=sc.next();
			Users user=ud.login(account, password);
			if(user!=null) {
				TeaView();
			}
			
		}
	}
	
	public void TeaView() throws Exception {
		System.out.println("��¼�ɹ�");
		boolean flag=true;
		a:while(flag) {
			System.out.println("**************��ѡ��Ҫ��������Ϣ�Ķ�Ӧ����******************");
			System.out.println("*1.�鿴ѧ����Ϣ\t2.���ѧ����Ϣ\t3.ɾ��ѧ����Ϣ\t4.�޸�ѧ����Ϣ\t5.�˳�");
			System.out.println("*************************************************************");
			System.out.println("��ѡ��:");
			int answer2=Integer.parseInt(sc.next());
			switch(answer2) {
			case 1:
				//�鿴ѧ����Ϣ
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
				System.out.println("\t1.�鿴����ѧ����Ϣ\t2.����ID��ѯѧ����Ϣ\t3.����ID��ѯѧ������\t�����ϼ�Ŀ¼\tϵͳ�˳�");
				System.out.println("��ѡ��˵�:");
				int answer4 = Integer.parseInt(sc.next());
				int id;
				switch(answer4) {
				case 1:
					//�鿴����ѧ����Ϣ
					List<Student> stus=sd.findAll();
					stus.forEach(stu->System.out.println(stu));
					break;
				case 2:
					//����ID��ѯѧ����Ϣ
					System.out.println("������Ҫ��ѯ��ID:");
					id=Integer.parseInt(sc.next());
					Student stu=sd.findById(id);
					System.out.println(stu);
					break;
				case 3:
					//����ID��ѯѧ������
					System.out.println("������Ҫ��ѯ������ID:");
					id=Integer.parseInt(sc.next());
					Student stu1=sd.findById(id);
					System.out.println(stu1.getName());
					break;
					
				}break;
				
			case 2:
				//���ѧ����Ϣ
				System.out.println("****************���ѧ����Ϣ********************");
				while(true) {
				if(addstu()) {
					System.out.println("���ݱ���ɹ�,ϵͳ���Զ������ϲ�Ŀ¼~");
					break;
				}
				else {
					System.out.println("���ʧ�ܣ���ȷ����Ϣ");
				}
				}
				break;
				
			case 3:
				//ɾ��ѧ����Ϣ
				System.out.println("����Ҫɾ��ѧ����ID");
				id=Integer.parseInt(sc.next());
				sd.delete(id);
				break;
			case 4:
				//�޸�ѧ����Ϣ
				System.out.println("-------------------------------------");
				System.out.println("1.����ID�޸�ѧ��ȫ����Ϣ\t2.����ID�޸�ѧ��������Ϣ\t3.�����ϼ�Ŀ¼\t4.ϵͳ�˳�");
				System.out.println("-------------------------------------");
				System.out.println("------------------------------------------");
				System.out.println("��ѡ��:");
				int answer3=Integer.parseInt(sc.next());	
				System.out.println("������Ҫ�޸ĵ�ID");
				id=Integer.parseInt(sc.next());
			//ͨ��ID���������ʾ
				Student show=sd.findById(id);
				System.out.println(show.toString());
				switch(answer3) {
				case 1:
					//����ID�޸�ѧ��ȫ����Ϣ
				
					if(UpdStuAll(id)) {
						System.out.println("�޸ĳɹ�!");
					}
					break;
				case 2:
					//����ID�޸�ѧ��������Ϣ
					if(UpdatePart(show)) {
						 System.out.println("�޸ĳɹ�!");
					}
				   
				    break;
				case 3:
					//�����ϲ�Ŀ¼
					break;
				case 4:
					//�˳�ϵͳ
					flag=false;
				}
				break;
				
				default:
					break a;//����ָ��ѭ��
			}
		}
	}

	
	private boolean  UpdatePart(Student stu) {
		// TODO �Զ����ɵķ������
		System.out.println("��������Ҫ�޸ĵ�����(����name,sex,age,grade,address,phone,email����һ��)");
		String answer=sc.next();
		switch(answer) {
		case "name":
			System.out.println("������Ҫ�޸ĵ�����:");
			String uname=sc.next();
			stu.setName(uname);
			break;
		case "sex":
			System.out.println("������Ҫ�޸ĵ��Ա�:");
			String Usex=sc.next();
			stu.setSex(Usex);
		    break;
		case "age":
			System.out.println("������Ҫ�޸ĵ�����:");
			int Uage=Integer.parseInt(sc.next());
			stu.setAge(Uage);
		case "grade":
			System.out.println("������Ҫ�޸ĵ��꼶:");
		    int gid=sc.nextInt();
			stu.getGrade().setGid(gid);
			break;
		case "address":
			System.out.println("������Ҫ�޸ĵĵ�ַ:");
			String Uaddress=sc.next();
			stu.setAddress(Uaddress);
			break;
		case "phone":
			System.out.println("������Ҫ�޸ĵĺ���:");
			String Uphone=sc.next();
			stu.setPhone(Uphone);
		    break;
		case "email":
			System.out.println("������Ҫ�޸ĵ�����:");
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
		stu.setId(id);//�޸���ҪID
		return sd.update(stu);
		
	}
	
	public Student edit() {
		System.out.println("������ѧ������:");
		String name=sc.next();
		System.out.println("������ѧ���Ա�:");
		String sex=sc.next();
		System.out.println("������ѧ������(ֻ��1-120֮�ڵ�����):");
		int age=Integer.parseInt(sc.next());
		System.out.println("������ѧ���������꼶(ֻ�ܳ���1���м�2���߼�3):");
		int gid=Integer.parseInt(sc.next());
		System.out.println("������ѧ����ַ:");
		String address=sc.next();
		System.out.println("������ѧ����ϵ��ʽ:");
		String phone=sc.next();
		System.out.println("������ѧ����������(����@��.com):");
		String email=sc.next();
		Grade g=new Grade();
		g.setGid(gid);
		Student st=new Student(name,sex,age,g,address,phone,email);
		return st;
	}
}
