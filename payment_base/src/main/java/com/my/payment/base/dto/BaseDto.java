package com.my.payment.base.dto;

import java.io.Serializable;

public interface BaseDto extends Serializable {

    public abstract String toString();

    public abstract boolean equals(Object o);

    public abstract int hashCode();

}
