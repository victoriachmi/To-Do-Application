package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Todo;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TodoService {

	static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 3;

	static {
		todos.add(new Todo(1, "Java", "OOP", new Date(), false));
		todos.add(new Todo(2, "Monday", "Run", new Date(), false));

	}

	public List<Todo> listTodo(String userName) {
		List<Todo> filteredTodos = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(userName)) {
				filteredTodos.add(todo);
			}
		}

		return filteredTodos;

	}

	public Todo getTodo(int id) {
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}

		return null;
	}

	public Todo addTodo(String user, String desc, Date targetDate, Boolean isDone) {
		Todo newTodo = new Todo(++todoCount, user, desc, targetDate, isDone);
		todos.add(newTodo);
		return newTodo;

	}

	public Todo deleteTodo(int todoId) {
		Todo deleteTodo = todos.stream().filter(todo -> todo.getId() == todoId).findFirst().orElse(null);
		todos.remove(deleteTodo);
		return deleteTodo;
	}

	public Todo updateTodo(Todo todo) {
		deleteTodo(todo.getId() - 1);
		todos.add(todo.getId(), todo);
		return todo;
	}

}
