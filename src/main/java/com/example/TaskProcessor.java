package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class TaskProcessor {

	@Autowired
	private Source source;
	
	public void publishRequest(String payload) {
		String url = "maven://com.example:spring-cloud-m3-task:jar:0.0.1-SNAPSHOT";
		List<String> input = new ArrayList<>(Arrays.asList(payload.split(",")));
		
		TaskLaunchRequest request = new TaskLaunchRequest(url, input, null, null, null);
		
		System.out.println("Created Task Request");
		
		GenericMessage<TaskLaunchRequest> message = new GenericMessage<>(request);
		
		this.source.output().send(message);
	}
}
