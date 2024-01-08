package com.jbk.dao.daoImpl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.SomethingWentWrongException;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory session;

	
	@Override
	public int addProduct(ProductEntity productentity) {
		int status = 0;
		try {
			Session openSession = session.openSession();
			openSession.save(productentity);
			Transaction beginTransaction = openSession.beginTransaction();
			beginTransaction.commit();
			status = 1;
		} catch (PersistenceException e) {
			status = 2;
		}

		catch (Exception e) {
			status = 3;
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public ProductEntity getProductById(long productId) {
		Session openSession = session.openSession();
		ProductEntity productEntity = openSession.get(ProductEntity.class, productId);
		return productEntity;
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		List list = null;
		Session openSession = session.openSession();
		try {
			Criteria createCriteria = openSession.createCriteria(ProductEntity.class);
			Criteria addOrder = createCriteria.addOrder(Order.desc("productname"));
		    list= addOrder.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}
		return list;
	}

	@Override
	public ProductEntity updateProduct(ProductEntity productentity) {
		Session openSession = session.openSession();
		try {
			openSession.update(productentity);
			openSession.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}
		return productentity;
	}

	@Override
	public int deleteProduct(long productId) {
		int status = 0;

		try {
			Session openSession = session.openSession();
			ProductEntity productEntity = openSession.get(ProductEntity.class, productId);

			if (productEntity != null) {
				openSession.beginTransaction();
				openSession.delete(productEntity);
				openSession.getTransaction().commit();
				status = 1; // Successfully deleted
			} 
		} catch (Exception e) {
			e.printStackTrace(); 
			throw new SomethingWentWrongException("Something went wrong");
		}

		return status;
	}
	}


