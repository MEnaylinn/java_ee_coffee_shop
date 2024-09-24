package com.coffee.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class VoucherDAO {

	private final DataSource dataSource;

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStm;
	private ResultSet rs;

	public VoucherDAO(DataSource dataSource) {
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

	public List<Voucher> getAllVoucher() {
		List<Voucher> vouchers = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from voucher");

			while (rs.next()) {
				vouchers.add(new Voucher(rs.getLong("id"),
						rs.getLong("code"),
						rs.getDouble("total"),
						rs.getDate("date").toLocalDate()));
			};

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return vouchers;

	}
	public Long getCount() {
		Long count=(long) 0;
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select count(id) as id from voucher");

			while (rs.next()) {
				count=rs.getLong("id");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
		
	}

	public int addVoucher(Voucher vou) {
		int rowEffected=0;
		boolean cond=false;
		try {
			connection=dataSource.getConnection();
			pStm=connection.prepareStatement("insert into voucher "
					+ "(code,total,date) "
					+ "values(?,?,?)");
			
			pStm.setLong(1, vou.getCode());
			pStm.setDouble(2, vou.getTotal());
			pStm.setDate(7,Date.valueOf(vou.getDate()));
			
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
