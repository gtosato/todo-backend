package io.nology.todolist;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nology.todolist.todolistposts.ToDoListPost;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class ToDoListPostService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ToDoListPostRepository repo;

	public ToDoListPost createPost(CreateToDoListPostDTO data) {

		ToDoListPost newPost = mapper.map(data, ToDoListPost.class);
		newPost.setCreatedAt(new Date());
		return this.repo.save(newPost);
	}

	public List<ToDoListPost> getAll() {
		return this.repo.findAll();
	}

	public Optional<ToDoListPost> findById(Integer id) {
		return this.repo.findById(id);
	}

	public Optional<ToDoListPost> updateById(@Valid UpdateToDoListPostDTO data, Integer id) {
		Optional<ToDoListPost> maybePost = this.findById(id);

		if (maybePost.isEmpty()) {
			return maybePost;
		}

		ToDoListPost foundPost = maybePost.get();

		mapper.map(data, foundPost);

		ToDoListPost updated = this.repo.save(foundPost);

		return Optional.of(updated);

	}

	public Boolean deletePostById(Integer id) {
		Optional<ToDoListPost> maybePost = this.repo.findById(id);

		if (maybePost.isEmpty()) {
			return false;
		}
		this.repo.delete(maybePost.get());
		return true;
	}

}
