package org.springframework.monopoly.util;

public class Trio<A, B, C> {
	
	private final A first;
	private final B second;
	private final C third;
	
	public Trio(A first, B second, C third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public static <A, B, C> Trio<A, B, C> of(A first, B second, C third) {
		return new Trio<A, B, C>(first, second, third);
	}

	@Override
	public String toString() {
		return String.format("(%s,%s,%s)",first,second,third);
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public C getThird() {
		return third;
	}
		
}
