package sg.edu.nus.iss.WS13Redo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.iss.WS13Redo.model.Contact;

@SpringBootTest
class Ws13RedoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testContact() throws Exception {
		Contact c = new Contact();
		c.setName("Kenneth");
		c.setEmail("a@a.com");
		c.setPhone(1234567);
		// assert equals to the setter value
        	assertEquals(c.getEmail(), "a@a.com");
	}

}
