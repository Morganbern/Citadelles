package test;

public class Test {
	public static void test(boolean passed, String message) {
		try {
			throw new AssertionError(message);
		}
		catch (AssertionError error) {
			StackTraceElement trace = error.getStackTrace()[1];
			System.out.print(trace.getFileName());
			System.out.print(":");
			System.out.print(trace.getLineNumber());
			System.out.print(": ");
			System.out.print((passed) ? "passed" : "error");
			System.out.print(": ");
			System.out.println(message);
		}
	}
}
