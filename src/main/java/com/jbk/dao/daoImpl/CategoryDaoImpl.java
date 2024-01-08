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

import com.jbk.dao.CategoryDao;
import com.jbk.entity.CategoryEntity;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.CategoryModel;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public int addCategory(CategoryEntity categoryEntity) {
		int status = 0;
		try {
			Session openSession = session.openSession();
			openSession.save(categoryEntity);
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
	public CategoryEntity getCategoryById(long categoryId) {
		Session openSession = session.openSession();
		CategoryEntity categoryEntity = openSession.get(CategoryEntity.class, categoryId);
		return categoryEntity;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<CategoryEntity> getAllCategorys() {
		List list = null;
		Session openSession = session.openSession();
		try {
			Criteria createCriteria = openSession.createCriteria(CategoryEntity.class);
			Criteria addOrder = createCriteria.addOrder(Order.desc("categoryname"));
		    list= addOrder.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}
		return list;
	}

	@Override
	public CategoryEntity updateCategory(CategoryEntity categoryentity) {
		Session openSession = session.openSession();
		try {
			openSession.update(categoryentity);
			openSession.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong");
		}
		return categoryentity;
	}

	@Override
	public int deleteCategory(long categoryId) {
		int status = 0;

		try {
			Session openSession = session.openSession();
			CategoryEntity categoryEntity = openSession.get(CategoryEntity.class, categoryId);

			if (categoryEntity != null) {
				openSession.beginTransaction();
				openSession.delete(categoryEntity);
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
