package com.memo.app.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.memo.app.common.enums.SearchMatch;
import com.memo.app.common.property.Properties;
import com.memo.app.entity.TMemos;
import com.memo.app.form.MemoSearchForm;
import com.memo.app.form.TMemosForm;
import com.memo.app.service.TMemosService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/memo")
@Slf4j
public class MemoController {
	
	@Autowired
	Properties prop;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	TMemosService tMemosService;

	/**
	 * 初期画面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		System.out.println("index");
		System.out.println(prop.getTest());
		System.out.println(messageSource.getMessage("test", null, null));
		log.info("output info log");
		// メモ一覧取得
		Collection<TMemos> memos = tMemosService.findAll();
		
		// 検索フォーム
		MemoSearchForm memoSearchForm = new MemoSearchForm();
		memoSearchForm.setMatchForTitle(SearchMatch.PERFECT);
		memoSearchForm.setMatchForDetail(SearchMatch.PERFECT);
		memoSearchForm.setMatchForTitles(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
		memoSearchForm.setMatchForDetails(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
		
		// マッピング
		model.addAttribute("memos", memos);
		model.addAttribute("memoSearchForm", memoSearchForm);
		
		// ページ返却
		return "index";
	}
	
	/**
	 * 初期画面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public String pagination(Model model, Pageable pageable) {
		// メモ一覧取得
		Page<TMemos> memos = tMemosService.findAllPage(pageable);
		
		// 検索フォーム
		MemoSearchForm memoSearchForm = new MemoSearchForm();
		memoSearchForm.setMatchForTitle(SearchMatch.PERFECT);
		memoSearchForm.setMatchForDetail(SearchMatch.PERFECT);
		memoSearchForm.setMatchForTitles(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
		memoSearchForm.setMatchForDetails(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
		
		// マッピング
		model.addAttribute("memos", memos.getContent());
		model.addAttribute("memoSearchForm", memoSearchForm);
		model.addAttribute("pages", memos);
		
		// ページ返却
		return "pagination";
	}
	
	/**
	 * 検索
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@ModelAttribute @Validated MemoSearchForm memoSearchForm, BindingResult result, Model model) throws Exception {
		// バリデーション
		if(result.hasErrors()) {
			// 検索フォーム
			memoSearchForm.setMatchForTitles(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
			memoSearchForm.setMatchForDetails(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
			
			// マッピング
			model.addAttribute("searchMemoForm", memoSearchForm);
			
			return "index";
		}
		
		// メモ検索
		Collection<TMemos> memos = tMemosService.findBy(memoSearchForm.getId(), memoSearchForm.getTitle(), memoSearchForm.getDetail(), memoSearchForm.getUserId(),
				memoSearchForm.getMatchForTitle(), memoSearchForm.getMatchForDetail());
		
		// 検索フォーム
		memoSearchForm.setMatchForTitles(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
		memoSearchForm.setMatchForDetails(List.of(SearchMatch.PERFECT, SearchMatch.PARTIAL));
		
		// マッピング
		model.addAttribute("memos", memos);
		model.addAttribute("searchMemoForm", memoSearchForm);
		
		// ページ返却
		return "index";
	}
	
	/**
	 * 詳細
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable Integer id) {
		// ページ返却
		TMemos memo = tMemosService.findById(id).orElse(new TMemos());
		
		// マッピング
		model.addAttribute("memo", memo);
		
		return "detail";
	}
	
	/**
	 * 新規登録
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute TMemosForm memosForm) {
		// 新規登録
		TMemos memos = memosForm.copy();
		boolean result = tMemosService.register(memos);
		
		// ページ返却
		return "redirect:/memo/";
	}
	
	/**
	 * 更新
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @ModelAttribute TMemosForm memosForm) {
		// 更新
		TMemos memos = memosForm.copy();
		boolean result = tMemosService.update(memos);
		
		// ページ返却
		return "redirect:/memo/";
	}	
	
	/**
	 * 削除
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Model model, @ModelAttribute TMemosForm memosForm) {
		// 削除
		TMemos memos = memosForm.copy();
		boolean result = tMemosService.delete(memos);
		
		// ページ返却
		return "index";
	}
}
