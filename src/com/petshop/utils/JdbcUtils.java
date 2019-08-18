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
		// TODO 自动生成的构造函数存根
	}
	
	public JdbcUtils(String path) throws IOException {
		 this.path=path;     
	}
	
	public static void init() throws IOException
	{
		//文件路径--相对路径
		InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream(path);
		//实例化Properties类
		Properties prop = new Properties();
		//读取
		prop.load(is);
    //从配置文件读取数据
	driver = prop.getProperty("driver");
    url = prop.getProperty("url");
    username = prop.getProperty("username");
    password = prop.getProperty("password");
	}
	
	/**
	 * 获取连接对象
	 * 
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public  static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		//初始化数据库连接参数
		init();
		//01.反射机制：加载驱动
		Class.forName(driver);
		//02.建立连接
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	/**
	 * 释放资源
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
	 * 查询方法
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static ResultSet excuteQuery(String sql ,Object[] params) throws ClassNotFoundException, SQLException, IOException{
		conn=getConnection();
		pstmt = conn.prepareStatement(sql);
		//设置参数
		for(int i=0;i<params.length;i++){
			pstmt.setObject(i+1, params[i]);
		}
		rs = pstmt.executeQuery();
		return rs;
		//rs还没有结束，此处不能关闭流
	}
	/**
	 * 执行修改的方法
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static int excuteUpdate(String sql ,Object[] params) throws ClassNotFoundException, SQLException, IOException{
		conn=getConnection();
		int row = 0;
		pstmt = conn.prepareStatement(sql);
		//设置参数
		for(int i=0;i<params.length;i++){
			pstmt.setObject(i+1, params[i]);
		}
		row = pstmt.executeUpdate();
			//执行关闭流的操作
			freeAll();
		return row;
	}	
	}

