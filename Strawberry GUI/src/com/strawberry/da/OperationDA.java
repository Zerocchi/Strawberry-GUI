package com.strawberry.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.strawberry.da.ConnectionProvider;
import com.strawberry.model.Customer;
import com.strawberry.model.Order;

public class OperationDA {
	
	private Connection con = null;
	private int STATUS = 0;

	public boolean login(String username, char[] password) {
		
		if(username != null && password != null) {
			boolean status = false;
	        PreparedStatement pst = null;
	        ResultSet rs = null;
	
	        try {
	            con = ConnectionProvider.getCon();
	
	            pst = con
	                    .prepareStatement("select * from admins where admin_name=? and admin_pass=?");
	            pst.setString(1, username);
	            pst.setString(2, String.valueOf(password));
	
	            rs = pst.executeQuery();
	            status = rs.next();
	
	        } catch (Exception e) {
	            System.out.println(e);
	        } 
	        return status;
			}
		return false;
	}

	public int newCustomer(Customer c) {
		
		/***
		 * Return order number
		 */
		
		ResultSet rs = null;
		int orderId = 0;
		
		try {
			// enter customer details
			if(con == null)
				con = ConnectionProvider.getCon();
			PreparedStatement ps=con.prepareStatement("insert into customer (customer_id, customer_name, customer_mail, customer_phone, "
					+ "customer_address) values (cust_seq.nextval, ?, ?, ?, ?)");  
			ps.setString(1, c.getCustomerName());
			ps.setString(2, c.getCustomerMail());
			ps.setString(3, c.getCustomerPhone());
			ps.setString(4, c.getCustomerAddress());
			              
			ps.executeUpdate();
			
			// get last customer ID
			int lastCustId = 0;
			Statement st = con.createStatement();
			ResultSet rs1 = st.executeQuery("select customer_id from customer order by customer_id desc");
			if(rs1.next()){
				lastCustId = rs1.getInt("customer_id");
			}
			
			// insert into order
			if(con == null)
				con = ConnectionProvider.getCon();
			PreparedStatement ps2=con.prepareStatement("insert into orders (order_id, admin_id, customer_id) values "
					+ "(order_seq.nextval, 1, ?)");  
			ps2.setInt(1, lastCustId);
			              
			ps2.executeUpdate();
			
			// get order ID
			Statement st2 = con.createStatement();
			ResultSet rs3 = st2.executeQuery("select order_id from orders order by order_id desc");
			if(rs3.next()){
				orderId = rs3.getInt("order_id");
			}
		
		} catch (SQLException e) {
            e.printStackTrace();
        }
		return orderId;
	}	
	
	public int getLastOrderId(){
		
		int orderId = 0;
		
		if(con == null)
			con = ConnectionProvider.getCon();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select order_id from orders order by order_id desc");
			if(rs.next()){
				orderId = rs.getInt("order_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orderId;
	}

	public void setTotalPrice(int lastOrderId, double price) {
		
		try {
			// update total price
			if(con == null)
				con = ConnectionProvider.getCon();
			PreparedStatement ps=con.prepareStatement("update orders set total_price = ? where order_id = ?");  
			ps.setDouble(1, price);
			ps.setInt(2, lastOrderId);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		try {
			// select all from orders
			if(con == null)
				con = ConnectionProvider.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from orders"); 
			while(rs.next()){
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setAdminId(rs.getInt("admin_id"));
				order.setCustomerId(rs.getInt("customer_id"));
				orders.add(order);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return orders;
	}
}

