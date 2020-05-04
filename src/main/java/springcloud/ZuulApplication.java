package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springcloud.filter.DemoFilter;
import springcloud.filter.ErrorFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public DemoFilter myZuulPreFilter() {
		return new DemoFilter();
	}

	@Bean
	public ErrorFilter myZuulErrorFilter() {
		return new ErrorFilter();
	}
}
