package com.mitake.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

public class PageSeperator extends BaseHibernateDAO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int totalpages;//總頁數
	private int totalcount;//總筆數
	private int currentrow = 0;//起始資料列
	private int currentpage = 1;//第幾頁
	private int pagerows = 10;//一頁幾筆
	private String sort;//排序鍵
	private String order;//排序方式

	private HashMap params = new HashMap();//查詢條件
	private List resultlist;//查詢結果

	public void list(Criteria criteria){
		//計算筆數
		criteria.setProjection(Projections.rowCount());
		Object object = criteria.uniqueResult();
		if(object==null){
			totalcount = 0;
			resultlist = new ArrayList();
			return;
		}
		totalcount = ((Long)object).intValue();
		//計算共幾頁
		totalpages = (totalcount/pagerows)+((totalcount%pagerows)!=0?1:0);
		//從哪一筆資料開始
		currentrow = currentrow+((currentpage-1)*pagerows);
		//設定排序
		if(sort!=null){
			if(order==null || order.equalsIgnoreCase("asc")){
				criteria.addOrder(Order.asc(sort));
			}else if("desc".equalsIgnoreCase(order)){
				criteria.addOrder(Order.desc(sort));
			}else{
				criteria.addOrder(Order.asc(sort));
			}
		}
		//取得資料
		criteria.setProjection(null);
		criteria.setFirstResult(currentrow) ; // 第一筆是 0 
		criteria.setMaxResults(pagerows); // pageSize : 一頁 show 的筆數限制
		resultlist = criteria.list();
	}

	public <T> void list(String hql,HashMap<String,Object> params){
		//計算筆數
		Session session = getCurrentSession();
		Query query_c = session.createQuery("select count (*) "+hql);
		if(null!= params && params.size()>0){   
			for(String key:params.keySet()){
				if(params.get(key) instanceof Collection){ 
					Collection<T> parameterList = (Collection<T>)params.get(key);
					query_c.setParameterList(key, parameterList);
				}else{
					query_c.setParameter(key, params.get(key));
				}
			}
		}
		Object object = query_c.uniqueResult();
		if(object==null){
			totalcount = 0;
			resultlist = new ArrayList();
			return;
		}
		totalcount = ((Long)object).intValue();
		//計算共幾頁
		totalpages = (totalcount/pagerows)+((totalcount%pagerows)!=0?1:0);
		//從哪一筆資料開始
		currentrow = currentrow+((currentpage-1)*pagerows);
		//設定排序
		if(sort!=null){
			if(order==null || order.equalsIgnoreCase("asc")){
				hql = hql+" order by o."+sort+" asc";
			}else if("desc".equalsIgnoreCase(order)){
				hql = hql+" order by o."+sort+" desc";
			}else{
				hql = hql+" order by o."+sort+" asc";
			}
		}
		Query query = session.createQuery(hql);
		if(null!= params && params.size()>0){   
			for(String key:params.keySet()){
				if(params.get(key) instanceof Collection){ 
					Collection<T> parameterList = (Collection<T>)params.get(key);
					query.setParameterList(key, parameterList);
				}else{
					query.setParameter(key, params.get(key));
				}
			}
		}
		//取得資料
		//criteria.setProjection(null);
		query.setFirstResult(currentrow) ; // 第一筆是 0 
		query.setMaxResults(pagerows); // pageSize : 一頁 show 的筆數限制
		resultlist = query.list();
	}

	public int getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(int totalpages) {
		this.totalpages = totalpages;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getCurrentrow() {
		return currentrow;
	}

	public void setCurrentrow(int currentrow) {
		this.currentrow = currentrow;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(Integer currentpage) {
		if(currentpage!=null)
			this.currentpage = currentpage;
	}

	public int getPagerows() {
		return pagerows;
	}

	public void setPagerows(Integer pagerows) {
		if(pagerows!=null)
			this.pagerows = pagerows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if(sort!=null)
			this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		if(order!=null)
			this.order = order;
	}

	public HashMap getParams() {
		return params;
	}

	public void setParams(HashMap params) {
		this.params = params;
	}

	public List getResultlist() {
		return resultlist;
	}

	public void setResultlist(List resultlist) {
		this.resultlist = resultlist;
	}
}
