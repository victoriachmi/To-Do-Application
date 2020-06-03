package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Todo;

public interface TodoInterface {

	public List<Todo> listTodos(String userName);
	
	public Todo getTodo(int todoId);
	
	public Todo addTodo(String user, String desc, Date targetDate, Boolean isDone);
	
	public Todo deleteTodo(int todoId);
	
	public Todo updateTodo(Todo todo);

	public List<Todo> listTodo(String userName);
}
