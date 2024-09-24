package com.coffee.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ItemDAO {

	private final DataSource dataSource;

	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStm;
	private ResultSet rs;

	public ItemDAO(DataSource dataSource) {
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

	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from item");

			while (rs.next()) {
				items.add(new Item(rs.getLong("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getBoolean("status")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return items;

	}
	public List<Item> getFoodItem() {
		List<Item> items = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from item where category='food'");

			while (rs.next()) {
				items.add(new Item(rs.getLong("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getBoolean("status")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return items;

	}

	public List<Item> getDrinkItem() {
		List<Item> items = new ArrayList<>();
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from item where category='drink'");

			while (rs.next()) {
				items.add(new Item(rs.getLong("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getBoolean("status")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return items;

	}
	public Item getbyID(Long id) {
		Item item=null;
		try {
			connection = dataSource.getConnection();

			pStm = connection.prepareStatement("select * from item where id=?");
			pStm.setLong(1, id);
			rs = pStm.executeQuery();
			

			while (rs.next()) {
				item=new Item(rs.getLong("id"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getBoolean("status"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return item;

	}
	public int addItem(Item item) {
		int rowEffected=0;
		try {
			connection=dataSource.getConnection();
			pStm=connection.prepareStatement("insert into item "
					+ "(name,category,price,status) "
					+ "values(?,?,?,?)");
			
			pStm.setString(1, item.getName());
			pStm.setString(2, item.getCategory());
			pStm.setDouble(3, item.getPrice());
			pStm.setBoolean(4, item.getStatus());
			
			rowEffected=pStm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
			
		return rowEffected;
	}
	public int updateItem(Item item) {
		int rowEffected=0;
		try {
			connection=dataSource.getConnection();
			pStm=connection.prepareStatement("UPDATE `item` SET `name` = ?, `category` = ?, `price` = ?, `status` = ? WHERE (`id` = ?);"
					+ "");
			
			pStm.setString(1, item.getName());
			pStm.setString(2, item.getCategory());
			pStm.setDouble(3, item.getPrice());
			pStm.setBoolean(4, item.getStatus());
			pStm.setLong(5, item.getId());
			
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
