package com.newtiming.finance.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 通用dao
 * 直接调用mybatis中
 * @author yujunjie
 *
 */
//@Repository(value="baseDao")
public class BaseDao extends SqlSessionDaoSupport {
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
    public boolean delete(String deleteId,Integer id) {  
        try {
        	 this.getSqlSession().delete(deleteId, id);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }
 
    /**
     * 根据queryName查询列表
     * @param selectId
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List getAll(String selectId) {  
        List list = null;  
        try {
            list = this.getSqlSession().selectList(selectId);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return list;  
    }  
 
    /**
     * 插入一条数据
     * @param insertId
     * @param object
     * @return
     */
    public boolean insert(String insertId, Object object) {  
        try {  
        	this.getSqlSession().insert(insertId, object);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }  
 
    /**
     * 更新数据
     * @param updateId
     * @param obj
     * @return
     */
    public boolean update(String updateId, Object obj) {  
        try {  
        	this.getSqlSession().update(updateId, obj);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    }

    /**
     * 通过Id查询
     * @param selectId
     * @param object
     * @return
     */
	public Object getById(String selectId, Object object) {
		return this.getSqlSession().selectOne(selectId, object);
	}

	/**
	 * 通过id查询
	 * @param selectId
	 * @param id
	 * @return
	 */
	public Object getById(String selectId, Integer id) {
		return this.getSqlSession().selectOne(selectId, id);
	}

	
	/**
	 * 
	 * @param selectId
	 * @return
	 */
	public int countAll(String selectId) {
		return this.getSqlSession().selectOne(selectId);
	}
	
	/**
	 * 修改外键约束
	 * @param status
	 */
	public void updateForeignKeyChecks(Integer status){
		try {  
        	this.getSqlSession().update("updateForeignKeyChecks", status);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	
	/**
	 * 获取分页数据列表
	 * @param selectId
	 * @param params
	 * @return
	 */
	public <T> PageInfo<T> paging(String selectId, Map<String, Object> params){
		return this.paging(selectId, true, params);
	}
	
	/**
	 * 获取分页数据列表
	 * @param selectId mapper文件对应的sql ID
	 * @param getCount 是否进行count查询
	 * @param params 查询所需的参数
	 * @return
	 */
	public <T> PageInfo<T> paging(String selectId, boolean getCount, Map<String, Object> params){
    	Integer pageNum = (Integer)params.get("pageNum");
		Integer pageSize = (Integer)params.get("pageSize");
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		
	    PageHelper.startPage(pageNum, pageSize, getCount);
	    List<T> list = this.getSqlSession().selectList(selectId, params);
		PageInfo<T> p = new PageInfo<T>(list);
	    return p;
	}
	
	public <T> PageInfo<T> paging(String selectId, boolean getCount, Integer pageNum, Integer pageSize, Object params){
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		
	    PageHelper.startPage(pageNum, pageSize, getCount);
	    List<T> list = this.getSqlSession().selectList(selectId, params);
		PageInfo<T> p = new PageInfo<T>(list);
	    return p;
	}
	
	//分页查询命名规范
}
