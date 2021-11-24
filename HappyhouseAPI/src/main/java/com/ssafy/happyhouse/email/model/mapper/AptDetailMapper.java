package com.ssafy.happyhouse.email.model.mapper;

import java.sql.SQLException;
import java.util.Map;

public interface AptDetailMapper {
	Map<String, String> getAptDetail(String aptCode) throws SQLException;
}
