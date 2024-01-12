package com.jbk.dao.daoImpl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbk.dao.SupplierDao;
import com.jbk.entity.SupplierEntity;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.SupplierModel;

@Component
public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	private SessionFactory session;

	@Override
	public int addSupplier(SupplierEntity supplierEntity) {
		int status = 0;
		try {
			Session openSession = session.openSession();
			openSession.save(supplierEntity);
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
	public SupplierEntity getSupplierById(long supplierId) {
		Session openSession = session.openSession();
		SupplierEntity supplierEntity = openSession.get(SupplierEntity.class, supplierId);
		return supplierEntity;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SupplierEntity> getAllSuppliers() {
		List list = null;
		Session openSession = session.openSession();
		try {
			Criteria createCriteria = openSession.createCriteria(SupplierEntity.class);
			Criteria addOrder = createCriteria.addOrder(Order.desc("suppliername"));
		    list= addOrder.list();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		return list;
	}

	// not completed
	@Override
	public SupplierEntity updateSupplier(SupplierEntity supplierentity) {
		Session openSession = session.openSession();
		try {
			openSession.update(supplierentity);
			openSession.beginTransaction().commit();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		return supplierentity;
	}

	@Override
	public int deleteSupplier(long supplierId) {
		int status = 3; // Default status for "something went wrong"

		try {
			Session openSession = session.openSession();
			SupplierEntity supplierEntity = openSession.get(SupplierEntity.class, supplierId);

			if (supplierEntity != null) {
				openSession.beginTransaction();
				openSession.delete(supplierEntity);
				openSession.getTransaction().commit();
				status = 1; // Successfully deleted
			} else {
				status = 2; // Record does not exist
			}

		} catch (Exception e) {
			e.printStackTrace(); 
		}

		return status;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SupplierModel> getAllSuppliersCriteria() {
		List list = null;
		Session openSession = session.openSession();
		try {
			Criteria createCriteria = openSession.createCriteria(SupplierEntity.class);
			createCriteria.setFirstResult(1);//starting from 0th index row one is not including
			createCriteria.setMaxResults(3);
			list = createCriteria.list();
		} catch (Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<SupplierModel> getAllSuppliersRistriction() {
		List list = null;
		Session openSession = session.openSession();
		try {
		Criteria createCriteria = openSession.createCriteria(SupplierEntity.class);
		createCriteria.add(Restrictions.eq("suppliername", "harsh Distributors"));
		list = createCriteria.list();
		}catch(Exception e) {
			throw new SomethingWentWrongException("Something went wrong");
		}
		return list;
	}
}
