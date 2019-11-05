package lab4;
import java.io.FileInputStream;
import java.util.Properties;
//配置类
public class Config {
	private static Properties p = null;
	static {
		try {
			p = new Properties();
			// 加载配置文件
			p.load(new FileInputStream("Config/mysql.properties"));
			System.out.println("配置已完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 获取键对应的值
	public static String getValue(String key) {
		System.out.println("键值对应获取正确");
		return p.get(key).toString();
	
	}
}
