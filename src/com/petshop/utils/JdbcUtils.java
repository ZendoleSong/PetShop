package com.petshop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

	private static String driver = "";
	private static String url = "";
	private static String username = "";
	private static String password = "";
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static String path="jdbc.properties";
	
	public JdbcUtils() {
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public JdbcUtils(String path) throws IOException {
		 this.path=path;     
	}
	
	public static void init() throws IOException
	{
		//�ļ�·��--���·��
		InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream(path);
		//ʵ����Properties��
		Properties prop = new Properties();
		//��ȡ
		prop.load(is);
    //�������ļ���ȡ����
	driver = prop.getProperty("driver");
    url = prop.getProperty("url");
    username = prop.getProperty("username");
    password = prop.getProperty("password");
	}
	
	/**
	 * ��ȡ���Ӷ���
	 * 
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public  static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		//��ʼ�����ݿ����Ӳ���
		init();
		//01.������ƣ���������
		Class.forName(driver);
		//02.��������
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	/**
	 * �ͷ���Դ
	 * @throws SQLException 
	 */
	public static void freeAll() throws SQLException {
		if(rs != null){
			rs.close();
		}
		if(pstmt != null){
			pstmt.close();
		}
		if(conn != null){
			conn.close();
		}
		}
	
	/**
	 * ��ѯ����
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static ResultSet excuteQuery(String sql ,Object[] params) throws ClassNotFoundException, SQLException, IOException{
		conn=getConnection();
		pstmt = conn.prepareStatement(sql);
		//���ò���
		for(int i=0;i<params.length;i++){
			pstmt.setObject(i+1, params[i]);
		}
		rs = pstmt.executeQuery();
		return rs;
		//rs��û�н������˴����ܹر���
	}
	/**
	 * ִ���޸ĵķ���
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static int excuteUpdate(String sql ,Object[] params) throws ClassNotFoundException, SQLException, IOException{
		conn=getConnection();
		int row = 0;
		pstmt = conn.prepareStatement(sql);
		//���ò���
		for(int i=0;i<params.length;i++){
			pstmt.setObject(i+1, params[i]);
		}
		row = pstmt.executeUpdate();
			//ִ�йر����Ĳ���
			freeAll();
		return row;
	}	
	}

