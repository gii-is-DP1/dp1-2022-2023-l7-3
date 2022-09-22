package org.springframework.samples.petclinic.repository;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericJPARepository< T extends Serializable > extends AbstractJPARepository< T > {

}
