package com.hisense.hitest2.dao;
    /**
     * @cmptId 1320538766611316984
     * @dmId 1320636827157135608
     * @author saasp-de
     * @date today
     */
import com.hisense.orm.dao.BaseDao;
import com.hisense.hitest2.pojo.Plus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface PlusDao extends BaseDao<Plus> {
}