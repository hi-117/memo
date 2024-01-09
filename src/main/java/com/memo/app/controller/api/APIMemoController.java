package com.memo.app.controller.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.memo.app.entity.TMemos;
import com.memo.app.form.TMemosForm;
import com.memo.app.service.TMemosService;

@RestController
@RequestMapping(value="/api/memo")
public class APIMemoController {

	@Autowired
	TMemosService tMemosService;
	
	/**
	 * メモ一覧取得
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<TMemos> index() {
		Collection<TMemos> memos = tMemosService.findAll();
		return memos;
	}
	
	/**
	 * メモ詳細取得
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public TMemos detail(@PathVariable("id") int id) {
		TMemos memo = tMemosService.findById(id).orElse(new TMemos());
		return memo;
	}
	
	/**
	 * メモ新規登録
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public boolean register(@RequestBody TMemosForm memosForm) {
		TMemos memos = memosForm.copy();
		boolean result = tMemosService.register(memos);
		return result;
	}
	
	/**
	 * メモ更新
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public boolean update(@RequestBody TMemosForm memosForm) {
		TMemos memos = memosForm.copy();
		boolean result = tMemosService.update(memos);
		return result;
	}
	
	/**
	 * メモ削除
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean delete(@RequestBody TMemosForm memosForm) {
		TMemos memos = memosForm.copy();
		boolean result = tMemosService.delete(memos);
		return result;
	}
}
