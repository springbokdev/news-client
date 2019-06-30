package space.springbok.newsclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@RestController
public class NewsClientApplication {

    @Autowired
    private NewsService newsService;

    public static void main(String[] args) {
        SpringApplication.run(NewsClientApplication.class, args);
    }

    @RequestMapping("/")
    public String callService() {
        return newsService.callService();
    }


}
