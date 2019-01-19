package sc.player2019.util;

public class  Triple<A, B, C> {
	public  A first;
	public  B second;
	public  C third;

	public Triple(A first, B second, C third) {
		this.first = first;
		this.second = second;
		this.third = third;
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
	public void setFirst(A a) {
		first = a;
	}
	public void setSecond(B b) {
		second = b;
	}
	public void setThird(C c) {
		third = c;
	}

}
