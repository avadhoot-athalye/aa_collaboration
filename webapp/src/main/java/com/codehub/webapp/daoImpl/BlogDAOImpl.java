package com.codehub.webapp.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehub.webapp.dao.BlogDAO;
import com.codehub.webapp.entity.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Blog> list() {
		String hql = "FROM Blog";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@Transactional
	public Blog getBlog(int id) {
		return sessionFactory.getCurrentSession().get(Blog.class, id);
	}

	@Override
	@Transactional
	public boolean addBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean deleteBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
