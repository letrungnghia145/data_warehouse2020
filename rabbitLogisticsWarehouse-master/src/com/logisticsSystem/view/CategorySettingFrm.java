/*
 * Project Name: RabbitLogisticsWareHouse
 * FileName: CategorySetting.java
 * Created Date: 2019-07-27
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
 */
package com.logisticsSystem.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.logisticsSystem.controller.DbController;
import com.logisticsSystem.controller.MariaConnector;
import com.logisticsSystem.model.Logistics_store;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategorySettingFrm extends JFrame {

	private int MemberID;
	
	private String colNames[] = {"��ȣ", "�׸��"};			// ���̺� �÷� ����
	private DefaultTableModel model = new DefaultTableModel(colNames, 0){  //�� ���� ���ϰ� �ϴ� �κ�
		public boolean isCellEditable(int row, int column){
		    return false;
		}
	};		// ���̺� ������ �� ��ü ����
	
	private DbController dbController = null;
	private PreparedStatement pstmt = null;
    private Statement stmt = null;
    
	private JPanel contentPane;
	private JTable table;
	private JTextField txtCategoryName;
	private JTextField txtMCategoryName;
	private JTextField txtMID;

	public int getMemberID() {
		return MemberID;
	}

	public void setMemberID(int memberID) {
		MemberID = memberID;
	}
	/**
	 * Create the frame.
	 */
	public CategorySettingFrm(int memberID) {
		
		setMemberID( memberID );
		
		setResizable(false);
		setTitle("ī�װ� - ȯ�漳��(Category - Preferences)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dbController = new DbController();			// dbController �ʱ�ȭ

		/// ȭ�� ���
		/// ������ â ��� ����ϱ�
		// ������ frame ������ ����
		Dimension frameSize = getSize();
		// �ڽ��� windowscreen ������ ����
	
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		// ����غ��� �� ����� ��µǴ°� Ȯ���� �� �ִ�.
	
		System.out.println(frameSize + " " + windowSize);
	
		// ��: ������width-������width)/2, (������height-������height)/2
	
		setLocation((windowSize.width - frameSize.width) / 2,
					(windowSize.height - frameSize.height) / 2);
		

		table = new JTable(model);							// ���̺� ����
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(tableClicked());				// ���̺� Ŭ��
		table = resizeColumnWidth(table);					// ���̺� ũ�� ����
		scrollPane.setBounds(12, 58, 321, 303);
		contentPane.add(scrollPane);
		
		JPanel panel_add = new JPanel();
		panel_add.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_add.setBounds(344, 58, 388, 73);
		contentPane.add(panel_add);
		panel_add.setLayout(null);
		
		JLabel lblCategoryName = new JLabel("�׸��(Name)");
		lblCategoryName.setFont(new Font("����", Font.PLAIN, 12));
		lblCategoryName.setBounds(12, 36, 95, 15);
		panel_add.add(lblCategoryName);
		
		txtCategoryName = new JTextField();
		txtCategoryName.setFont(new Font("����", Font.PLAIN, 12));
		txtCategoryName.setBounds(108, 33, 142, 21);
		panel_add.add(txtCategoryName);
		txtCategoryName.setColumns(10);
		
		JLabel lblAddTitle = new JLabel("ī�װ� �׸� �߰�(Add category item)");
		lblAddTitle.setFont(new Font("����", Font.BOLD, 13));
		lblAddTitle.setBounds(12, 11, 265, 15);
		panel_add.add(lblAddTitle);
		
		JButton btnAdd = new JButton("�߰�(Add)");
		btnAdd.setFont(new Font("����", Font.PLAIN, 12));
		btnAdd.addActionListener( insertAction() );
		btnAdd.setBounds(257, 32, 119, 23);
		panel_add.add(btnAdd);
		
		JLabel lblTitle = new JLabel("ī�װ� - ȯ�漳��(Category - Preferences)");
		lblTitle.setFont(new Font("����", Font.BOLD, 16));
		lblTitle.setBounds(12, 25, 406, 23);
		contentPane.add(lblTitle);
		
		JPanel panel_modify = new JPanel();
		panel_modify.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_modify.setBounds(344, 141, 388, 91);
		contentPane.add(panel_modify);
		panel_modify.setLayout(null);
		
		JLabel label = new JLabel("�׸� ���� �� ����(Modify and Remove item)");
		label.setFont(new Font("����", Font.BOLD, 13));
		label.setBounds(12, 10, 376, 15);
		panel_modify.add(label);
		
		JLabel lblMCategoryName = new JLabel("�׸��(Name)");
		lblMCategoryName.setFont(new Font("����", Font.PLAIN, 12));
		lblMCategoryName.setBounds(12, 61, 95, 15);
		panel_modify.add(lblMCategoryName);
		
		txtMCategoryName = new JTextField();
		txtMCategoryName.setFont(new Font("����", Font.PLAIN, 12));
		txtMCategoryName.setColumns(10);
		txtMCategoryName.setBounds(108, 58, 142, 21);
		panel_modify.add(txtMCategoryName);
		
		JButton btnUpdate = new JButton("����(Update)");
		btnUpdate.setFont(new Font("����", Font.PLAIN, 12));
		btnUpdate.setBounds(255, 32, 121, 21);
		btnUpdate.addActionListener(updateAction());
		panel_modify.add(btnUpdate);
		
		JLabel lblMID = new JLabel("�ĺ���ȣ(ID)");
		lblMID.setFont(new Font("����", Font.PLAIN, 12));
		lblMID.setBounds(12, 35, 95, 15);
		panel_modify.add(lblMID);
		
		txtMID = new JTextField();
		txtMID.setEditable(false);
		txtMID.setFont(new Font("����", Font.PLAIN, 12));
		txtMID.setColumns(10);
		txtMID.setBounds(108, 32, 142, 21);
		panel_modify.add(txtMID);
		
		JButton btnRemove = new JButton("����(Remove)");
		btnRemove.setFont(new Font("����", Font.PLAIN, 12));
		btnRemove.setBounds(255, 58, 121, 21);
		btnRemove.addActionListener(removeAction());
		panel_modify.add(btnRemove);
		
		JButton btnClose = new JButton("�ݱ�(Close)");
		btnClose.setFont(new Font("����", Font.PLAIN, 12));
		btnClose.addActionListener(closedAction());
		btnClose.setBounds(611, 340, 121, 21);
		contentPane.add(btnClose);
		
		initSelect();
		setVisible(true);
	}
	
	private ActionListener insertAction(){
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String msg = "";
				
				if ( !txtCategoryName.getText().isEmpty() ){

					insert();
					msg = "���������� ��ϵǾ����ϴ�.\n(Successfully registered.)";
					JOptionPane.showMessageDialog(null, msg, "�˸�(Alert)", 
							JOptionPane.INFORMATION_MESSAGE);

					initSelect();
				} // end of if
			}
		};
		
		return actionListener;
	}
	
	private ActionListener updateAction(){
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String msg = "";
				
				msg = "�ش� �����͸� �����Ͻðڽ��ϱ�?\n(Are you sure you want to edit the data?)";
				int result = JOptionPane.showConfirmDialog(null, msg, "�˸�(Alert)", 
						JOptionPane.YES_NO_OPTION);
				
				// Yes, No
				if ( result == JOptionPane.YES_OPTION ){
	
					if ( txtMID.getText().isEmpty() ){
						
						msg = "�׸��� �����ϼ���.\n(Please select an item.)";
						JOptionPane.showMessageDialog(null, msg, "�˸�(Alert)", 
											JOptionPane.INFORMATION_MESSAGE);
						
					}else{
						update();
						msg = "������ �Ϸ�Ǿ����ϴ�.\n(Modification has been completed.)";
						JOptionPane.showMessageDialog(null, msg, "�˸�(Alert)", 
											JOptionPane.INFORMATION_MESSAGE);
						initSelect();
					} // end of if
					
				} // end of if
				
			}
		};
		
		return actionListener;
		
	}

	private ActionListener removeAction(){
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String msg = "";
				int row = -1;

				msg = "�ش� �����͸� �����Ͻðڽ��ϱ�?\n(Are you sure you want to delete this data?)";
				int result = JOptionPane.showConfirmDialog(null, msg, "�˸�(Alert)", 
						JOptionPane.YES_NO_OPTION);
				
				// Yes, No
				if ( result == JOptionPane.YES_OPTION ){
					
					if ( txtMID.getText().isEmpty() ){
						
						msg = "�׸��� �����ϼ���.\n(Please select an item.)";
						JOptionPane.showMessageDialog(null, msg, "�˸�(Alert)", 
											JOptionPane.INFORMATION_MESSAGE);
						
					}else{
						row = remove();
						msg = "������ �Ϸ�Ǿ����ϴ�.\n(Delete completed.)";
						JOptionPane.showMessageDialog(null, msg, "�˸�(Alert)", 
											JOptionPane.INFORMATION_MESSAGE);
						initSelect();
						
					} // end of if

				} // end of if
				
			}
		};
		
		return actionListener;
		
	}
	
	private ActionListener closedAction(){
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MgtFrm mgtFrm = new MgtFrm();
				mgtFrm.setMemberID(getMemberID());
				dispose();
				mgtFrm.setVisible(true);
			}
		};
		
		return actionListener;
	}
	
	private MouseAdapter tableClicked(){
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table.getSelectedRow();
				Object value;
				
				try{
					value = table.getValueAt(row, 0);
					txtMID.setText( String.valueOf( value ) );
					
					value = table.getValueAt(row, 1);
					txtMCategoryName.setText( String.valueOf( value ) );
					
					System.out.println(value);
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		};
		
		return mouseAdapter;
	}
	
	private void initSelect(){

		String query = "";
		MariaConnector mariaConnector = new MariaConnector();

		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		query = "select id, category from logistics_category order by id asc";

		model.setNumRows(0);							// JTable �ʱ�ȭ
		
		try {
			pstmt = conn.prepareStatement(query);
			dbController.select(pstmt);
			dbController.setModel(model);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} // end of try to catch
		
		rs = dbController.getResultSet();
		
		try {
			
			while(rs.next()){
				
				model.addRow(
						new Object[]{
								rs.getString("id"), rs.getString("category")
				});
				
			} // end of while
			
			pstmt.close();
			rs.close();
			conn.close();
			
			dbController.setResultSet(null);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} // end of while
		
	}
	
	private void insert(){
		
		String query = "";
		
		query = "insert into logistics_category(category) VALUES(?)";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, txtCategoryName.getText() );
			
			int rowNum = pstmt.executeUpdate();
			
			if ( rowNum > 0 ){
				System.out.println("���Լ���");
			}
			else{
				System.out.println("����");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			
			try{
				
				if ( conn != null ){
					conn.close();
				}
				if ( pstmt != null ){
					pstmt.close();
				}
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
			
		} // end of try to catch finally
		
	}
	
	private void update(){
		
		String query = "";
		
		query = "update logistics_category set category = ? where id = ?";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, txtMCategoryName.getText());
			pstmt.setInt(2, Integer.valueOf( txtMID.getText() ));
			
			int rowNum = pstmt.executeUpdate();
			
			if ( rowNum > 0 ){
				System.out.println("��������");
			}
			else{
				System.out.println("����");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			
			try{
				
				if ( conn != null ){
					conn.close();
				}
				if ( pstmt != null ){
					pstmt.close();
				}
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
			
		} // end of try to catch finally
		
	}
	
	private int remove(){
		
		int rowNum = 0;
		String query = "";
		
		query = "delete from logistics_category where id = ?";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.valueOf(txtMID.getText()));
			
			rowNum = pstmt.executeUpdate();
			
			if ( rowNum > 0 ){
				System.out.println("��������");
			}
			else{
				System.out.println("����");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			
			try{
				
				if ( conn != null ){
					conn.close();
				}
				if ( pstmt != null ){
					pstmt.close();
				}
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		return rowNum;
		
	}
	
	private JTable resizeColumnWidth(JTable table) {
		
        //JTable �� �÷� ���� ����
        table.getColumnModel().getColumn(0).setPreferredWidth(15);  //JTable �� �÷� ���� ����
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
		
		return table; 
		
	}
	
}
