import java.net.URL;

public class classloader {
	public static void main(String[] args) {
		// URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		// for (int i = 0; i < urls.length; i++) {
		// System.out.println(urls[i].toExternalForm());
		// }
		for (String string : System.getProperty("sun.boot.class.path").split(";")) {
			System.out.println(string);
		}

	}
}
