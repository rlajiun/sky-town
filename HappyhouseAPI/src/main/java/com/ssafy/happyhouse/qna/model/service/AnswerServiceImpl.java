package com.ssafy.happyhouse.qna.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.qna.model.Answer;
import com.ssafy.happyhouse.qna.model.mapper.AnswerMapper;

@Service
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	private AnswerMapper answerMapper;

	@Override
	public Answer detailAnswer(int parentNo) {
		return answerMapper.selectAnswerByParentNo(parentNo);
	}

	@Override
	public boolean writeAnswer(Answer answer) {
		return answerMapper.insertAnswer(answer) == 1;
	}

	@Override
	public boolean updateAnswer(Answer answer) {
		return answerMapper.updateAnswer(answer) == 1;
	}

	@Override
	public boolean deleteAnswer(int no) {
		return answerMapper.deleteAnswer(no) == 1;
	}

}
