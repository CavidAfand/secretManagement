package az.edu.aztu.secretmanagement.service;

import az.edu.aztu.secretmanagement.dto.payment.RequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private String reqToken;

    private RequestService requestService;
    private VaultTemplate vaultTemplate;

    public PaymentService(RequestService requestService,
                          VaultTemplate vaultTemplate) {
        this.requestService = requestService;

        VaultResponse response = vaultTemplate
                .opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("request");

        this.reqToken = (String) response.getData().get("token");
    }

    @Value("${payment.url}")
    private String url;

    public boolean pay(BigDecimal amount) {
        RequestDto requestDto = new RequestDto(reqToken, amount);
        return requestService.send(requestDto).isResult();
    }

}
