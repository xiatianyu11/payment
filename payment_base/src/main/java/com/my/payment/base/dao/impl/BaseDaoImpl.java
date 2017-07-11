package com.my.payment.base.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.my.payment.base.dao.BaseDao;
import com.my.payment.base.dao.mybatis.DynamicSql;
import com.my.payment.base.dao.mybatis.model.TimeStampConverter;
import com.my.payment.base.dto.BaseDto;
import com.my.payment.base.dto.PageDto;
import com.my.payment.base.dto.Pagination;



public abstract class BaseDaoImpl<T extends BaseDto> extends SqlSessionDaoSupport implements BaseDao<T> {

    protected final Logger logger = Logger.getLogger(this.getClass());
    
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    
    @PostConstruct
    public void initSqlSessionFactory() {
    	super.setSqlSessionFactory(sqlSessionFactory);
    }


    public List<T> getAll(final T object) {

        String tableName =  MyBatisDaoUtils.getTableName(object);

        return getAll(object, tableName);

    }
    
    @SuppressWarnings("unchecked")
    private List<T> getAll(final T object, String tableName) {

        ConvertUtils.register(new TimeStampConverter(), java.sql.Timestamp.class);

        List<HashMap> list = getSqlSession().selectList("GenericSql.SelectObjectVOSql",
            DynamicSql.getInstance().SelectSql(object, tableName));
        return DynamicSql.getInstance().ListHashToListbean(list, object);
    }
    

    public T getRow(final T object) {


        String tableName =  MyBatisDaoUtils.getTableName(object);

        return getRow(object, tableName);
    }


    @SuppressWarnings("unchecked")
    private T getRow(final T object, String tableName) {

        List list = getAll(object, tableName);

        if (list != null && list.size() > 0) {

            T ob = (T) list.get(0);

            return ob;

        }

        return null;
    }

    public boolean exists(final T object) {


        String tableName =  MyBatisDaoUtils.getTableName(object);

        return exists(object, tableName);

    }

    private boolean exists(final T object, String tableName) {

        Integer countNum = (Integer) getSqlSession().selectOne(
            "GenericSql.CountSelectObjectVOSql",
            DynamicSql.getInstance().SelectCountSql(object, tableName));

        if (countNum > 0) {

            return true;

        }

        return false;
    }

    public T save(final T object) {

        String tableName =  MyBatisDaoUtils.getTableName(object);

        return save(object, tableName);
    }

    private T save(final T object, String tableName) {


        Object primaryKeyValue = MyBatisDaoUtils.getPrimaryKeyValue(object);
        
        getSqlSession().insert("GenericSql.InsertObjectVOSql",
                DynamicSql.getInstance().InsertSql(object, tableName, primaryKeyValue));

        return object;
    }

    public Integer updatePK(T object) {

        String tableName =  MyBatisDaoUtils.getTableName(object);

        return updatePK(object, tableName);

    }

    private Integer updatePK(T object, String tableName) {


        // check for null id
        return getSqlSession().update("GenericSql.UpdateObjectVOSql",
            DynamicSql.getInstance().UpdatebeanToSqlPk(object, tableName));

    }

    public void delete(T object) {

        String tableName =  MyBatisDaoUtils.getTableName(object);

        delete(object, tableName);
    }

    private void delete(T object, String tableName) {

        getSqlSession().delete("GenericSql.DeleteObjectVOSql",
            DynamicSql.getInstance().DeletebeanToSql(object, tableName));
    }


    public void updateBatchPK(T object, List<Long> listPK) {

        String tableName =  MyBatisDaoUtils.getTableName(object);


        updateBatchPK(object, listPK, tableName);
    }

    private Integer updateBatchPK(T object, List listPK, String tableName) {


        // check for null id
        return getSqlSession().update("GenericSql.UpdateObjectVOSqlBatch",
            DynamicSql.getInstance().UpdateBatchbeanToSqlPk(object, listPK, tableName));

    }
    
    
    public Pagination findObjectsWithPg(PageDto pageDto){
    	return this.findObjectsWithPg("GenericSql.SelectObjectVOSql", pageDto);
    }

    @SuppressWarnings("unchecked")
    private Pagination findObjectsWithPg(String statementName, PageDto pageDto) {
    	String tableName =  MyBatisDaoUtils.getTableName(pageDto.getDto());
        Map map = DynamicSql.getInstance().SelectSql(pageDto.getDto(), tableName);
        Object count = getSqlSession().selectOne(
            MyBatisDaoUtils.getCountQueryFullName(statementName), map);
        int totalCount = Integer.parseInt(count.toString());

        pageDto.setTotalCount(totalCount);
        pageDto.calStart();

        map.put("start", pageDto.getStart());
        map.put("end", pageDto.getEnd());

        List rstList = getSqlSession().selectList(statementName, map);

        Pagination pg = new Pagination();

        try {
            BeanUtils.copyProperties(pg, pageDto);
        } catch (IllegalAccessException e) {

            logger.error("", e);

            throw new RuntimeException();

        } catch (InvocationTargetException e) {

            logger.error("", e);
            throw new RuntimeException();

        }

        pg.setTotalCount(totalCount);

        pg.setResultList(rstList);

        return pg;
    }

}
