package net.shiladitya.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Swing_Attendees extends JFrame
{
	private static Connection con;
	PreparedStatement pst;
	ResultSet rs;
	ResultSetMetaData rd;
	DefaultTableModel model;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCoun;
	private JTextField txtCon;
	private JTextField txtEmail;
	private JTextField txtName;
	public JTable table;
	private final JButton btnStats = new JButton("Get Attendee Statistics");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swing_Attendees frame = new Swing_Attendees();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void connect() throws SQLException,ClassNotFoundException
	{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo","root","stranger");
			JOptionPane.showMessageDialog(null, "Connection Success!");
	}
	
	public void table() throws SQLException
	{
		int a;
		pst = con.prepareStatement("SELECT * FROM ATTENDEES");
		rs = pst.executeQuery();
		rd = rs.getMetaData();
		a = rd.getColumnCount();
		model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		while(rs.next())
		{
			Vector<String> v = new Vector<String>();
			for(int i=1; i<=a; i++)
			{
				v.add(rs.getString("name"));
				v.add(rs.getString("email"));
				v.add(rs.getString("contact"));
				v.add(rs.getString("country"));
			}
			model.addRow(v);
		}
	}
	
	public void searchtable(String sql) throws SQLException
	{
		int a;
		pst = con.prepareStatement(sql);
		rs = pst.executeQuery();
		rd = rs.getMetaData();
		a = rd.getColumnCount();
		model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
		while(rs.next())
		{
			Vector<String> v = new Vector<String>();
			for(int i=1; i<=a; i++)
			{
				v.add(rs.getString("name"));
				v.add(rs.getString("email"));
				v.add(rs.getString("contact"));
				v.add(rs.getString("country"));
			}
			model.addRow(v);
		}
	}
	
	/**
	 * Create the frame.
	 */
	
	private int getGeneratedId(PreparedStatement preparedStatement) throws SQLException {
        int generatedId = -1;
        try (var resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        }
        return generatedId;
    }
	
	public Swing_Attendees()throws SQLException,ClassNotFoundException
	{
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label1 = new JLabel("Full Name:");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label1.setBounds(10, 34, 101, 33);
		contentPane.add(label1);
		
		JLabel lblEnterName = new JLabel("Email:");
		lblEnterName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterName.setBounds(10, 78, 101, 33);
		contentPane.add(lblEnterName);
		
		JLabel lblEnterSalary = new JLabel("Contact:");
		lblEnterSalary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterSalary.setBounds(10, 122, 101, 33);
		contentPane.add(lblEnterSalary);
		
		JLabel lblEnterAddress = new JLabel("Country:");
		lblEnterAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterAddress.setBounds(10, 166, 101, 33);
		contentPane.add(lblEnterAddress);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name, email, contact, country;
				name = txtName.getText();
				contact = txtCon.getText();
				email = txtEmail.getText();
				country = txtCoun.getText();
				try {
					pst = con.prepareStatement("INSERT INTO ATTENDEES (NAME, EMAIL, CONTACT, COUNTRY) VALUES(?,?,?,?)",
												PreparedStatement.RETURN_GENERATED_KEYS);
					pst.setString(1, name);
					pst.setString(2, email);
					pst.setString(3, contact);
					pst.setString(4, country);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Inserted witn ID: "+getGeneratedId(pst));
					txtName.setText("");
					txtEmail.setText("");
					txtCon.setText("");
					txtCoun.setText("");
					txtName.requestFocus();
					table();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setBounds(67, 223, 89, 23);
		contentPane.add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name, email, contact, country;
				name = txtName.getText();
				contact = txtCon.getText();
				email = txtEmail.getText();
				country = txtCoun.getText();
				try {
					pst = con.prepareStatement("UPDATE ATTENDEES SET email=?, contact=?, country=? where name=?");
					pst.setString(4, name);
					pst.setString(1, email);
					pst.setString(2, contact);
					pst.setString(3, country);
					int n = pst.executeUpdate();
					if(n!=0)
						JOptionPane.showMessageDialog(null, "Record Updated Successfully!");
					else
						JOptionPane.showMessageDialog(null, "No such record to update!");
					txtName.setText("");
					txtEmail.setText("");
					txtCon.setText("");
					txtCoun.setText("");
					txtName.requestFocus();
					table();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(166, 223, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				name = txtName.getText();
				try {
					pst = con.prepareStatement("DELETE FROM ATTENDEES WHERE name=?");
					pst.setString(1, name);
					int n = pst.executeUpdate();
					if(n!=0)
						JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
					else
						JOptionPane.showMessageDialog(null, "No such record to delete!");
					txtName.setText("");
					txtEmail.setText("");
					txtCon.setText("");
					txtCoun.setText("");
					txtName.requestFocus();
					table();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(265, 223, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name;
				name = txtName.getText();
				String sql = "SELECT * FROM ATTENDEES WHERE name =\""+name+"\"";
				try {
					txtName.setText("");
					txtEmail.setText("");
					txtCon.setText("");
					txtCoun.setText("");
					txtName.requestFocus();
					searchtable(sql);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(67, 257, 144, 23);
		contentPane.add(btnSearch);
		
		txtCoun = new JTextField();
		txtCoun.setBounds(106, 166, 246, 33);
		contentPane.add(txtCoun);
		txtCoun.setColumns(10);
		
		txtCon = new JTextField();
		txtCon.setColumns(10);
		txtCon.setBounds(106, 122, 246, 33);
		contentPane.add(txtCon);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(106, 78, 246, 33);
		contentPane.add(txtEmail);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(106, 34, 246, 33);
		contentPane.add(txtName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(362, 11, 371, 308);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Email", "Contact", "Country"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnRef = new JButton("Refresh");
		btnRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { table(); } catch(SQLException whatever) {}
			}
		});
		btnRef.setBounds(221, 257, 133, 23);
		contentPane.add(btnRef);
		btnStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CallableStatement callstat = con.prepareCall("{call AttStats}");
					ResultSet resultSet = callstat.executeQuery();
					while (resultSet.next()) {
		                String country = resultSet.getString("country");
		                int attendeeCount = resultSet.getInt("attendee_count");

		                System.out.println("Country: " + country + ", Attendee Count: " + attendeeCount);
		            }}
				catch (SQLException e1) {}
				
			}
		});
		btnStats.setBounds(67, 291, 287, 28);
		contentPane.add(btnStats);
		
		table();
	}
}
