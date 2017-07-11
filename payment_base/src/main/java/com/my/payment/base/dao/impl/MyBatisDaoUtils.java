package com.my.payment.base.dao.impl;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.util.ClassUtils;

import com.my.payment.base.annotations.AddParentClass;
import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.BaseDto;
import com.my.payment.base.exception.BusinessException;



public final class MyBatisDaoUtils {

    protected final static Logger logger = Logger.getLogger(MyBatisDaoUtils.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private MyBatisDaoUtils() {
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param o
     * @return
     */
    public static String getPrimaryKeyFieldName(BaseDto o) {

        EntityPK entity = o.getClass().getAnnotation(EntityPK.class);

        if (entity == null) {

            return null;
        }

        return "".equals(entity.Pk()) ? null : entity.Pk();
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param o
     * @return
     */
    public static boolean getDefaultColumnIdentity(Object o) {

        EntityPK entity = o.getClass().getAnnotation(EntityPK.class);

        if (entity == null) {

            return false;
        }

        return entity.defaultColumn();
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param o
     * @return  the value as an Object
     */
    public static Object getPrimaryKeyValue(BaseDto o) {

        String fieldName = getPrimaryKeyFieldName(o);

        if (fieldName == null) {

            return null;
        }

        String getterMethod = "get" + Character.toUpperCase(fieldName.charAt(0))
                              + fieldName.substring(1);

        try {

            Method getMethod = o.getClass().getMethod(getterMethod, (Class[]) null);

            return getMethod.invoke(o, (Object[]) null);

        } catch (Exception e) {

            e.printStackTrace();

            logger.error("Could not invoke method '" + getterMethod + "' on "
                         + ClassUtils.getShortName(o.getClass()));

            throw new BusinessException("");
        }
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param o
     * @param value
     */
    @SuppressWarnings("unchecked")
    public static void setPrimaryKey(BaseDto o, Object value) {

        String fieldName = getPrimaryKeyFieldName(o);

        try {
            EntityPK entity = o.getClass().getAnnotation(EntityPK.class);
            Class clazz = o.getClass().getDeclaredField(entity.Pk()).getType();

            String setMethodName = "set" + Character.toUpperCase(fieldName.charAt(0))
                                   + fieldName.substring(1);

            Method setMethod = o.getClass().getMethod(setMethodName, clazz);
            if (value != null) {
                setMethod.invoke(o, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Could not set '{0}.{1}' with value {2}", ClassUtils
                .getShortName(o.getClass()), fieldName, value));
            throw new BusinessException("D0000003");
        }
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param ob
     * @param tableName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getTableName(Object ob, String tableName) {

        if (tableName == null) {

            tableName = ClassUtils.getShortName(ob.getClass());

        }

        return tableName;

    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param queryName
     * @return
     */
    public static String getCountQuery(String queryName) {
        return "Count" + Character.toUpperCase(queryName.charAt(0)) + queryName.substring(1);
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param queryName
     * @return
     */
    public static String getCountQueryFullName(String queryName) {
        int index = queryName.lastIndexOf('.');
        if (index < 0) {
            return getCountQuery(queryName);
        } else {
            String parttwo = queryName.substring(index + 1);
            return queryName.substring(0, index) + "." + getCountQuery(parttwo);
        }
    }


    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param o
     * @return
     */
    public static String getTableName(Object o) {

        EntityPK entity = o.getClass().getAnnotation(EntityPK.class);

        if (entity == null || "".equals(entity.tableName())) {

            logger.error(MessageFormat.format("Could not set '{0}' tableName", ClassUtils
                .getShortName(o.getClass())));
            throw new BusinessException("D0000009");
        }

        return entity.tableName();
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param o
     * @return
     */
    public static boolean getAddParentClass(Object o) {

        AddParentClass entity = o.getClass().getAnnotation(AddParentClass.class);

        if (entity == null || "".equals(entity.AddParentClass())) {

            logger.warn("not set AddParentClass on dto");

            return false;
        }

        return entity.AddParentClass();
    }

    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param o
     * @return
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public static Object getSetDefaultValueClass(Object o) {

        AddParentClass entity = o.getClass().getAnnotation(AddParentClass.class);

        if (entity == null || "".equals(entity.DefaultValueClass())) {

            logger.error(" not set DefaultValueClass value  ");
            throw new BusinessException("D0000009");
        }

        try {

            return Class.forName(entity.DefaultValueClass()).newInstance();

        } catch (Exception e) {

            BusinessException e_b = new BusinessException(e.getMessage());

            throw e_b;
        }
    }

}
