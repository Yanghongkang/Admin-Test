package annt;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;

@Person
public class classroom {
	public static void main(String[] args) throws ClassNotFoundException {
		Class cls = Class.forName("annt.yu");
		for (Annotation string : cls.getAnnotations()) {
			System.out.println(string);
		}
		for (AnnotatedType string :cls.getAnnotatedInterfaces()) {
			System.out.println(string);
		}

	}	
}
