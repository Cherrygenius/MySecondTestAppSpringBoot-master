package ru.ushkalov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ushkalov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.ushkalov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.ushkalov.MySecondTestAppSpringBoot.model.*;
import ru.ushkalov.MySecondTestAppSpringBoot.service.*;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final UnsupportedCodeService unsupportedCodeService;
    private final ModifyResponceService modifyResponceService;
    private final ModifyRequestService modifyRequestService;
    private final ModifySourceRequestSystem modifySourceRequestSystem;
    private final ModifyTimeOfReceiptRequestService modifyTimeOfReceiptRequestService;

    @Autowired
    public MyController(ValidationService validationService, UnsupportedCodeService unsupportedCodeService,
                        @Qualifier("ModifySystemTimeResponceService") ModifyResponceService modifyResponceService,
                        @Qualifier("ModifySystemNameRequestSystem") ModifyRequestService modifyRequestService,
                        ModifySourceRequestSystem modifySourceRequestSystem,
                        ModifyTimeOfReceiptRequestService modifyTimeOfReceiptRequestService) {
        this.validationService = validationService;
        this.unsupportedCodeService = unsupportedCodeService;
        this.modifyResponceService = modifyResponceService;
        this.modifyRequestService = modifyRequestService;
        this.modifySourceRequestSystem = modifySourceRequestSystem;
        this.modifyTimeOfReceiptRequestService = modifyTimeOfReceiptRequestService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<Responce> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        modifyTimeOfReceiptRequestService.modify(request);
        log.info("request: {}", request);

        Responce responce = Responce.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Созданный ответ: {}", responce);

        try {
            validationService.isValid(bindingResult);
            unsupportedCodeService.isSupported(Integer.parseInt(request.getUid()));
        } catch (ValidationFailedException e) {
            responce.setCode(Codes.FAILED);
            responce.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            responce.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("Ответ после ошибки валидации: {}", bindingResult.getFieldError());
            return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            responce.setCode(Codes.FAILED);
            responce.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            responce.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.error("Ответ после ошибки неподдерживаемого кода: {}", responce);
            return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responce.setCode(Codes.FAILED);
            responce.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            responce.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("Ответ после неизвестной ошибки: {}", responce);
            return new ResponseEntity<>(responce, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponceService.modify(responce);
        modifyRequestService.modify(request);
        modifySourceRequestSystem.modify(request);
        log.info("Ответ после модификации: {}", responce);
        return new ResponseEntity<>(modifyResponceService.modify(responce), HttpStatus.OK);
    }
}