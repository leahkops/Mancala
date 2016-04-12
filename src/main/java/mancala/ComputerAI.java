package mancala;

import java.util.ArrayList;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class ComputerAI extends Thread {

	public interface ComputerMoveListener {
		public void onMove(int cupNumber);
	}

	private Cup[] cups;
	private PieceAnimation animation;
	private Board board;
	private boolean goAgain;

	public ComputerAI(PieceAnimation animator, Board board) {
		this.cups = board.getCups();
		this.goAgain = false;
		this.animation = animator;
		this.board = board;
	}

	/**
	 * First Choice: Land in goal- If the index of the cup plus the amount of
	 * marbles is equal to the goal, then the marbles will land in the goal.
	 * Second Choice 2: Land in an empty slot on th oppenents side so that the
	 * player obtains the marbles in the cup opposite. Choice 3: If cannot land
	 * in an empty cup, choose randomly
	 **/

	@Override
	public void run() {
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Integer bestCup = null;
		Random rand = new Random();
		// find cup that goes into goal
		for (int i = 7; i <13; i++) {
			if (i + cups[i].getCount() == Board.GOAL2) {
				bestCup = i;
				break;
			}
		}

		// Choose the spot that will land in an empty spot-
		int counter = 0;
		for (int i = 0; i < 6; i++) {
			if (cups[i].getCount() == 0) {
				for (int j = 12; j > 7; j--) {
					if (cups[j].getCount() == ((j + i) - counter) && cups[j].getCount() != 0) {
						bestCup = j;
						System.out.println("went into logic");
					}
					counter += 2;
				}
				break;
			}
		}
		// Otherwise choose a random c up
		while (bestCup == null) {
			bestCup = rand.nextInt(6) + 7;
			if (cups[bestCup].getCount() == 0) {
				bestCup = null;
			}
		}
		System.out.println(bestCup);
		goAgain = animation.distibute(bestCup, this.board);
	}

	public boolean goAgain() {
		return goAgain;
	}
}
