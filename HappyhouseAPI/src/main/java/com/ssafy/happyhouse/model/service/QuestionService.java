package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Question;

public interface QuestionService {
	public List<Question> retrieveQuestion();

	public Question detailQuestion(int no);

	public boolean writeQuestion(Question question);

	public boolean updateQuestion(Question question);

	public boolean deleteQuestion(int no);
}
