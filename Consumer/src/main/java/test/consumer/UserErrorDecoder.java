package test.consumer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Primary;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import test.scin.exception.TestException;

public class UserErrorDecoder implements ErrorDecoder {

	public Exception decode(String methodKey, Response response) {
		System.out.println("methodKey:" + methodKey);
		Exception exception = null;
		try {
			String ex = Util.toString(response.body().asReader());
			System.out.println("ex:" + ex);
			ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decodeBase64(ex));
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Exception) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return exception != null ? exception : new TestException("系统运行异常");
	}
}