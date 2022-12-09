package org.springframework.monopoly.util;

import java.util.Random;

import org.springframework.data.util.Pair;

public class RollGenerator {
	
	private static Random random = new Random();
	
	public static Pair<Integer, Boolean> getRoll() {
		Integer roll1 = random.ints(1, 7).findFirst().getAsInt();
		Integer roll2 = random.ints(1, 7).findFirst().getAsInt();
		return Pair.of(roll1 + roll2, roll1.equals(roll2));
	}
}
