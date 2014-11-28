package com.smhdemo.common.query;
/**
 * 
 *  
 * @author zhoudongchu
 */
public class Order {
    private static final String ASC = "asc";
    private String sort;
    private String order;

    public Order() {
		super();
	}

	public Order(String sort, String order) {
		this.sort = sort;
		this.order = order;
	}

	public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    
    public boolean hasOrder(){
    	if(order==null||order.length()==0)return false;
        return true;
    }

    public boolean isAsc() {
        return order.equals(ASC);
    }
}	

