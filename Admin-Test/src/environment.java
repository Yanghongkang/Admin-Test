public class environment {

	public static void main(String[] args) {
		
		/*Map<String, String> map = System.getenv();
		for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext();) {
			String key = itr.next();
			System.out.println(key + "=" + map.get(key));
		}*/
		System.out.println(System.getProperty("os.arch"));
	}

}
