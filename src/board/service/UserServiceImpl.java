package board.service;

import board.dao.UserDao;
import board.dao.UserDaoImpl;
import board.dto.UserDto;

public class UserServiceImpl implements UserService{
	
	private static UserServiceImpl instance;
	
	private UserServiceImpl() {}
	
	
	public static UserServiceImpl getInstance() {
		if( instance == null ) {
			instance = new UserServiceImpl();
		}
		
		return instance;
	}
	
	UserDao userDao = UserDaoImpl.getInstance();
	
	@Override
	public int userRegister(UserDto userDto) {
		return userDao.userRegister(userDto);
	}
}

