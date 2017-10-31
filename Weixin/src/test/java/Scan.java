import java.io.File;

import net.sf.json.JSONArray;

public class Scan {
	public static void main(String[] args) {
		File file = new File(System.getProperty("user.dir") + "/src/main/java");
		System.out.println(JSONArray.fromObject(file.list()));
		
	}
}
