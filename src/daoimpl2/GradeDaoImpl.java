package daoimpl2;


import java.sql.ResultSet;
import daoimpl2.DBUtil;
import bean.Grade;
import dao.GradeDao;

public class GradeDaoImpl extends DBUtil<Grade> implements GradeDao {



	@Override
	public Grade getEntity(ResultSet rs2) throws Exception {
		// TODO �Զ����ɵķ������
		Grade grade=new Grade();
		grade.setGid(rs.getInt(1));
		grade.setGname(rs.getString(2));
		return grade;
	}

	@Override
	public Grade findById(int gid) throws Exception{
		// TODO �Զ����ɵķ������
		String sql="select * from grade where gid=?";
		return Query(sql, gid).get(0);
		
	}

}
