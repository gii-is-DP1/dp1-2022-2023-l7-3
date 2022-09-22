package org.springframework.samples.petclinic.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractJPARepository< T extends Serializable > {
	 
	   private Class< T > clazz;
	 
	   @PersistenceContext
	   EntityManager entityManager;
	 
	   public void setClazz( Class< T > clazzToSet ) {
	      this.clazz = clazzToSet;
	   }
	 
	   public T findOne( Long id ){
	      return entityManager.find( clazz, id );
	   }
	   public List< T > findAll(){
	      return entityManager.createQuery( "from " + clazz.getName() )
	       .getResultList();
	   }
	 
	   public void save( T entity ){
	      entityManager.persist( entity );
	   }
	 
	   public void update( T entity ){
	      entityManager.merge( entity );
	   }
	 
	   public boolean delete( T entity ){
		   if(entity == null)
			   return false;
		   else {
			   try {
				   entityManager.remove( entity );
				   return true;
			   }catch(IllegalArgumentException ex) {
				 ex.printStackTrace();
				 return false;
			   }
		   }
	   }
	   public boolean deleteById( Long entityId ){
	      T entity = findOne( entityId );	      
	      return delete( entity );
	   }
	}
