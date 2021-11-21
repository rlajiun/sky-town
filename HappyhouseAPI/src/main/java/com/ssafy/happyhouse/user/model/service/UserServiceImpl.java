package com.ssafy.happyhouse.user.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.user.model.User;
import com.ssafy.happyhouse.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int idCheck(String checkId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).idCheck(checkId); // 0 or 1
	}

	@Override
	public void registerMember(User memberDto) throws Exception {
//		validation check
		sqlSession.getMapper(UserMapper.class).registerMember(memberDto);
	}

	@Override
	public User login(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).login(map);
	}

	@Override
	public List<User> listMember() throws Exception {
		return sqlSession.getMapper(UserMapper.class).listMember();
	}

	@Override
	public User getMember(String userId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getMember(userId);
	}

	@Override
	public void updateMember(User memberDto) throws Exception {
		sqlSession.getMapper(UserMapper.class).updateMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		sqlSession.getMapper(UserMapper.class).deleteMember(userId);
	}
}
