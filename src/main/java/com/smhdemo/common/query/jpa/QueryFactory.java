package com.smhdemo.common.query.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.smhdemo.common.query.Result;
import com.smhdemo.common.query.jpa.QueryParameter.QueryOperateType;

/**
 * jpa数据查询实现
 * 
 * @author zhoudongchu
 */
@Service
public class QueryFactory {
	private static final Logger logger = LoggerFactory.getLogger(QueryFactory.class);
	private String errorCode;
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Result queryByConditions(QueryCondition qc) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select model \n");
		sqlBuffer.append("from " + qc.getEntityName() + " as model \n");
		boolean first = true;
		List<QueryParameter> parameters = qc.getParameters();
		for (int i = 0; i < parameters.size(); i++) {
			if (parameters.get(i).getName() != null) {
				if (first) {
					sqlBuffer.append("where ");
					first = false;
				} else {
					sqlBuffer.append("and ");
				}
				if (parameters.get(i).getType() == QueryOperateType.Equal) {
					sqlBuffer.append("model."
							+ parameters.get(i).getName()
							+ " = :"
							+ parameters.get(i).getName() + " \n");
				} else if (parameters.get(i).getType() == QueryOperateType.CharIn) {
					sqlBuffer.append("InStr(model."
							+ parameters.get(i).getName()
							+ " , :"
							+ parameters.get(i).getName() + " ) > 0 \n");
				}else if (parameters.get(i).getType() == QueryOperateType.In) {
					sqlBuffer.append("model."
							+ parameters.get(i).getName()
							+ " in( :"
							+ parameters.get(i).getName() + " ) > 0 \n");
				}
			}
		}
		
		List<Object> list = new ArrayList<Object>();
		int count=0;
		try {
			logger.info(sqlBuffer.toString());

			Query queryObject = entityManager.createQuery(sqlBuffer.toString());
			for (int i = 0; i < parameters.size(); i++) {
				queryObject.setParameter(parameters.get(i).getName(), 
						parameters.get(i).getValue());
			}
			queryObject.setFirstResult((qc.getPage()-1)*qc.getRows());
			queryObject.setMaxResults(qc.getRows());
			list = queryObject.getResultList();
			count = 100;
			entityManager.close();

		} catch (RuntimeException re) {
			errorCode += "CM000006";
			throw re;
		}
		
		return new Result(qc.getRows(),count,list);
	}
}
