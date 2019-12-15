package com.hisense.hitest2.dao;
/**
 * @cmptId 1320538766611316984
 * @dmId 1320538828888342776
 * @author saasp-de
 * @date today
 */
import com.hisense.orm.dao.BaseDao;
import com.hisense.hitest2.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseDao<User> {
}