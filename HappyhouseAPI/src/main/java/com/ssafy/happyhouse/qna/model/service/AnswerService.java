package com.ssafy.happyhouse.qna.model.service;

import com.ssafy.happyhouse.qna.model.Answer;

public interface AnswerService {
	public Answer detailAnswer(int parentNo);

	public boolean writeAnswer(Answer answer);

	public boolean updateAnswer(Answer answer);

	public boolean deleteAnswer(int no);
}
