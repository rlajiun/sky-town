package com.ssafy.happyhouse.qna.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.qna.model.Question;
import com.ssafy.happyhouse.qna.model.mapper.QuestionMapper;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<Question> retrieveQuestion() {
		return questionMapper.selectQuestion();
	}

	@Override
	public Question detailQuestion(int no) {
		return questionMapper.selectQuestionByNo(no);
	}

	@Override
	public boolean writeQuestion(Question question) {
		return questionMapper.insertQuestion(question) == 1;
	}

	@Override
	public boolean updateQuestion(Question question) {
		return questionMapper.updateQuestion(question) == 1;
	}

	@Override
	public boolean deleteQuestion(int no) {
		return questionMapper.deleteQuestion(no) == 1;
	}

}
