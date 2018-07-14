package edu.ccu.se.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLHelper {

	/*
	 * driver=com.mysql.jdbc.Driver url=jdbc:mysql:///liuyanban user=root
	 * password=admin
	 */
	// ���弸����Ҫ�ı���
	private static Connection ct = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	// �������ݿ����ӵĲ��� url,user,password, driver
	// ��ʵ�ʿ����У���������Щ��Ϣд��һ�������ļ���
	// ����������ʱ�򣬶�ȡ�����ļ�������Щ��Ϣ java.util.Properties
	private static String driver = null;
	private static String user = "root";
	private static String password = "admin";
	private static String url = "jdbc:mysql:///liuyanban";

	private static Properties pp = null;
	private static FileInputStream fis = null;
	// ��̬���������,ֻ�����һ��
	static {

		try {
			// ��dbinfo.properties��ȡ������Ϣ
			// pp = new Properties();
			// fis = new FileInputStream("dbinfo.properties");
			// // ���ļ�����
			// pp.load(fis);
			// driver = pp.getProperty("driver");
			// url = pp.getProperty("url");
			// user = pp.getProperty("user");
			// password = pp.getProperty("password");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �õ�����
	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}

	// дһ��select�ķ���
	// sql="select * from emp where ename=?"
	// parameters={"SMITH"};
	public static ResultSet executeQuery(String sql, String[] parameters) {
		// ResultSet rs=null;
		try {
			// 1.�õ�����
			ct = DriverManager.getConnection(url, user, password);
			// 2.����sql����
			ps = ct.prepareStatement(sql);
			// 3.������ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			// 4.ִ��sql
			rs = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// �׳������쳣����������һ��ѡ��
			// ���Բ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());
		} finally {
			// close(rs,ps,ct);
		}
		return rs;
	}

	// д���Insert/update/delete�ķ�������Ҫ��������
	// sql1="update emp set sal=sal-10 where ename=?"
	// sql2="update emp set sal=sal+10 where ename=?"
	// sql1_paras={"SMITH"}
	// sql2_paras={"ALLEN"};
	public static void executeUpdate(String[] sqls, String[][] parameters)
			throws SQLException {
		// 1.�õ�����
		// 2.���ܴ�����sql����Ҫ��������
		// ���������Զ��ύ
		// 3.ѭ������sql����
		// 4.���ÿ��sql�����?��ֵ
		// 5.ִ��ÿ��sql
		// 6.�Ѷ��sql����һ������ͳһ�ύ
		// ע�⣬��������쳣���ع�����ȡ��ȫ��dml����)

		// 1.�õ�����
		try {
			ct = DriverManager.getConnection(url, user, password);
			ct.setAutoCommit(false);
			// 2.����sql����
			for (int i = 0; i < sqls.length; i++) {
				ps = ct.prepareStatement(sqls[i]);
				for (int j = 0; j < parameters[i].length; j++) {
					ps.setString(j + 1, parameters[i][j]);
					ps.executeUpdate();
				}
			}
			ct.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ct.rollback();
		} finally {
			// close(rs,ps,ct);
		}

	}

	// ��дһ��insert/update/delete�ķ���
	// sql="insert into emp(empno,ename) values(9999,'LiHeng')";
	// ����sql="insert into emp(empno,ename) values(?,?)"
	// parameters={"9999","LiHeng"}
	public static void executeUpdate(String sql, String[] parameters) {
		try {
			// �õ�����
			ct = DriverManager.getConnection(url, user, password);
			// ����sql����
			ps = ct.prepareStatement(sql);
			// ��?��ֵ
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			// ִ��sql
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// �׳������쳣����������һ��ѡ��
			// ���Բ���Ҳ���Է�������
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}

	}

	// �ر���Դ�󴴽����ȹر�
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static Connection getCt() {
		return ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

}
