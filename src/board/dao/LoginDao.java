package board.dao;

import board.dto.UserDto;

public interface LoginDao {
	public UserDto login(String userEmail);
}
