package com.alebarre.todosimples.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alebarre.todosimples.models.Tasks;
import com.alebarre.todosimples.services.TasksService;
import com.alebarre.todosimples.services.UserService;

@RestController
@RequestMapping("/tasks")
@Validated
public class TasksController {

	@Autowired
	private TasksService tasksService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Tasks> findById(@PathVariable Long id){
		Tasks obj = this.tasksService.findById(id);
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/user-tasks/{userId}")
	public ResponseEntity<List<Tasks>> findAllTasksByUserId(@PathVariable Long userId){
		userService.findById(userId);
		List<Tasks> objs = this.tasksService.findAllTasksByUserId(userId);
		return ResponseEntity.ok().body(objs);
	}
	
	@PostMapping
	public ResponseEntity<Void> create (@Valid @RequestBody Tasks obj){
		this.tasksService.create(obj);
		URI uri = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Validated
	public ResponseEntity<Void> update(@Valid @RequestBody Tasks obj, @PathVariable Long id){
		obj.setId(id);
		this.tasksService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.tasksService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
