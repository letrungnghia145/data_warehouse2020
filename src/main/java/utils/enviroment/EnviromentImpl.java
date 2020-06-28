package utils.enviroment;

import java.util.Properties;

public class EnviromentImpl implements Enviroment {

	public String getStringProp(String propKey) {
		String propValue = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			Properties properties = new Properties();
			properties.load(classLoader.getResourceAsStream("config.properties"));
			propValue = (String) properties.get(propKey);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return propValue;
	}
}
