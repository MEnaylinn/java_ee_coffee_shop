package com.coffee.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hostmdy.crypto.PasswordEncoder;
import com.hostmdy.crypto.PasswordValidator;

public class UserDAO {
	
	private final DataSource dataSource;
	
	private Connection connection;
	private PreparedStatement pStm;
	private ResultSet rs;


	public UserDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public User getUserByPhone(String phone) {
			User user=null;
		try {
			connection=dataSource.getConnection();
			pStm=connection.prepareStatement("Select * from user where phone=?");
			pStm.setString(1, phone);
			rs=pStm.executeQuery();
			
			while(rs.next()) {
				user=new User(rs.getLong("id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("phone"),
						rs.getString( "email"),
						rs.getString( "role"),
						rs.getString("password"),
						rs.getBoolean("status"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return user;
		
	}
	public boolean isUserValid(String phone,String pass){
		boolean valid=false;
		User user=getUserByPhone(phone);
		
		if(user==null) {
			return valid;
		}
		
		try {
			if(PasswordValidator.validatePassword(pass, user.getPassword())&&user.getStatus()) {
				valid=true;
			}
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return valid;
		
	}
	
	
//	public boolean isUserValid(String email,String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
//		String password=PasswordEncoder.encode(pass);
//		boolean con=false;
//		int valid=0;
//		try {
//			connection=dataSource.getConnection();
//			pStm=connection.prepareStatement("select * from user where email=? and password=?");
//			pStm.setString(1, email);
//			pStm.setString(2, password);
//			valid=pStm.executeUpdate();		
//			if(valid>0) {
//				con=true;
//			}else {
//				con=false;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return con;
//		
//		
//	}
	
	
	
	public int register(User user) {
		int rowEffected=0;
		try {
			connection=dataSource.getConnection();
			pStm=connection.prepareStatement("insert into user "
					+ "(name,phone,email,password,address,role,status) "
					+ "values(?,?,?,?,?,?,?)");
			
			pStm.setString(1, user.getName());
			pStm.setString(2, user.getPhone());
			pStm.setString(3, user.getEmail());
			pStm.setString(4, user.getPassword());
			pStm.setString(5, user.getAddress());
			pStm.setString(6, user.getRole());
			pStm.setBoolean(7, user.getStatus());
			
			rowEffected=pStm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
			
		return rowEffected;
	}

}
