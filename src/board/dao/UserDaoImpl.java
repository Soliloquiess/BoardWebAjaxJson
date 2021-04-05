package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.common.DBManager;
import board.dto.UserDto;

public class UserDaoImpl implements UserDao{
	
	private static UserDaoImpl instance;
	
	private UserDaoImpl() {}
	
	
	public static UserDaoImpl getInstance() {
		if( instance == null ) {
			instance = new UserDaoImpl();
		}
		
		return instance;
	}
	
	@Override
	public int userRegister(UserDto userDto) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int ret = -1;
		
		try {
			con = DBManager.getConnection();
			String sql = 
					"INSERT INTO USER " + 
					" (USER_NAME, USER_PASSWORD, USER_EMAIL, USER_PROFILE_IMAGE_URL, USER_REGISTER_DATE) " + 
					" VALUES ( ?, ?, ?, '', now() ) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  userDto.getUserName());
			pstmt.setString(2,  userDto.getUserPassword());
			pstmt.setString(3,  userDto.getUserEmail());

			ret = pstmt.executeUpdate();

		}catch(Exception e) {			
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return ret;
	}

}

