package com.my.payment.base.dto;

import java.util.ArrayList;
import java.util.List;

public class Pagination extends BaseListDto{
    
    private static final long serialVersionUID = -2347438840805341687L;
    
    @SuppressWarnings("unchecked")
    private List              resultList       = new ArrayList();

    @SuppressWarnings("unchecked")
    public List getResultList() {
        return resultList;
    }

    @SuppressWarnings("unchecked")
    public void setResultList(List resultList) {
        this.resultList = resultList;
    }

}
