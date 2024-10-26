package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.ushkalov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
