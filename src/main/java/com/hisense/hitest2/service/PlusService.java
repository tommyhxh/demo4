package com.hisense.hitest2.service;
/**
 * @cmptId 1320538766611316984
 * @dmId 1320636827157135608
 * @author saasp-de
 * @date today
 */

import com.alibaba.fastjson.JSONObject;
import com.hisense.orm.annotation.Relation;
import com.hisense.orm.annotation.RelationType;
import com.hisense.orm.dao.BaseDao;
import com.hisense.orm.service.DaoContext;
import com.hisense.orm.sql.TableUtils;
import com.hisense.orm.sql.inf.IOrder;
import com.hisense.hitest2.pojo.Plus;
import com.hisense.hitest2.pojo.CommonInput;
import com.hisense.hitest2.pojo.UpLoadDataInput;
import com.hisense.hitest2.pojo.GetProductNameInParam;
import com.hisense.hitest2.pojo.DownLoadDataOutput;
import com.hisense.hitest2.dao.PlusDao;
import com.hisense.bcommon.utils.ConvertObjUtil;
import com.hisense.bcommon.utils.HiWebResult;
import com.hisense.hitest2.utils.OrmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


@Service
public class PlusService {
    private static final Logger log = LoggerFactory.getLogger(PlusService.class);

    @Autowired
    PlusDao plusDao;
    @Autowired
    OrmUtil ormUtil;


    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Object addUsedByBase(CommonInput clz) {
        Plus plus = ConvertObjUtil.copyProperties(clz, Plus.class);
        ormUtil.doInsertRelation(plus);
        plusDao.insertOne(plus);
        return HiWebResult.success(ConvertObjUtil.copyProperties(plus,GetProductNameInParam.class));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Object updateUsedByBase(UpLoadDataInput clz) {
        Plus plus = ConvertObjUtil.copyProperties(clz, Plus.class);
        ormUtil.doUpdateRelation(plus);
        plusDao.updateById(plus);
        return HiWebResult.success(ConvertObjUtil.copyProperties(plus,DownLoadDataOutput.class));
    }
}
