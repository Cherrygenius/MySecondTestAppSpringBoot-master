package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.ushkalov.MySecondTestAppSpringBoot.model.Responce;

@Service
public interface ModifyResponceService {
    Responce modify(Responce responce);
}
