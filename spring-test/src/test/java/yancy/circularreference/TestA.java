package yancy.circularreference;

public class TestA {
	private TestB testB;

	public TestA(TestB testB) {
		this.testB = testB;
	}

	public TestB getTestB() {
		return testB;
	}

	public void setTestB(TestB testB) {
		this.testB = testB;
	}
}
