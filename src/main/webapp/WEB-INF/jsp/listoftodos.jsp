
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<HTML>
<head>
<title>Todo App </title>

	<link rel="stylesheet" type="text/css" href="/css/listoftodos.css">
	<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
		
</head>
<body>	
<div class="container">
<nav role="navigation" class="navbar navbar-default">
    <div>
        <a href="#" class="navbar-brand">Logo</a>
    </div>
    <div class="navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/welcome">Home</a></li>
            <li><a href="/todos">Todos</a></li>
            <li><a href="/Users">Users</a></li>
        </ul>
        
    </div>
</nav>

<table class = "table table-striped table-dark">
	<thead>
		 <tr style = "color:blue">
			<td> Name</td>
			<td> Todo Stuff</td>
			<td> Target Date </td>
			<td> Status</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${Todos}" var="todo">
			<tr >
				<td>${todo.user}</td>
				<td>${todo.desc}</td>
				<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
				<td>${todo.isDone}</td> 
				<td><a class= "btn btn-warning" href="/deletetodo?todoId=${todo.id}">Delete </a></td>
				<td><a class= "btn btn-success" href="/updatetodo?todoId=${todo.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a class = "btn btn-success" href = "/addtodo">Add Todo</a>
</div>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
 <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</HTML>