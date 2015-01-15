package cn.wzga.open.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * Description:读取配置文件
 * </p>
 * 
 * @author sutong,chenjingchai
 * @version 1.0 2014-07-11
 */
public class Configuration {
	private Properties prop = new Properties();//记录配置项
	private static Configuration conf = null;
	public static final String syslog = "/syslog.properties";

	//此构造方法用于新建配置文件
	public Configuration() {
	}

	//从指定文件名读入配置信息
	public Configuration(String fileName) throws Exception {
		try {
			InputStream fis = this.getClass().getResourceAsStream(fileName);

			prop.load(fis); //载入文件

			fis.close();
		} catch (IOException ex) {
			throw new Exception("not found properties file: " + fileName);

		}
	}

	public synchronized static Configuration getInstance() {
		try {
			if (conf == null) {
				conf = new Configuration(syslog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conf;
	}

	public Properties getPropertiesFromSystemPath(String pptFile) throws Exception {

		InputStream is = ClassLoader.getSystemResourceAsStream(pptFile);

		Properties props = new Properties();
		props.load(is);

		return props;
	}

	//指定配置项名称，返回配置值
	public String getValue(String itemName) {
		return prop.getProperty(itemName);
	}

	//指定配置项名称和默认值，返回配置值
	public String getValue(String itemName, String defaultValue) {
		return prop.getProperty(itemName, defaultValue);
	}

	//添加配置项名称及其值
	public void addValue(String itemName, String value) {
		prop.put(itemName, value);
		return;
	}

	//设置配置项名称及其值
	public void setValue(String itemName, String value) {
		prop.setProperty(itemName, value);
		return;
	}

	//保存配置文件，指定文件名和抬头描述
	public void saveFile(String fileName, String description) throws Exception {
		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			prop.store(fout, description);//保存文件
			fout.close();
		} catch (IOException ex) {
			throw new Exception("error to save properties file: " + fileName);
		}
	}

	//保存配置文件，指定文件名
	public void saveFile(String fileName) {
		try {
			saveFile(fileName, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration config = Configuration.getInstance();
		System.out.println(config.getValue("/catalog/biblio/add.htm"));
		//config.setValue("application.path", "jssss");
		//config.saveFile("/Users/machengy/Documents/Workspaces/hisa/WebRoot/WEB-INF/classes/" + syslog);
		//System.out.println(config.getValue("application.path"));
	}
}
