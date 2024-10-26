package ru.ushkalov.MySecondTestAppSpringBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @NotBlank(message = "UID не может быть пустым")
    private String uid;
    @NotBlank(message = "operationUid не может быть пустым")
    private String operationUid;
    private String systemName;
    private String systemTime;
    private String source;
    private Positions position;
    private double salary;
    private double bonus;
    private int workDays;
    @Max(value = 100000,message = "communicationId не может быть больше 100000")
    @Min(value = 1, message = "communicationId не может быть меньше 1")
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
    private Instant timeOfReceipt;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("uid='").append(uid).append('\'');
        sb.append(", operationUid='").append(operationUid).append('\'');
        sb.append(", systemName='").append(systemName).append('\'');
        sb.append(", systemTime='").append(systemTime).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", communicationId=").append(communicationId);
        sb.append(", templateId=").append(templateId);
        sb.append(", productCode=").append(productCode);
        sb.append(", smsCode=").append(smsCode);
        sb.append(", timeOfReceipt=").append(timeOfReceipt);
        sb.append('}');
        return sb.toString();
    }
}
