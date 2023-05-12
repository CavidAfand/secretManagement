package az.edu.aztu.secretmanagement.dto.payment;

import java.math.BigDecimal;

public class RequestDto {

    private String token;
    private BigDecimal amount;

    public RequestDto(String token, BigDecimal amount) {
        this.token = token;
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
