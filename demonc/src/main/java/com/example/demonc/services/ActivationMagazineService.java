package com.example.demonc.services;

import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demonc.model.FormSubmissionDTO;
import com.example.demonc.model.Magazine;

@Service
public class ActivationMagazineService implements JavaDelegate{
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	ScientificAreaService scienceService;
	@Autowired
	MagazineService magazineService;
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;


	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		boolean approvedMag = (boolean)execution.getVariable("approvedMag");
		
		 List<FormSubmissionDTO> magInfo = (List<FormSubmissionDTO>)execution.getVariable("magazineData");
		 String issn="";
		 for(FormSubmissionDTO item: magInfo){
			 if(item.getFieldId().equals("issn")){
				 issn = item.getFieldValue();
				 break;
			 }
		 }
		 
		 Magazine magazine = new Magazine();
		  magazine = magazineService.findMagazineByIssn(issn);
		  if(magazine!=null){
			  magazine.setActive(approvedMag);
		  }
		  magazineService.save(magazine);
		  if(approvedMag){
			  System.out.println("Odobren");
		  }
	}

}
