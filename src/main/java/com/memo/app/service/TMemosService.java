package com.memo.app.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.memo.app.common.enums.SearchMatch;
import com.memo.app.entity.TMemos;
import com.memo.app.repository.TMemosRepository;

@Service
public class TMemosService {

	@Autowired
	private TMemosRepository tMemosRepository;
	
	/**
	 * メモ一覧取得
	 * @return
	 */
	public Collection<TMemos> findAll(){
		return tMemosRepository.findAll();
	}
	
	/**
	 * メモ一覧ページ取得
	 * @return
	 */
	public Page<TMemos> findAllPage(Pageable pageable){
		long total = tMemosRepository.count();
		List<TMemos> memos;
		if (0 < total) {
			memos = tMemosRepository.findAllPage(pageable);
		} else {
			memos = Collections.emptyList();
		}		
		return new PageImpl<>(memos, pageable, total);
	}
	
	/**
	 * メモ詳細取得
	 * @return
	 */
	public Optional<TMemos> findById(int id){
		return tMemosRepository.findById(id);
	}
	
	/**
	 * メモ検索
	 * @return
	 */
	public Collection<TMemos> findBy(Integer id, String title, String detail, Integer userId, SearchMatch matchForTitle, SearchMatch matchForDetail){
		return tMemosRepository.findBy(id, title, detail, userId, matchForTitle, matchForDetail);
	}
	
	/**
	 * メモ新規登録
	 * @return
	 */
	public boolean register(TMemos memo){
		tMemosRepository.register(memo);
		return true;
	}
	
	/**
	 * メモ更新
	 * @return
	 */
	public boolean update(TMemos memo){
		tMemosRepository.update(memo);
		return true;
	}
	
	/**
	 * メモ削除
	 * @return
	 */
	public boolean delete(TMemos memo){
		tMemosRepository.delete(memo);
		return true;
	}
}
