package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomDAOImpl implements CustomerDAO {

    //inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

        //get the session
        Session currentSession = sessionFactory.getCurrentSession();

        //create query
        Query theQuery = currentSession.createQuery("from Customer", Customer.class);

        //execute query
        List<Customer> customers = theQuery.getResultList();

        //return the results
        return customers;
    }
}
