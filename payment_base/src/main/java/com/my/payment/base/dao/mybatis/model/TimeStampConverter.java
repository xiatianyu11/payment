package com.my.payment.base.dao.mybatis.model;

import java.sql.SQLException;
import java.sql.Timestamp;

import oracle.sql.TIMESTAMP;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

public class TimeStampConverter implements Converter {

    private final Logger log = Logger.getLogger(this.getClass());

    public TimeStampConverter() {

        this.defaultValue = null;
        this.useDefault = true;
    }

    public TimeStampConverter(Object defaultValue) {

        this.defaultValue = defaultValue;
        this.useDefault = true;
    }

    private Object  defaultValue = null;

    private boolean useDefault   = true;

    public Object convert(Class type, Object value) {

        if (value == null || "".equals(value)) {
            if (useDefault) {
                return (defaultValue);
            } else {
                throw new ConversionException("No value specified");
            }
        }

        if (value instanceof TIMESTAMP) {

            try {
                return (((oracle.sql.TIMESTAMP) value).timestampValue());
            } catch (SQLException e) {
                log.error("::", e);
            }
        }

        if (value instanceof Timestamp) {

            return value;
        }

        return null;
    }
}
