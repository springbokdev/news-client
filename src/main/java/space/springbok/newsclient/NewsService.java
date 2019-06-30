package space.springbok.newsclient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {

    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "unknown")
    public String callService() {
        InstanceInfo instanceInfo = client.getNextServerFromEureka("news-service", false);
        String baseUrl = instanceInfo.getHomePageUrl();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);

        return response.getBody();
    }

    public String unknown() {
        return "unknown";
    }

}
