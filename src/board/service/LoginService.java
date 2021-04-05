package board.service;

import board.dto.UserDto;

public interface LoginService {
	public UserDto login(String userEmail, String userPassword);
}
