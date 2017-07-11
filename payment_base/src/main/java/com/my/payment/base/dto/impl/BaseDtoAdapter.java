package com.my.payment.base.dto.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.my.payment.base.dao.impl.MyBatisDaoUtils;
import com.my.payment.base.dto.BaseDto;


public abstract class BaseDtoAdapter extends BaseDtoImpl {

    /**
     * <pre>
     * 
     * </pre>
     */
    private static final long serialVersionUID = -5667483166249714826L;
    public static final int FLAG_NO        = 0;
    public static final int FLAG_YES       = 1;

    public static final int IS_DELETE_NO   = 0;
    public static final int IS_DELETE_YES  = 1;

    private String          patitionPrefix = null;

    public String toString() {

        StringBuffer propBuffer = new StringBuffer();

        Field[] fields = this.getClass().getDeclaredFields();

        propBuffer.append("[").append(this.getClass()).append("]");

        for (Field field : fields) {

            String fieldName = field.getName();

            Object fieldValue = null;

            String getterMethod = "get" + Character.toUpperCase(fieldName.charAt(0))
                                  + fieldName.substring(1);
            try {
                Method getMethod = this.getClass().getMethod(getterMethod, (Class[]) null);

                Object o = getMethod.invoke(this, (Object[]) null);

                if (o instanceof Map || o instanceof List) {

                    continue;
                }

                if (o instanceof BaseDto) {
                    fieldName += "Id";
                    fieldValue = MyBatisDaoUtils.getPrimaryKeyValue((BaseDto) o);
                } else {
                    fieldValue = o;
                }

                propBuffer.append(fieldName).append(":").append(fieldValue).append(",");

            } catch (Exception e) {
            }

        }

        return propBuffer.substring(0, propBuffer.length() - 1);
    }

    public String getPatitionPrefix() {
        return patitionPrefix;
    }

    public void setPatitionPrefix(String patitionPrefix) {
        this.patitionPrefix = patitionPrefix;
    }

}
