package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class login {
	public static void main(String[] args) {
		//查询数据库完成登录验证
		//1.用户输入用户名密码 分别为li 666
		//2.到数据库中查询有没有一条记录name 为li password为666
		//3.如果有登录成功，否则登录失败
		System.out.println("请输入用户名和密码");
//		
		Scanner sc = new Scanner(System.in);
//		
		String name = sc.nextLine();
		String password = sc.nextLine();
//		
//		if(name.equals("li") && password.equals("666")) {
//			System.out.println("登录成功");
//		}else {
//			System.out.println("登录失败");
//		}
		//用户很多
		//用户信息存储在数据库中
		//1.注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");//找驱动包中的类，driver：注册到了驱动管理器里面，并执行类中的一些代码
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("缺少驱动包或者ClassName拼接错误");
		}
		//2.获取链接    连接数据库
		String url = "jdbc:mysql://localhost:3306/handsomeboy?useSSL=false";//?useSSL=false，消去ssl服务器身份验证，等号两边不能有空格
		String user = "root";
		String passwd = "htyqq0896airiu";
		Connection con = null;
		try {//获取链接
			con = DriverManager.getConnection(url, user, passwd);//DriverManager在选择合适的driver   驱动管理器调用一个方法
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//3.编写语句
		String sql = "select uname,upassword from testone where uname=? and upassword=?";
		
		//4.创建语句对象
		PreparedStatement sta = null;
		try {
			sta = con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//5.参数赋值
		//？：占位符
		try {
			sta.setString(1, name);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sta.setString(2, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//6.执行语句
		ResultSet res =null;
		try {
			res = sta.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//7.处理结果
		 {
			try {
//				while(res.next()) {
//					System.out.println(res.getString("uname")+"\t"+res.getString("upassword"));
//				}
			if(res.next()) {
				System.out.println("登录成功");
			}else {
				System.out.println("登录失败");
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }

		//8.释放资源
		try {
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sta.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
	}

}
