package com.dao;

import com.bean.Grade;

public interface GradeDao {
	
     Grade findById(int gid) throws Exception;
}
