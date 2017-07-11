package com.my.payment.base.dto;

import com.my.payment.base.dto.impl.BaseDtoImpl;




public class PageDto<T extends BaseDto> extends BaseListDto {

	private static final long serialVersionUID = -2762689922407395921L;

	private T dto;

	public void setDto(T dto) {
		this.dto = dto;
	}

	public T getDto() {
		return dto;
	}
}
