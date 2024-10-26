package ru.ushkalov.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ushkalov.MySecondTestAppSpringBoot.model.Responce;

import java.util.UUID;

@Service
@Qualifier("ModifyOperationUidResponceService")
public class ModifyOperationUidResponceService implements ModifyResponceService {
    @Override
    public Responce modify(Responce responce) {
        UUID uuid = UUID.randomUUID();
        responce.setOperationUid(uuid.toString());
        return responce;
    }
}
