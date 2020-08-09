/*
 * Project Name: RabbitLogisticsWareHouse
 * FileName: MainFrm.java
 * Created Date: 2019-07-25
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 
 * 
 */

package com.logisticsSystem.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.logisticsSystem.controller.DbController;
import com.logisticsSystem.controller.MariaConnector;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginFrm extends JFrame {

	private static LoginFrm frame = null;
	private static MgtFrm mgtFrm = null;
	private JPanel contentPane = null;
	
	private JTextField txtEmail = null;
	private JPasswordField txtPasswd = null;
	private PreparedStatement pstmt = null;
	
	/**
	 * Create the frame.
	 */
	public LoginFrm() {
		setResizable(false);
		
		setTitle("Rabbit Logistics Warehouse System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 170);


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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("�̸���(Email)");
		lblEmail.setBounds(12, 51, 89, 15);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(141, 45, 194, 21);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPasswd = new JLabel("��й�ȣ(Password)");
		lblPasswd.setBounds(12, 78, 125, 15);
		contentPane.add(lblPasswd);
		
		txtPasswd = new JPasswordField();
		txtPasswd.setBounds(141, 74, 194, 21);
		contentPane.add(txtPasswd);
		
		JLabel lblRabbitLogisticsWarehouse = new JLabel("Rabbit Logistics WareHouse");
		lblRabbitLogisticsWarehouse.setFont(new Font("�������", Font.BOLD, 16));
		lblRabbitLogisticsWarehouse.setBounds(12, 10, 260, 15);
		contentPane.add(lblRabbitLogisticsWarehouse);
		
		JButton btnLogin = new JButton("�α���(Login)");
		btnLogin.addActionListener( this.btnLoginClicked() );
		btnLogin.setBounds(12, 105, 323, 23);
		contentPane.add(btnLogin);
		
		setVisible(true);
	}
	
	public ActionListener btnLoginClicked(){
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean state = true;
				
				int memberID = -1;
				String Email = txtEmail.getText();
				String Passwd = txtPasswd.getText();
				ResultSet rs = null;
				
				// �̸���
				if ( Email.isEmpty() ){
					JOptionPane.showMessageDialog(null, "�̸����� �Է��ϼ���.\n(Please enter your email.)", "�˸�(Alert)",
														JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}
				
				// ��й�ȣ
				if ( Passwd.isEmpty() && state == true ){
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.\n(Please enter a password)", "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}
				
				// ���̵�, ��й�ȣ Ȯ��
				DbController dbController = new DbController();
				String query = "select id, email, passwd from logistics_member where email = ? and passwd = ?";

				Connection conn = dbController.getConn();
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString( 1, Email );
					pstmt.setString( 2, Passwd );
					dbController.select(pstmt);
					rs = dbController.getResultSet();
					
				} catch (SQLException e) {
					e.printStackTrace();
					
					JOptionPane.showMessageDialog(null, e.getMessage(), "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
					state = false;
				}
				
				try {
					
					if (! rs.first() && state == true ){
						JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� Ȯ���ϼ���.\n(Please check your ID and password.)", 
													 "�˸�(Alert)", JOptionPane.INFORMATION_MESSAGE);
						state = false;
					}
					
					while(rs.next()){

						if(! rs.getString("email").equals(Email) ||
						   !rs.getString("passwd").equals(Passwd)){
							state = false;
							
							JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� Ȯ���ϼ���.\n(Please check your ID and password.)", 
									 "�˸�(Alert)", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							memberID = rs.getInt("id");
						}
					}
					
					pstmt.close();
					rs.close();
					conn.close();
					
					dbController.setResultSet(null);
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
					state = false;
					
				} // end of while
				
				// �� ����
				if ( state ){

					dispose();
					
					mgtFrm = new MgtFrm();
					mgtFrm.setMemberID(memberID);
					mgtFrm.setVisible(true);
				}
				
			}
		}; // end of ActionListener
		
		return actionListener;
		
	}
}
