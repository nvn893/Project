package gnrl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class genProp {

	public String getval(String key) throws IOException
	{ 
		FileReader read=new FileReader("config.properties");
		Properties p1=new Properties();
		p1.load(read);
		String val=p1.getProperty(key);
	
		return val;
	}
	
}
