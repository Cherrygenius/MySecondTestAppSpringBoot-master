package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ushkalov.MySecondTestAppSpringBoot.model.Request;

import java.time.Instant;

@Service
public class ModifyTimeOfReceiptRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request) {
        request.setTimeOfReceipt(Instant.now());

        HttpEntity<Request> httpEntity = new HttpEntity<>(request);

        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}

