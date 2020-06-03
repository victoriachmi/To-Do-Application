package testing;

import org.springframework.stereotype.Component;


@Component("HelloWrold")
public class Hello {

	
	public Hello() {
		System.out.println("hello class loaded");
	}
}
