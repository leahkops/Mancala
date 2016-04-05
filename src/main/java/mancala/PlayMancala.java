package mancala;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class PlayMancala {
	public static void main(String[] args) {

		Injector injector = Guice.createInjector(new MancalaModule());
		NameFrame f = injector.getInstance(NameFrame.class);
		f.setVisible(true);

	 }

}
