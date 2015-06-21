package com.strawberry.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.strawberry.da.ConnectionProvider;

public class MainUI extends JFrame {
	
	public static MainUI window;
	private Connection con = null;
	private JTable table;
	private JPanel panel;
	private static Vector<String> columnNames = new Vector<>();
	private static Vector<Vector<Object>> data = new Vector<>();
	
	// method to call another window;
	public void logout(){
		window.setVisible(false);
		window.dispose();
		String[] args = {};
		try {
			com.strawberry.gui.LoginUI.main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JTable getData() {
		try {
			if(con == null)
				con = ConnectionProvider.getCon();
		
		Statement stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery("select o.order_id, c.customer_name, o.total_price "
	    		+ "from customer c, orders o where c.customer_id = o.customer_id");

	    // It creates and displays the table
	    table = new JTable(buildTableModel(rs));
	    //JOptionPane.showMessageDialog(null, new JScrollPane(table));
		} catch (Exception e){
			e.printStackTrace();
		}
		return table;
	}
	
	public MainUI() {
		
		panel = new JPanel();
	    
	    JScrollPane scrollPane = new JScrollPane(getData(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setPreferredSize(new Dimension(450, 200));
	    
	    panel.add(scrollPane);
	    add(panel, BorderLayout.NORTH);
	    
	    JPanel menuPanel = new JPanel();
	    
	    Button addUser = new Button("Add");
	    addUser.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] args = {};
				com.strawberry.gui.AddUser.main(args);
				
			}
			
		});
	    
	    Button refreshBtn = new Button("Refresh");
	    refreshBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.fireTableDataChanged();
				model.fireTableStructureChanged();
				panel.repaint();
				System.out.println("Table has been refreshed");
				
			}
	    	
	    });
	    
	    Button deleteBtn = new Button("Delete");
	    deleteBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				int selected = Integer.parseInt(model.getValueAt(row, 0).toString());
				
				model.removeRow(row);
				
				try {
					if(con == null)
						con = ConnectionProvider.getCon();
					
					PreparedStatement ps = con.prepareStatement("delete from orders where order_id=?");
	                ps.setInt(1, selected);
	                ps.executeUpdate();
	                
	                PreparedStatement ps2 = con.prepareStatement("delete from customer where customer_id in"
	                		+ " (select c.customer_id from customer c, orders o where c.customer_id = "
	                		+ " o.customer_id and o.order_id = ?)");
	                ps2.setInt(1, selected);
	                ps2.executeUpdate();
	                
	                
	            }
	            catch (Exception w) {
	                w.getStackTrace();
	            }  
			}
    	
	    });
	    
	    Button logout = new Button("Logout");
	    logout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
				
			}
	    	
	    });
	    
	    menuPanel.add(addUser);
	    menuPanel.add(refreshBtn);
	    menuPanel.add(deleteBtn);
	    menuPanel.add(logout);
	    add(menuPanel, BorderLayout.CENTER);

	    
	    
	    pack();
	    setSize(500, 400);
	    setTitle("Strawberry Restaurant");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	}
	
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
}
