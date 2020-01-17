package com.example.demonc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidationException;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demonc.model.FormFieldsDTO;
import com.example.demonc.model.FormSubmissionDTO;
import com.example.demonc.model.TaskDTO;
import com.example.demonc.model.User;
import com.example.demonc.services.UserService;

@RestController
@RequestMapping("/magazine")
@CrossOrigin(origins = "http://localhost:4200")

public class MagazineController {

	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	UserService userService;

	
	@GetMapping(path = "/startMagProcess", produces = "application/json")
    public @ResponseBody FormFieldsDTO getData(@Context HttpServletRequest request) {
		//startujemo proces sa id MagazineProcess
		System.out.println("Startovanje magazin procesa");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("MagazineProcess");
        User us=(User) request.getAttribute("logged");
        if(us!=null){
        	us.setType("maineditor");
        	userService.save(us);
        }
		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		task.setAssignee("editor");
		taskService.saveTask(task);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDTO(task.getId(), pi.getId(), properties);
    }	
	@PostMapping(path = "/create/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity create(@RequestBody List<FormSubmissionDTO> formData, @PathVariable String taskId) {
		System.out.println("Kreiranje magazina kontroler");
		HashMap<String, Object> map = this.mapListToDto(formData);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		
		for(FormSubmissionDTO item: formData){
			String fieldId = item.getFieldId();
			System.out.println("Id polja je "+fieldId);
			System.out.println("Vrednost polja je "+item.getFieldValue());
			if(!fieldId.equals("areas") && !fieldId.equals("paying")){
				if(item.getFieldValue()==null || item.getFieldValue().equals("") || item.getFieldValue().isEmpty()==true){
					System.out.println("Prazno je");	
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			if(fieldId.equals("areas")){
				if(item.getCategories().size()==0){
					System.out.println("Nema naucnih oblasti");	

					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

				}
			}
		
			
		}
		
		try{
			runtimeService.setVariable(processInstanceId, "magazineData", formData);
			formService.submitTaskForm(taskId, map);
	     	
			List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
			System.out.println("Taskovi");
			for(Task t : tasks){
				System.out.println(t.getName());
			}
			    
		}catch(FormFieldValidationException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("Zavrsio");
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/update/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity update(@RequestBody List<FormSubmissionDTO> formData, @PathVariable String taskId) {
		System.out.println("Update magazina kontroler");
		HashMap<String, Object> map = this.mapListToDto(formData);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		
		for(FormSubmissionDTO item: formData){
			String fieldId = item.getFieldId();
			System.out.println("Id polja je "+fieldId);
			System.out.println("Vrednost polja je "+item.getFieldValue());
			
			if(fieldId.equals("editorsl")){
				if(item.getCategories().size()>2){
					System.out.println("Vise od 2 naucne oblasti");	
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

				}
			}
			if(fieldId.equals("reviewersl")){
				if(item.getCategories().size()!=2){
					System.out.println("Nisu 2 recenzenta");	
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		
			
		}
		
		try{
			runtimeService.setVariable(processInstanceId, "updateData", formData);
			formService.submitTaskForm(taskId, map);
	     	
			List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
			System.out.println("Taskovi");
			for(Task t : tasks){
				System.out.println(t.getName());
			}
			    
		}catch(FormFieldValidationException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("Zavrsio update magazina");
		return new ResponseEntity<>(HttpStatus.OK);
    }

	
	
	private HashMap<String, Object> mapListToDto(List<FormSubmissionDTO> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDTO temp : list){
			map.put(temp.getFieldId(), temp.getFieldValue());
		}
		
		return map;
	}
	@GetMapping(path = "/nextTaskMag/{processId}", produces = "application/json")
    public @ResponseBody FormFieldsDTO getNextTask(@PathVariable String processId) {

		System.out.println("Usao u get nexxt task "+ processId);
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processId).list();
		List<TaskDTO> taskDTOList = new ArrayList<TaskDTO>();
		if(tasks.size()==0){
			System.out.println("Prazna lista");
		}
		for(Task T: tasks){
			System.out.println("Dodaje task "+T.getName());
			taskDTOList.add(new TaskDTO(T.getId(), T.getName(), T.getAssignee()));
		}
		
		Task nextTask = tasks.get(0);
		if(nextTask.getAssignee()==null || !nextTask.getAssignee().equals("demo")){
			nextTask.setAssignee("editor");
			taskService.saveTask(nextTask);
		}
		System.out.println("Assigne je "+nextTask.getAssignee());
		TaskFormData tfd = formService.getTaskFormData(nextTask.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}
		
        return new FormFieldsDTO(nextTask.getId(), processId, properties);
    }
	@PostMapping(path = "/approveMagazine/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity approveReviewer(@RequestBody List<FormSubmissionDTO> formData, @PathVariable String taskId) {
		System.out.println("Usao u approveMagazine");
		HashMap<String, Object> map = this.mapListToDto(formData);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		boolean approved = false;
		for(FormSubmissionDTO item: formData){
			String fieldId = item.getFieldId();
			System.out.println("Id polja je "+fieldId);
			System.out.println("Vrednost polja je "+item.getFieldValue());
			if(fieldId.equals("correct")){
				if(item.getFieldValue().equals("true")){
					approved = true;
				}
			}
		}
		
		try{
			runtimeService.setVariable(processInstanceId, "approvedMag", approved);
			
			formService.submitTaskForm(taskId, map);
	     	
			List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
			
			    
		}catch(FormFieldValidationException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
    }



}
