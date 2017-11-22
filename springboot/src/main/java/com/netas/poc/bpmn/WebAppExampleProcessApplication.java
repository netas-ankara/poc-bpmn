package com.netas.poc.bpmn;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.util.Assert;

@SpringBootApplication
@EnableProcessApplication
public class WebAppExampleProcessApplication {

	private final RuntimeService runtimeService;

	@Autowired
	public WebAppExampleProcessApplication(RuntimeService runtimeService) {
		Assert.notNull(runtimeService,"runtimeService must not be null.");
		this.runtimeService = runtimeService;
	}

	public static void main(String... args) {
		SpringApplication.run(WebAppExampleProcessApplication.class, args);
	}

	@EventListener
	private void processPostDeploy(PostDeployEvent event) {
		runtimeService.startProcessInstanceByKey("loanApproval");
	}
}
