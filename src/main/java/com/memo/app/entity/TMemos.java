package com.memo.app.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TMemos {
	@JsonFormat
	private int id;
	@JsonFormat
	private String title;
	@JsonFormat
	private String detail;
	@JsonFormat
	private int userId;
	@JsonFormat
	private boolean isDeleted;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Tokyo")
	private LocalDateTime createdAt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Tokyo")
	private LocalDateTime updatedAt;
}
