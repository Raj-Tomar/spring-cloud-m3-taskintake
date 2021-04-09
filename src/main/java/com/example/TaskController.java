package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	@Autowired
	private TaskProcessor task;
	
	@PostMapping("/tasks")
	public String launchTask(@RequestBody String s) {
		task.publishRequest(s);
		System.out.println("Request Made");
		return "success";
	}
}
