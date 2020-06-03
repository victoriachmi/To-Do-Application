package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoControler {

	@Autowired
	TodoService todoService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {

		List<Todo> todos = todoService.listTodo(getUsername(model));

		model.put("Todos", todos);
		return "listoftodos";
	}

	private String getUsername(ModelMap model) {

		model.put("name", "Victoria");

		return (String) model.get("name");
	}

	@RequestMapping(value = "/addtodo", method = RequestMethod.GET)
	public String showAddTodo(ModelMap model) {

		model.put("todo", new Todo(0, getUsername(model), "", new Date(), false));
		return "addtodo";
	}

	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors())
			return "addtodo";

		todoService.addTodo(getUsername(model), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/todos";
	}

	@RequestMapping(value = "/deletetodo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam String todoId) {
		todoService.deleteTodo(Integer.parseInt(todoId));
		return "redirect:/todos";

	}

	@RequestMapping(value = "/updatetodo", method = RequestMethod.GET)
	public String showUpdatetodo(ModelMap model, @RequestParam String todoId) {

		model.put("todo", todoService.getTodo(Integer.parseInt(todoId)));

		return "addtodo";

	}

	@RequestMapping(value = "/updatetodo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		System.out.println();
		if (result.hasErrors())
			return "addtodo";

		todoService.updateTodo(todo);
		return "redirect:/todos";
	}

}
