package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import lab4.DBUtil;

public class DBDemo {
	public static void main(String args[]) throws ClassNotFoundException {
		// 创建DBUtil对象
		String insertSql = "insert into Product (Product_Code, Description, Price) values(?,?,?)";
		DBUtil db = new DBUtil();
		Connection conn = null;
		try {
			System.out.println("3");
			conn = db.getConnection();
			// 获取事务自动提交状态
			boolean autoCommit = conn.getAutoCommit();
			System.out.println("4");
			System.out.println("事务自动提交状态：" + autoCommit);
			if (autoCommit) {
				// 关闭自动提交,开启事务
				conn.setAutoCommit(false);
			}
			System.out.println("5");
			Statement stmt = conn.createStatement();
			// 多条DML批处理语句
			
			//stmt.executeUpdate("insert into Product(Product_Code, Description, Price) values('ip','2018',2599)");
			//	stmt.executeUpdate("insert into Product(Product_Code, Description, Price) values('Toaster','Media',780)");
			//stmt.executeUpdate("insert into Product(Product_Code, Description, Price) values('Dryer','Flyco',180)");
			System.out.println("增加成功");

			stmt.execute("update Product set Price=Price - 10  where Product_Code = 'Toaster'");
			stmt.execute("update Product set Price=Price + 10  where Product_Code = 'Dryer'");
			System.out.println("修改成功！");
			// 如果顺利执行则在此提交
			conn.commit();
			// 恢复原有事务提交状态
			conn.setAutoCommit(autoCommit);
			// 关闭连接
			db.closeAll();
		} catch (Exception e) {
			// 出现异常
			if (conn != null) {
				try {
					// 回滚
					System.out.println("事务回滚！");
					conn.rollback();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			e.printStackTrace();
		}
}
}