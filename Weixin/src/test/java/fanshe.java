import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class fanshe {
	public void sys() {
		System.out.println("aasasass");
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		Class cls = Class.forName("cn.sh.yhk.controller.ExpendController");
		Method m3 = cls.getMethod("sys");
		m3.invoke(cls.newInstance());
	}
}
