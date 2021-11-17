package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.Question;

public interface QuestionMapper {
	public List<Question> selectQuestion();

	public Question selectQuestionByNo(int no);

	public int insertQuestion(Question question);

	public int updateQuestion(Question question);

	public int deleteQuestion(int no);
}
