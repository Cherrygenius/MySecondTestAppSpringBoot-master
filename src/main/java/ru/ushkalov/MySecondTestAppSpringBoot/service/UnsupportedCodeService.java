package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.ushkalov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.ushkalov.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface UnsupportedCodeService {
    void isSupported(int uid) throws UnsupportedCodeException;
}
