package com.mobile.meishang.model;

/**
 * 
 * @Title:
 * @Description:
 * @Author:Administrator
 * @Since:2013-5-2
 * @Version:
 */
public class ObjectReference<T>
{

    private T referent;

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public T getReferent()
    {
        return referent;
    }

    /**
     * 
     * @Description:
     * @Author Administrator
     * @Date 2013-5-2
     */
    public void setReferent(T referent)
    {
        this.referent = referent;
    }

    /**
     * 
     * @param r
     */
    public ObjectReference(T r)
    {
        super();
        referent = r;
    }

    /**
     * 
     */
    public ObjectReference()
    {
        super();
    }
}