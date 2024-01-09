package com.memo.app.form;

import java.time.LocalDateTime;

import com.memo.app.entity.TMemos;

import lombok.Data;

@Data
public class TMemosForm {
	private Integer id;
	private String title;
	private String detail;
	private Integer userId;
	private boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public TMemos copy() {
		TMemos memo = new TMemos();
		memo.setId(this.getId());
		memo.setTitle(this.getTitle());
		memo.setDetail(this.getDetail());
		memo.setUserId(this.getUserId());
		memo.setDeleted(this.isDeleted());
		memo.setCreatedAt(this.getCreatedAt());
		memo.setUpdatedAt(this.getUpdatedAt());
		return memo;
	}
}
