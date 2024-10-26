package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.ushkalov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;

@Service
public class RequestUnsupportedCodeService implements UnsupportedCodeService {
    @Override
    public void isSupported(int uid) throws UnsupportedCodeException {
        if (uid == 123) {
            throw new UnsupportedCodeException("Unsupported UID: " + uid);
        }
    }
}
