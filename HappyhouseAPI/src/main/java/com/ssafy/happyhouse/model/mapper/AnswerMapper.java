package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.Answer;

public interface AnswerMapper {
	public Answer selectAnswerByParentNo(int parentNo);

	public int insertAnswer(Answer answer);

	public int updateAnswer(Answer answer);

	public int deleteAnswer(int no);
}
