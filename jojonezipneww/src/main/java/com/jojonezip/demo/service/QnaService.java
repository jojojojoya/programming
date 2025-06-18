package com.jojonezip.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jojonezip.demo.mapper.QnaMapper;
import com.jojonezip.demo.vo.QnaVO;

@Service
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	
	public List<QnaVO> getQnaByProductId(int productId) {
	return qnaMapper.getQnaByProductId(productId);	
	}
	
	public List<QnaVO> getQnaByUserId(String userId) {
	return qnaMapper.getQnaByUserId(userId);	
	}
	
	public QnaVO getQnaByQnaId(int qnaId) {
	return qnaMapper.getQnaByQnaId(qnaId);	
	}
	
	public void updateQnaById(QnaVO qna) {
	qnaMapper.updateQnaById(qna);

}
	
	public void deleteQnaById(int qnaId) {
		qnaMapper.deleteQnaById(qnaId);
	}
	
	public void insertQnaById(QnaVO qna) {
		qnaMapper.insertQnaById(qna);
	}
}
