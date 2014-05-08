package s12.ex1.ResoucreBundle;

import java.io.IOException;
import java.util.ListResourceBundle;


public class WelcomeBundle_ge_GE extends ListResourceBundle {
	static final Object[][] contents = {
			{ "Welcome to our website", "Bienvence a' notre site Web" },
			{ "Good Morning", "Bonjour" } };

	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return contents;
	}

}
