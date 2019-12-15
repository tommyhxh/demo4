package com.hisense.hitest2.utils;
/*
* hxh
* today
*/

import com.hisense.orm.annotation.AdvanceConfig;
import com.hisense.orm.annotation.Relation;
import com.hisense.orm.annotation.RelationType;
import com.hisense.orm.dao.BaseDao;
import com.hisense.orm.service.DaoContext;
import com.hisense.orm.sql.TableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class OrmUtil {

    private static final Logger log = LoggerFactory.getLogger(OrmUtil.class);

    public <T> void doSelectRelation(T clz) {
        AdvanceConfig advanceConfig = DaoContext.getBean(AdvanceConfig.class);
        if (advanceConfig.getRelation()) {
            List<Field> fields = TableUtils.getRelationFields(clz.getClass());
            if (fields != null) {
                for (Field field : fields) {
                    Relation relationOne = field.getAnnotation(Relation.class);
                    if (!relationOne.select()) {
                        continue;
                    }
                    BaseDao tempDao = (BaseDao) DaoContext.getBean(relationOne.mapper());
                    try {
                        if (relationOne.type().equals(RelationType.One)) {
                            field.set(clz, tempDao.selectObjOneByObject(getCreateObject(field, clz, relationOne)));
                        } else {
                            field.set(clz, tempDao.selectObjNoPageList(getCreateObject(field, clz, relationOne), null));
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
    }

    public <T> void doUpdateRelation(T clz) {
        AdvanceConfig advanceConfig = DaoContext.getBean(AdvanceConfig.class);
        if (advanceConfig.getRelation()) {
            List<Field> fields = TableUtils.getRelationFields(clz.getClass());
            if (fields != null) {
                for (Field field : fields) {
                    Relation relationOne = field.getAnnotation(Relation.class);
                    try {
                        if (!relationOne.update() || field.get(clz) == null) {
                            continue;
                        }
                        BaseDao tempDao = (BaseDao) DaoContext.getBean(relationOne.mapper());

                        if (relationOne.type().equals(RelationType.One)) {
                            tempDao.updateById(getUpdateObject(field, clz, relationOne));
                        } else {
                            tempDao.deleteByObject(getCreateObject(field, clz, relationOne));
                            tempDao.insertAll((List) getUpdateObject(field, clz, relationOne));
                        }

                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new RuntimeException("doUpdateRelation");
                    }
                }
            }
        }
    }

    public <T> void doInsertRelation(T clz) throws RuntimeException {
        AdvanceConfig advanceConfig = DaoContext.getBean(AdvanceConfig.class);
        if (advanceConfig.getRelation()) {
            List<Field> fields = TableUtils.getRelationFields(clz.getClass());
            if (fields != null) {
                for (Field field : fields) {
                    Relation relationOne = field.getAnnotation(Relation.class);
                    try {
                        if (!relationOne.add() || field.get(clz) == null) {
                            continue;
                        }
                        BaseDao tempDao = (BaseDao) DaoContext.getBean(relationOne.mapper());
                        if (relationOne.type().equals(RelationType.One)) {
                            tempDao.insertOne(getUpdateObject(field, clz, relationOne));
                        } else {
                            tempDao.insertAll((List) getUpdateObject(field, clz, relationOne));
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new RuntimeException("doInsertRelation");
                    }
                }
            }
        }
    }

    public <T> void doDelRelation(T clz) {
        AdvanceConfig advanceConfig = DaoContext.getBean(AdvanceConfig.class);
        if (advanceConfig.getRelation()) {
            List<Field> fields = TableUtils.getRelationFields(clz.getClass());
            if (fields != null) {
                for (Field field : fields) {
                    Relation relationOne = field.getAnnotation(Relation.class);
                    if (!relationOne.del()) {
                        continue;
                    }
                    BaseDao tempDao = (BaseDao) DaoContext.getBean(relationOne.mapper());
                    try {
                        tempDao.deleteByObject(getCreateObject(field, clz, relationOne));
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new RuntimeException("doDelRelation");
                    }
                }
            }
        }
    }

    public Object getCreateObject(Field field, Object clz, Relation relationOne) throws Exception {
        //获取master字段的方法
        Method masterMethod = TableUtils.fieldToGetMethod(clz.getClass(),
                relationOne.masterColumn());
        //获取master字段的值
        Object value = masterMethod.invoke(clz);
        Object obj;
        Method detailMethod;
        if (relationOne.type().equals(RelationType.One)) {
            obj = field.getType().newInstance();
            detailMethod =
                    TableUtils.fieldToSetMethod(field.getType(), relationOne.detailColumn());
        } else {
            Type t = field.getGenericType();
            ParameterizedType pt = (ParameterizedType) t;
            Class one = (Class) pt.getActualTypeArguments()[0];
            obj = one.newInstance();
            detailMethod =
                    TableUtils.fieldToSetMethod(one, relationOne.detailColumn());
        }
        detailMethod.invoke(obj, value);
        return obj;
    }

    public Object getUpdateObject(Field field, Object clz, Relation relationOne) throws Exception {
        //获取master字段的方法
        Method masterMethod = TableUtils.fieldToGetMethod(clz.getClass(),
                relationOne.masterColumn());
        //获取detail对象的方法
        Method detailObjMethod = TableUtils.fieldToGetMethod(clz.getClass(),
                field.getName());
        Object detailObj;
        ///获取detail对象
        detailObj = detailObjMethod.invoke(clz);
        ///获取detail关联字段方法
        if (relationOne.type().equals(RelationType.One)) {
            Method detailMethod =
                    TableUtils.fieldToSetMethod(field.getType(), relationOne.detailColumn());
            //设置关联的字段值
            detailMethod.invoke(detailObj, masterMethod.invoke(clz));
        } else {
            Type t = field.getGenericType();
            ParameterizedType pt = (ParameterizedType) t;
            Class one = (Class) pt.getActualTypeArguments()[0];
            ///获取detail关联字段方法
            Method detailMethod =
                    TableUtils.fieldToSetMethod(one, relationOne.detailColumn());
            for (Object temp : (List) detailObj) {
                //设置关联的字段值
                detailMethod.invoke(temp, masterMethod.invoke(clz));
            }
        }
        return detailObj;
    }
}
