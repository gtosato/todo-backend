package io.nology.todolist;

import org.springframework.data.jpa.repository.JpaRepository;

import io.nology.todolist.todolistposts.ToDoListPost;

public interface ToDoListPostRepository extends JpaRepository<ToDoListPost, Integer> {

}
