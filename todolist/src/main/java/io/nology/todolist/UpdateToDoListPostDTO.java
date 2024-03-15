package io.nology.todolist;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public class UpdateToDoListPostDTO {

	@Pattern(regexp = "^(?=\\S).*$", message = "Title cannot be empty")
	private String title;

	@Valid
	private Boolean isComplete;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(Boolean isComplete) {
		this.isComplete = isComplete;
	}
}
