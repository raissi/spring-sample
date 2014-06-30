package com.raissi.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.raissi.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest extends AbstractContextTests {

	private static final Logger logger = LoggerFactory.getLogger(PersonServiceTest.class);
	private static final Marker CACHE_LOG = MarkerFactory.getMarker("AUDIT_SYS");
	
	@InjectMocks
	@Autowired
	private PersonService personService;
	
	@Test
	public void test(){
		final String dept1 = "DEPT1";
		for(int i=0; i <3; i++){
			logger.info("Calling PersonService for unit names of dept {}, got {}", dept1, personService.getDepartmentUnitNames(dept1));
			logger.info("Calling PersonService for persons names of dept {}, got {}", dept1, personService.getPersonNames(dept1));
		}
		
		logger.debug(CACHE_LOG, "Adding new person {} to dept: {} with evict", "PERS04", dept1);
		personService.savePersonInDepartment("PERS04", dept1);
		logger.debug(CACHE_LOG, "Calling PersonService for persons names of dept {}, got {}", dept1, personService.getPersonNames(dept1));
		
		
		
		logger.info("Adding new person {} to dept: {} with no evict", "PERS05", dept1);
		personService.savePersonInDepartmentNoEvict("PERS05", dept1);
		logger.info("Calling PersonService for persons names of dept {}, got {}", dept1, personService.getPersonNames(dept1));
		
		
		logger.debug(CACHE_LOG, "Adding new person {} to dept: {} with evict", "PERS06", dept1);
		personService.savePersonInDepartment("PERS06", dept1);
		logger.debug(CACHE_LOG, "Calling PersonService for persons names of dept {}, got {}", dept1, personService.getPersonNames(dept1));
	}
}
