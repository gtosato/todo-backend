package io.nology.todolist.todolistposts;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nology.todolist.CreateToDoListPostDTO;
import io.nology.todolist.ToDoListPostService;
import io.nology.todolist.UpdateToDoListPostDTO;
import io.nology.todolist.exceptions.NotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class ToDoListPostController {

	@Autowired
	private ToDoListPostService toDoListPostService;

	@PostMapping
	public ResponseEntity<ToDoListPost> createPost(@Valid @RequestBody CreateToDoListPostDTO data) {
		ToDoListPost createdPost = this.toDoListPostService.createPost(data);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ToDoListPost>> getAllPosts() {
		List<ToDoListPost> allPosts = this.toDoListPostService.getAll();

		return new ResponseEntity<>(allPosts, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ToDoListPost> getPostById(@PathVariable Integer id) throws NotFoundException {
		Optional<ToDoListPost> maybePost = this.toDoListPostService.findById(id);

		ToDoListPost foundPost = maybePost.orElseThrow(() -> new NotFoundException(ToDoListPost.class, id));

		return new ResponseEntity<>(foundPost, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ToDoListPost> updatePostById(@Valid @RequestBody UpdateToDoListPostDTO data,
			@PathVariable Integer id) throws NotFoundException {
		Optional<ToDoListPost> maybeUpdatedPost = this.toDoListPostService.updateById(data, id);

		ToDoListPost updatedPost = maybeUpdatedPost.orElseThrow(() -> new NotFoundException(ToDoListPost.class, id));

		return new ResponseEntity<>(updatedPost, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ToDoListPost> deletePostById(@PathVariable Integer id) throws NotFoundException {
		Boolean deleted = this.toDoListPostService.deletePostById(id);

		if (!deleted) {
			throw new NotFoundException(ToDoListPost.class, id);
		}

		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
