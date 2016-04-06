package mancala;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

//TODO Fix Logic
public class ComputerAI implements Callable<Integer> {

	List<Cup> cups;
	Map<Cup, Integer> cupAmounts;

	public ComputerAI(Cup[] cupsArray) {

		ArrayList<Cup> cupsList = new ArrayList<Cup>();
		for (int i = 0; i < cupsArray.length; i++) {
			cupsList.add(cupsArray[i]);
		}
		cups = cupsList.subList(cupsList.indexOf(cupsList.get(7)), cupsList.indexOf(cupsList.get(13)));
		// Collections.reverse(cups);
		cupAmounts = new HashMap<Cup, Integer>();
	}

	public Integer call() {
		try {
			// sleep for 5 seconds
			Thread.sleep(5 * 1000);
		} catch (final InterruptedException ex) {
			ex.printStackTrace();
		}
		
		List<Integer> counts = new ArrayList<Integer>();
		int move = 0;

		for (int i = 0; i < cups.size(); i++) {
			cupAmounts.put(cups.get(i), cups.get(i).getCount());
			if (cups.get(i).getCount() == cups.indexOf(cups.get(i)) + 1) {
				return switchMove(i);

			} else if (cups.get(i).getCount() > i) {
				counts.add(cups.get(i).getCount());

				for (Map.Entry entry : cupAmounts.entrySet()) {
					if (counts.get(0).equals(entry.getValue())) {
						move = cups.indexOf((Cup) entry.getKey());
					}
				}
			}
		}

		System.out.println(counts + "b4");
		Collections.sort(counts);
		System.out.println(counts + "after");
		return switchMove(move);
	}

	private int switchMove(int move) {

		switch (move) {
		case 0:
			move = 12;
			break;
		case 1:
			move = 11;
			break;
		case 2:
			move = 10;
			break;
		case 3:
			move = 9;
			break;
		case 4:
			move = 8;
			break;
		case 5:
			move = 7;
			break;
		}
		return move;
	}
}