package com.raissi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
	private static final Marker CACHE_LOG = MarkerFactory.getMarker("AUDIT_SYS");
	
	private List<String> units = new ArrayList<>(Arrays.asList("UNIT01", "UNIT02", "UNIT03"));
	private List<String> persons = new ArrayList<>(Arrays.asList("PERSON01", "PERSON02", "PERSON03"));
	
	
	@Override
	@Cacheable(value = "spring-cache")
	public List<String> getPersonNames(String department){
		LOGGER.info(CACHE_LOG, "Getting Person names of department {}", department);
		return new ArrayList<>(persons);
	}

	@Override
	@Cacheable(value = "spring-cache")
	public List<String> getDepartmentUnitNames(String department) {
		LOGGER.info(CACHE_LOG, "Getting unit names of department {}", department);
		return units;
	}
	
	@Override
	@CacheEvict(value = { "spring-cache" }, key="#root.targetClass.getName() + 'getPersonNames' + #department.toString()")
	public void savePersonInDepartment(String person, String department){
		persons.add(person);
	}
	
	@Override
	public void savePersonInDepartmentNoEvict(String person, String department){
		persons.add(person);
	}
}
