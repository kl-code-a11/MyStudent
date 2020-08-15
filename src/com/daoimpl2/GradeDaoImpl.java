package com.daoimpl2;
import java.sql.ResultSet;

import com.bean.Grade;
import com.dao.GradeDao;
import com.util.DBUtil;

public class GradeDaoImpl extends DBUtil<Grade> implements GradeDao {



	@Override
	public Grade getEntity(ResultSet rs) throws Exception {
		// TODO 自动生成的方法存根
		Grade grade=new Grade();
		grade.setGid(rs.getInt(1));
		grade.setGname(rs.getString(2));
		return grade;
	}

	@Override
	public Grade findById(int gid) throws Exception{
		// TODO 自动生成的方法存根
		String sql="select * from grade where gid=?";
		return Query(sql, gid).get(0);
		
	}

}
