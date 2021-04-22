package com.mimacom.taskmanager;

import com.mimacom.taskmanager.persistence.model.Task;
import com.mimacom.taskmanager.persistence.repository.ITaskDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@SpringBootApplication
public class TaskManagerApplication {
	private static final Logger log = LoggerFactory.getLogger(TaskManagerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(ITaskDao repository) {
		return (args) -> {
			// save a few tasks
			repository.save(new Task("test", "description"));

			// fetch all customers
			log.info("Tasks found with findAll():");
			log.info("-------------------------------");
			for (Task task : repository.findAll()) {
				log.info(task.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<Task> task = repository.findById(1);
			log.info("Task found with findById(1):");
			log.info("--------------------------------");
			log.info(task.toString());
			log.info("");

		};
	}
	 */

}
