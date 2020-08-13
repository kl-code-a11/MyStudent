package dao;

import java.util.List;

import bean.Student;

public interface StudentDao {

	
	List<Student> findAll();
	boolean save(Student student);
	boolean update(Student student);
	boolean delete(int id);
	Student findById(int id) throws Exception;
	
	
}
