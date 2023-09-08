package com.alebarre.todosimples.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alebarre.todosimples.models.Tasks;
import com.alebarre.todosimples.models.User;
import com.alebarre.todosimples.repositories.TasksRepository;

@Service
public class TasksService {

	@Autowired
	private TasksRepository tasksRepository;
	
	@Autowired
	private UserService userService;
	
	public Tasks findById(Long id) {
		Optional<Tasks> tasks = this.tasksRepository.findById(id);
		return tasks.orElseThrow(
				() -> new RuntimeException("Tarefa não encontrada! Id: " + " Tipo: " + Tasks.class.getName()
			)
		);
	}
	
	public List<Tasks> findAllTasksByUserId(Long userId){
		List<Tasks> userTasks = this.tasksRepository.findByUser_Id(userId);
		return userTasks;
	}
	
	@Transactional
	public Tasks create(Tasks obj) {
		User user = this.userService.findById(obj.getUser().getId());
		obj.setId(null);
		obj.setUser(user);
		obj = this.tasksRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Tasks update (Tasks obj) {
		Tasks newObj = findById(obj.getId());
		newObj.setDescription(obj.getDescription());
		return this.tasksRepository.save(newObj);
	}
	
	public void delete (Long id) {
		findById(id);
		try {
			this.tasksRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possível excluir. Entidade está relacionada a outra no BD.");
		}
	}
}
