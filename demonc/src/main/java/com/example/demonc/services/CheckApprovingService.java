package com.example.demonc.services;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demonc.model.FormSubmissionDTO;
import com.example.demonc.model.User;

@Service
public class CheckApprovingService implements JavaDelegate{

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Usao u checkApproving servis");
		if(execution==null){
			System.out.println("Null exec");
		}
		boolean approvedReviewer = (boolean)execution.getVariable("approvedFlag");
		if(execution.getVariable("approvedFlag")==null){
			System.out.println("Nema variable");
		}
		if(approvedReviewer){
			System.out.println("Odobren");
		}else{
			System.out.println("Nije odobren");
		}
		com.example.demonc.model.User reviewer = new com.example.demonc.model.User();  
		List<FormSubmissionDTO> userInfo = (List<FormSubmissionDTO>)execution.getVariable("registrationData");
	    String username = "";
		for(FormSubmissionDTO item: userInfo){
			  System.out.println("***************");
			  String fieldId = item.getFieldId();
			  String fieldValue = item.getFieldValue();
			  System.out.println("Id je "+fieldId);
			  System.out.println("vALUE JE "+fieldValue);
			     if(fieldId.equals("username")){ 
					 username = fieldValue;
				  }	
			}
		//reviewer = userService.findUserByUsername(username);
		List<User> users = userService.findAllByUsername(username);
		reviewer = users.get(0);
		if(reviewer!=null){
			System.out.println("Nije null reviewer");
			if(approvedReviewer){
				reviewer.setType("reviewer");
				userService.save(reviewer);
			}	
		}
		
		
	}

}
