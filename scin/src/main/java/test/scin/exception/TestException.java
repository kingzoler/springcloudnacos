package test.scin.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

public class TestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestException(String message) {
		super(message);
	}

}
