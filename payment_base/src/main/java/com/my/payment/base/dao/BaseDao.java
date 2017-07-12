package com.my.payment.base.dao;

import java.util.List;

import com.my.payment.base.dto.BaseDto;
import com.my.payment.base.dto.PageDto;
import com.my.payment.base.dto.Pagination;



public interface BaseDao<T extends BaseDto> {

   /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param object
     * @return
     */
    List<T> getAll(T object) ;

    /**
     * 
     * <pre>
     * get an object based on class and identifier.
     * </pre>
     *
     * @param id
     * @return  a populated object
     */
    T getRow(final T object);
    
    /**
     * 
     * <pre>
     * Checks for existence of an object of type T using the id arg.
     * </pre>
     *
     * @param id
     * @return true if it exists, false if it doesn't
     */
    boolean exists(T object);
    
    /**
     * 
     * <pre>
     * Generic method to save an object
     * </pre>
     *
     * @param object
     * @return the persisted object
     */
    T save(T object);
    
    /**
     * 
     * <pre>
     * Generic method to save an object
     * </pre>
     *
     * @param object
     * @return the persisted object
     */
    void saveBatch(List<T> list);
    
    
   /**
     * 
     * <pre>
     * Generic method to save an object
     * </pre>
     *
     * @param object
     */
    Integer updatePK(T object);
    
   /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param object
     */
    void delete(T object);
    
    
    /**
     * 
     * <pre>
     * 
     * </pre>
     *
     * @param object
     * @param listPK
     */
    public void updateBatchPK(T object,  List<Long> listPK);
    
    
    public Pagination findObjectsWithPg(PageDto pageDto);
    
}
