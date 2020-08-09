/*
 * Project Name: RabbitLogisticsWareHouse
 * FileName: StoreUpdate.java
 * Created Date: 2019-07-26
 * Author: Dodo (rabbit.white@daum.net)
 * Description:
 * 2019-07-26 / ����� �������̽� ������ / Dodo / rabbit.white@daum.net
 * 2019-07-27 / insert ���� / Dodo / rabbit.white@daum.net
 * 2019-07-27 / ���� ���� �������� ��� �߰� / Dodo / rabbit.white@daum.net
 * 
 * Reference:
 * 2019-07-27 / https://stackoverflow.com/questions/41291893/how-to-set-get-images-blob-using-the-resultset
 */
package com.logisticsSystem.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.logisticsSystem.controller.DbController;
import com.logisticsSystem.controller.FileController;
import com.logisticsSystem.controller.Function;
import com.logisticsSystem.controller.MariaConnector;
import com.logisticsSystem.model.*;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StoreUpdate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int memberID;
	
	private JPanel contentPane;
	private JLabel usrImage;
	
	private InetAddress local;

	private JLabel lblID; 
	private JLabel lblFileName;
	private JLabel lblFileSize;
	
	private JTextField txtSubject;
	private JTextField txtBarcode;
	private JTextField txtAuthor;
	private JTextField txtPrice;
	private JTextField txtFilePath;
	
	private JComboBox<String> cmbCategory;
	private Image image;

	private DbController dbController = null;
	private PreparedStatement pstmt = null;
    private Statement stmt = null;
    
	private StoreUpdate dialogFrm = null;
	private JTextField txtCount;

	private Function function = null;

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	/**
	 * Create the frame.
	 */
	public StoreUpdate(int id, int memberID) {
		
		setMemberID(memberID);
		setTitle("������ - â�� ����(Item - Warehouse Detail)");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dbController = new DbController();			// dbController �ʱ�ȭ
		function = new Function();					// function()
		
		// IP�ּ�
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		
		JLabel lblTitle = new JLabel("������ - â�� ��(Item - Store Detail)");
		lblTitle.setFont(new Font("�������", Font.BOLD, 16));
		lblTitle.setBounds(12, 24, 324, 19);
		contentPane.add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(12, 66, 639, 222);
		contentPane.add(panel);
		panel.setLayout(null);
		
		usrImage = new JLabel("���� ����(No Picture)");
		usrImage.setFont(new Font("����", Font.PLAIN, 12));
		usrImage.setBounds(12, 10, 126, 159);
		panel.add(usrImage);
		
		JLabel lblSubject = new JLabel("�׸��(Subject)");
		lblSubject.setFont(new Font("����", Font.PLAIN, 12));
		lblSubject.setBounds(175, 49, 226, 15);
		panel.add(lblSubject);
		
		txtSubject = new JTextField();
		txtSubject.setFont(new Font("����", Font.PLAIN, 12));
		txtSubject.setBounds(413, 46, 214, 21);
		panel.add(txtSubject);
		txtSubject.setColumns(10);
		
		JLabel lblBarcode = new JLabel("���� - ���ڵ�(Handwriting - Barcode)");
		lblBarcode.setFont(new Font("����", Font.PLAIN, 12));
		lblBarcode.setBounds(175, 77, 226, 15);
		panel.add(lblBarcode);
		
		txtBarcode = new JTextField();
		txtBarcode.setFont(new Font("����", Font.PLAIN, 12));
		txtBarcode.setColumns(10);
		txtBarcode.setBounds(413, 73, 214, 21);
		panel.add(txtBarcode);
		
		JLabel lblAuthor = new JLabel("������ �Ǵ� ����(Author)");
		lblAuthor.setFont(new Font("����", Font.PLAIN, 12));
		lblAuthor.setBounds(175, 105, 226, 15);
		panel.add(lblAuthor);
		
		txtAuthor = new JTextField();
		txtAuthor.setFont(new Font("����", Font.PLAIN, 12));
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(413, 101, 214, 21);
		panel.add(txtAuthor);
		
		JLabel lblPrice = new JLabel("����(Price)");
		lblPrice.setFont(new Font("����", Font.PLAIN, 12));
		lblPrice.setBounds(175, 133, 226, 15);
		panel.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.addKeyListener(function.checkKeyAdapter());
		txtPrice.setFont(new Font("����", Font.PLAIN, 12));
		txtPrice.setBounds(413, 130, 214, 21);
		panel.add(txtPrice);
		
		JLabel lblCount = new JLabel("����(Count)");
		lblCount.setFont(new Font("����", Font.PLAIN, 12));
		lblCount.setBounds(175, 160, 226, 15);
		panel.add(lblCount);
		
		JLabel lblFile = new JLabel("����(File)");
		lblFile.setFont(new Font("����", Font.PLAIN, 12));
		lblFile.setBounds(12, 190, 69, 15);
		panel.add(lblFile);
		
		txtFilePath = new JTextField();
		txtFilePath.setFont(new Font("����", Font.PLAIN, 12));
		txtFilePath.setColumns(10);
		txtFilePath.setBounds(76, 187, 425, 21);
		panel.add(txtFilePath);
		
		JButton btnFileSelect = new JButton("����(Select)");
		btnFileSelect.setFont(new Font("����", Font.PLAIN, 12));
		btnFileSelect.addActionListener( fileSelect() );
		btnFileSelect.setBounds(513, 186, 114, 23);
		panel.add(btnFileSelect);
		
		lblFileName = new JLabel("");
		lblFileName.setBounds(0, 0, 57, 15);
		lblFileName.setVisible(false);
		panel.add(lblFileName);
		
		lblFileSize = new JLabel("");
		lblFileSize.setBounds(0, 0, 57, 19);
		lblFileSize.setVisible(false);
		panel.add(lblFileSize);
		
		txtCount = new JTextField();
		txtCount.addKeyListener(function.checkKeyAdapter());
		txtCount.setFont(new Font("����", Font.PLAIN, 12));
		txtCount.setColumns(10);
		txtCount.setBounds(413, 158, 214, 21);
		panel.add(txtCount);
		
		JLabel lblCategory = new JLabel("�з�(Category)");
		lblCategory.setFont(new Font("����", Font.PLAIN, 12));
		lblCategory.setBounds(175, 20, 226, 15);
		panel.add(lblCategory);
		
		cmbCategory = new JComboBox<String>();
		cmbCategory.setFont(new Font("����", Font.PLAIN, 12));
		cmbCategory.setBounds(413, 17, 214, 21);
		panel.add(cmbCategory);
		
		lblID = new JLabel("");
		lblID.setBounds(12, 0, 57, 15);
		lblID.setVisible(false);
		panel.add(lblID);
		
		JButton btnOK = new JButton("Ȯ��(OK)");
		btnOK.setFont(new Font("����", Font.PLAIN, 12));
		btnOK.addActionListener( btnOK() );
		btnOK.setBounds(411, 298, 114, 23);
		contentPane.add(btnOK);
		
		JButton btnCancel = new JButton("���(Cancel)");
		btnCancel.setFont(new Font("����", Font.PLAIN, 12));
		btnCancel.addActionListener( btnCancel() );
		btnCancel.setBounds(538, 298, 114, 23);
		contentPane.add(btnCancel);
		
		// �޺� �ڽ�
		initSelectCategory();
		
		// ȭ�� ��������
		Logistics_store_view store_view = initSelect(id, memberID);
		
		// ����Ұ� ���� �ƴ� ��
		if ( store_view != null ){
			Logistics_store logistics_store = store_view.getLogistics_store();
			Logistics_category logistics_category = store_view.getLogistics_category();
			
			// ������ �ҷ�����
			lblID.setText(String.valueOf( logistics_store.getId() ) );
			cmbCategory.setSelectedItem( logistics_category.getCategory() );
			txtSubject.setText( logistics_store.getSubject() );
			txtBarcode.setText( logistics_store.getBarcode() );
			txtAuthor.setText( logistics_store.getAuthor() );
			txtPrice.setText( String.valueOf( logistics_store.getPrice() ) );
			txtCount.setText( String.valueOf( logistics_store.getCnt() ) );
		}
		else{

			JOptionPane.showMessageDialog(null, "������ ���� ������Դϴ�.\n(Unauthorized user.)", "�˸�(Alert)",
					JOptionPane.INFORMATION_MESSAGE);
			

			dispose();
			MgtFrm mgtFrm = new MgtFrm();
			mgtFrm.setVisible(true);
		}
	}
	
	private ActionListener fileSelect(){
		
		JFileChooser chooser = new JFileChooser();
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FileController fileController = new FileController();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG, Gif Images",
						"jpg", "gif");
				
				chooser.setFileFilter(filter);
				
				int ret = chooser.showOpenDialog(null);
				
				if ( ret != JFileChooser.APPROVE_OPTION){
					
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�.\n(No file selected.)", "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				
				String filePath = chooser.getSelectedFile().getPath();
				String fileName = chooser.getSelectedFile().getName();
				long fileSize = fileController.getFileSize(filePath);
				
				lblFileName.setText(fileName);
				lblFileSize.setText(String.valueOf(fileSize));
				txtFilePath.setText(filePath);

				
				JOptionPane.showMessageDialog(null, fileSize, "�˸�(Alert)",
						JOptionPane.INFORMATION_MESSAGE);
				
				//lblFilePath = filePath;
				
				ImageIcon imgIcon = new ImageIcon(filePath);
				
				usrImage.setIcon(imgIcon);
				
			}
		};
		
		return actionListener;
	}
	
	private ActionListener btnOK(){
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean state = true;
				boolean fileChk = false;
				MgtFrm mgtFrm = null;
				int categoryID = -1;
				
				// �з�
				if ( cmbCategory.getSelectedItem().toString().isEmpty() ){
					
					JOptionPane.showMessageDialog(null, "�з��� �����ϼ���.\n(Please select a category.)", "�˸�(Alert)",
							JOptionPane.INFORMATION_MESSAGE);

					state = false;
				}
				else{
					String categoryTxt = cmbCategory.getSelectedItem().toString();
					categoryID = selectCategoryQuery(categoryTxt);
				}
				
				// �׸��
				if ( txtSubject.getText().isEmpty() && state == true ){
					
					JOptionPane.showMessageDialog(null, "�׸���� �Է��ϼ���.\n(Please enter the item name.)", "�˸�(Alert)",
														JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}

				// ����
				if ( txtAuthor.getText().isEmpty() && state == true ){
					
					JOptionPane.showMessageDialog(null, "������ �� ���ڸ� �Է��ϼ���.\n(Please enter author.)", "�˸�(Alert)",
														JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}

				// ����
				if ( txtPrice.getText().isEmpty() && state == true ){
					
					JOptionPane.showMessageDialog(null, "������ �Է��ϼ���.\n(Please enter price.)", "�˸�(Alert)",
														JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}
				
				// ����
				if ( txtCount.getText().isEmpty() && state == true ){
					
					JOptionPane.showMessageDialog(null, "������ �Է��ϼ���.\n(Please enter quantity.)", "�˸�(Alert)",
														JOptionPane.INFORMATION_MESSAGE);
					
					state = false;
				}

				// ���ϼ��ÿ���
				if ( txtFilePath.getText().isEmpty() && state == true ){}
				else{
					fileChk = true;
				}
				
				// �����ϱ�
				if ( state ){
					update(categoryID, fileChk);

					dispose();
					mgtFrm = new MgtFrm();
					mgtFrm.setVisible(true);
				}
				
			}
		};
		
		return actionListener;
	}
	
	private ActionListener btnCancel(){
		
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MgtFrm mgtFrm = new MgtFrm();
				dispose();
				mgtFrm.setVisible(true);
			}
		};
		
		return actionListener;
	}
	

	private void initSelectCategory(){
		
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		
		// SQL ��(Statement SQL)
		query = sb.append("select id, category from logistics_category order by id asc").toString();
				
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "�˸�(Alert)",
					JOptionPane.INFORMATION_MESSAGE);

		}
		
		// ������ �ҷ�����
		try {
			
			while(rs.next()){
				cmbCategory.addItem(rs.getString("category"));
			}
					
			stmt.close();
			rs.close();
			conn.close();
			
			dbController.setResultSet(null);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} // end of while
		
	}
	
	private void update(int categoryID, boolean FileChk){
		
		String query = "";
		String filePath = "";
		
		File f = null;
		FileInputStream fis = null;
		
		Logistics_store logistics_store = new Logistics_store();
		
		query = "update logistics_store set categoryID = ?, subject = ?,"
				+ " barcode = ?, author = ?, price = ?, cnt = ?, "
					+ "filename = ?, filesize = ?, image = ?, ip = ?"
					+ " where id = ?";

		MariaConnector mariaConnector = new MariaConnector();
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		// ������ �������� ��
		if ( FileChk == true ){ 
		
			filePath = txtFilePath.getText();
			f = new File(filePath);
			
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		} // end of if
		
		// ��¥
		java.util.Date date=new java.util.Date();
		
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		java.sql.Timestamp sqlTime=new java.sql.Timestamp(date.getTime());
		
		try {
			logistics_store.setId( Integer.valueOf( lblID.getText() ));
			logistics_store.setCategoryID(categoryID);
			logistics_store.setSubject(txtSubject.getText());
			logistics_store.setBarcode(txtBarcode.getText());
			logistics_store.setAuthor(txtAuthor.getText());
			logistics_store.setPrice( Integer.valueOf( txtPrice.getText() ));
			logistics_store.setCnt( Integer.valueOf( txtCount.getText() ));

			if ( FileChk == true ){
				logistics_store.setFilename(lblFileName.getText());
				logistics_store.setFilesize( Integer.valueOf(lblFileSize.getText() ));
				logistics_store.setImage(fis);
				logistics_store.setFile(f);	
			}
			else{
				logistics_store.setFilename( null );
				logistics_store.setFilesize( -1 );
				logistics_store.setImage( null );
				logistics_store.setFile( null );
			}
			
			logistics_store.setIp(local.getHostAddress());
			logistics_store.setRegidate(sqlTime);
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, logistics_store.getCategoryID());
			pstmt.setString(2, logistics_store.getSubject());
			pstmt.setString(3, logistics_store.getBarcode());
			pstmt.setString(4, logistics_store.getAuthor());
			pstmt.setInt(5, Integer.valueOf( logistics_store.getPrice() ));
			pstmt.setInt(6, Integer.valueOf( logistics_store.getCnt() ));
			pstmt.setString(7, logistics_store.getFilename());
			pstmt.setInt(8, logistics_store.getFilesize());
			
			
			if ( FileChk == true )
			{
				pstmt.setBinaryStream(9, logistics_store.getImage(), 
									 (int)logistics_store.getFile().length() );	
			}
			else{
				pstmt.setBinaryStream(9, null);
			}
			
			pstmt.setString(10, logistics_store.getIp() );
			pstmt.setInt(11,  logistics_store.getId() );
			
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

	private int selectCategoryQuery(String keyword){
		
		int id = -1;
		StringBuilder sb = new StringBuilder();
		MariaConnector mariaConnector = new MariaConnector();
		
		String query = "";
		
		// SQL ��(Statement SQL)
		query = sb.append("select id, category from logistics_category")
				  .append(" where")
				  .append(" category ='")
				  .append(keyword)
				  .append("' order by id asc").toString();
				
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();

		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "�˸�(Alert)",
					JOptionPane.INFORMATION_MESSAGE);

		} // end of try to catch
		
		// ������ �ҷ�����
		try {
			
			while(rs.next()){
				id = rs.getInt("id");
			} // end of while
					
			stmt.close();
			rs.close();
			conn.close();
			
			dbController.setResultSet(null);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} // end of try to catch
		
		return id;
		
	}
	
	private Logistics_store_view initSelect(int id, int memberID){
		
		StringBuilder sb = new StringBuilder();
		String query = "";
		
		MariaConnector mariaConnector = new MariaConnector();
		
		Logistics_store logistics_store = new Logistics_store();
		Logistics_category logistics_category = new Logistics_category();
		Logistics_store_view store_view = null;
		
		byte[] img = null;
		
		// SQL ��(Statement SQL)
		query = sb.append("select id, category, memberID, subject, barcode, author, price, cnt, filename, filesize, image, ip, regidate ")
				.append( "from logistics_store_view where id = '")
				.append(id)
				.append("' and ")
				.append("memberID = '")
				.append(memberID)
				.append("'")
				.append(" order by id asc").toString();
		
		// System.out.println(query);
		
		ResultSet rs = null;
		dbController.setConn(mariaConnector.getConn());
		Connection conn = dbController.getConn();
		
		try {
			this.stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "�˸�(Alert)",
					JOptionPane.INFORMATION_MESSAGE);

		} // end of try to catch
		
		// ������ �ҷ�����
		try {
			
			// rs ���� ����
			if( rs != null && rs.isBeforeFirst() ){
				store_view = new Logistics_store_view();
			}
			
			while(rs.next()){
				
				logistics_store.setId(rs.getInt("id"));
				logistics_category.setCategory(rs.getString("category"));
				logistics_store.setMemberID(rs.getInt("memberID"));
				logistics_store.setSubject(rs.getString("subject"));
				logistics_store.setBarcode(rs.getString("barcode"));
				logistics_store.setAuthor(rs.getString("author"));
				logistics_store.setPrice(Integer.valueOf(rs.getString("price")));
				logistics_store.setCnt(Integer.valueOf(rs.getString("cnt")));
				logistics_store.setIp(rs.getString("ip"));
				logistics_store.setRegidate(rs.getTimestamp("regidate"));
				
				// ������ ������ ��
				if ( rs.getInt("filesize") != -1 ){
					logistics_store.setFilename(rs.getString("filename"));
					logistics_store.setFilesize(rs.getInt("filesize"));
					img = rs.getBytes("image");
				} // end of if
				
			} // end of while
			
			// �׸� ��������
			if ( img != null ){
				ImageIcon image = new ImageIcon(img);
				Image im = image.getImage();
				Image myImg = im.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				
				usrImage.setIcon(new ImageIcon(myImg));
			}
			
			if ( store_view != null ){
				store_view.setLogistics_store(logistics_store);
				store_view.setLogistics_category(logistics_category);
			}
			
			stmt.close();
			rs.close();
			conn.close();

			dbController.setResultSet(null);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} // end of try to catch
		
		return store_view;
		
	}
}
