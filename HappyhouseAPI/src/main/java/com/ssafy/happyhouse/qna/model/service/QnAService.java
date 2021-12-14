package com.ssafy.happyhouse.qna.model.service;

import java.util.List;

import com.ssafy.happyhouse.qna.model.Answer;
import com.ssafy.happyhouse.qna.model.Question;

public interface QnAService {
	public List<Question> retrieveQuestion();

	public Question detailQuestion(int no);

	public boolean writeQuestion(Question question);

	public boolean updateQuestion(Question question);

	public boolean deleteQuestion(int no);
	
	public Answer detailAnswer(int parentNo);

	public boolean writeAnswer(Answer answer);

	public boolean updateAnswer(Answer answer);

	public boolean deleteAnswer(int no);
}
