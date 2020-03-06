package test.scin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "service-provider2")
public interface TestHello {
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(@RequestParam("id") String id);
}
