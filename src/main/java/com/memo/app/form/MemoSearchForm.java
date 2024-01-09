package com.memo.app.form;

import java.util.Collection;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.CodePointLength;

import com.memo.app.common.enums.SearchMatch;
import com.memo.app.validation.Custom;

import lombok.Data;

@Data
public class MemoSearchForm {
	@Min(1)
	private Integer id;
	@CodePointLength(max = 10)
	private String title;
	@CodePointLength(max = 100)
//	@Custom(custom = "detail")
	private String detail;
	@Min(1)
	private Integer userId;
	private boolean isDeleted;
	private SearchMatch matchForTitle;
	private Collection<SearchMatch> matchForTitles;
	private SearchMatch matchForDetail;
	private Collection<SearchMatch> matchForDetails;
	
}
