package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.common.DBManager;
import board.dto.UserDto;

public class LoginDaoImpl implements LoginDao {
	private static LoginDaoImpl instance;
	
	private LoginDaoImpl() {}
	
	
	public static LoginDaoImpl getInstance() {
		if( instance == null ) {
			instance = new LoginDaoImpl();
		}
		
		return instance;
	}
	
	@Override
	public UserDto login(String userEmail) {
		
		UserDto userDto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			String sql = 
					"SELECT USER_SEQ, USER_NAME, USER_PASSWORD, USER_EMAIL, USER_PROFILE_IMAGE_URL, USER_REGISTER_DATE " + 
					"  FROM USER WHERE USER_EMAIL = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  userEmail);
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();

			if(rs.next()) {
				userDto = new UserDto();
				userDto.setUserSeq(rs.getInt("USER_SEQ"));
				userDto.setUserName(rs.getString("USER_NAME"));
				userDto.setUserPassword(rs.getString("USER_PASSWORD"));
				userDto.setUserEmail(rs.getString("USER_EMAIL"));
				userDto.setUserProfileImageUrl(rs.getString("USER_PROFILE_IMAGE_URL"));
				userDto.setUserRegisterDate(rs.getDate("USER_REGISTER_DATE"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.releaseConnection(rs, pstmt, con);
		}
		
		return userDto;
	}

}
