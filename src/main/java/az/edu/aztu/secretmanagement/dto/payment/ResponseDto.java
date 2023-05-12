package az.edu.aztu.secretmanagement.dto.payment;

import lombok.Data;

@Data
public class ResponseDto {

    private boolean result;

    public ResponseDto(boolean result) {
        this.result = result;
    }
}
