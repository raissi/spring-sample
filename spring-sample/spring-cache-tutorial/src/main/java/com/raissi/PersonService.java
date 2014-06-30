package com.raissi;

import java.util.List;

public interface PersonService {

	List<String> getPersonNames(String department);
	
	List<String> getDepartmentUnitNames(String department);

	void savePersonInDepartment(String person, String department);

	void savePersonInDepartmentNoEvict(String person, String department);
	
	
	

}
