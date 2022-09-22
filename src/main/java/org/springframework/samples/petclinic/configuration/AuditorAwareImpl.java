package org.springframework.samples.petclinic.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
	 
    @Override
    public Optional<String> getCurrentAuditor() {
    	Optional<String> currentUserName=Optional.empty();
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    	     currentUserName=Optional.of(authentication.getName());
    	    
    	}else if(authentication!=null) {
    		currentUserName=Optional.of(authentication.getPrincipal().toString());
    	}
    	return currentUserName;
    }

}