package ru.jakesmokie.cities.services;

import lombok.val;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class RestTemplateService implements IRestTemplateService {
    private final RestTemplate restTemplate;

    public RestTemplateService(Environment env) {
        val builder = HttpClients.custom();

        if (Arrays.stream(env.getActiveProfiles()).allMatch(x -> x.equals("dev"))) {
            builder.setSSLHostnameVerifier(new NoopHostnameVerifier());
        }

        val httpClient = builder.build();

        val requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        restTemplate = new RestTemplate(requestFactory);
    }

    @Override
    public RestTemplate get() {
        return restTemplate;
    }
}
