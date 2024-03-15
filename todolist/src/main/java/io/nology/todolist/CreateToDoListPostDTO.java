package io.nology.todolist;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;

public class CreateToDoListPostDTO {

	@NotBlank
	private String title;

	@AssertFalse
	private Boolean isComplete;

	public String getTitle() {
		return title;
	}

	public Boolean getIsComplete() {
		return isComplete;
	}

	@Override
	public String toString() {
		return "CreateToDoListPostDTO [title=" + title + ", isComplete=" + isComplete + "]";
	}

//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public void setIsComplete(Boolean isComplete) {
//		this.isComplete = isComplete;
//	}

}
