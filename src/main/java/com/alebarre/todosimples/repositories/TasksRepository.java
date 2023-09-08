package com.alebarre.todosimples.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alebarre.todosimples.models.Tasks;

@Repository 
public interface TasksRepository extends JpaRepository<Tasks, Long>{
	
	List<Tasks> findByUser_Id(Long id);
	
	//Usando o JPQL
	//@Query (value = "select t FROM Task t WHERE t.user.id = :id")
	//List<Tasks> findByUser_Id(@Param("id") Long id);
	
	//Usando o SQL nativo
	//@Query (value = "SELECT * FROM tasks t WHERE t.user_id = :id", nativeQUery = true)
	//List<Tasks> findByUser_Id(@Param("id") Long id);
}
