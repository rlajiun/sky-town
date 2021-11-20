package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Answer;
import com.ssafy.happyhouse.model.mapper.AnswerMapper;

@Qualifier
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
