package com.ssafy.happyhouse.qna.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.qna.model.Answer;
import com.ssafy.happyhouse.qna.model.Question;
import com.ssafy.happyhouse.qna.model.mapper.AnswerMapper;
import com.ssafy.happyhouse.qna.model.mapper.QuestionMapper;

@Service
public class QnAServiceImpl implements QnAService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Question> retrieveQuestion() {
		return sqlSession.getMapper(QuestionMapper.class).selectQuestion();
	}

	@Override
	public Question detailQuestion(int no) {
		return sqlSession.getMapper(QuestionMapper.class).selectQuestionByNo(no);
	}

	@Override
	public boolean writeQuestion(Question question) {
		return sqlSession.getMapper(QuestionMapper.class).insertQuestion(question) == 1;
	}

	@Override
	public boolean updateQuestion(Question question) {
		return sqlSession.getMapper(QuestionMapper.class).updateQuestion(question) == 1;
	}

	@Override
	public boolean deleteQuestion(int no) {
		return sqlSession.getMapper(QuestionMapper.class).deleteQuestion(no) == 1;
	}
	
	@Override
	public Answer detailAnswer(int parentNo) {
		return sqlSession.getMapper(AnswerMapper.class).selectAnswerByParentNo(parentNo);
	}

	@Override
	public boolean writeAnswer(Answer answer) {
		return sqlSession.getMapper(AnswerMapper.class).insertAnswer(answer) == 1;
	}

	@Override
	public boolean updateAnswer(Answer answer) {
		return sqlSession.getMapper(AnswerMapper.class).updateAnswer(answer) == 1;
	}

	@Override
	public boolean deleteAnswer(int no) {
		return sqlSession.getMapper(AnswerMapper.class).deleteAnswer(no) == 1;
	}

}
