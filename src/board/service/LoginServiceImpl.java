package board.service;

import board.dao.LoginDao;
import board.dao.LoginDaoImpl;
import board.dto.UserDto;

public class LoginServiceImpl implements LoginService{
	
	private static LoginServiceImpl instance;
	
	private LoginServiceImpl() {}
	
	
	public static LoginServiceImpl getInstance() {
		if( instance == null ) {
			instance = new LoginServiceImpl();
		}
		
		return instance;
	}
	
	LoginDao loginDao = LoginDaoImpl.getInstance();
	
	@Override
	public UserDto login(String userEmail, String userPassword) {
		
		UserDto userDto = loginDao.login(userEmail);
		
		if( userDto != null && userDto.getUserPassword().equals(userPassword)) {
			return userDto;
		}else {
			return null;
		}
	}
}

