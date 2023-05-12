package az.edu.aztu.secretmanagement.dto;

import lombok.Data;

@Data
public class PayRequest {

    private int productId;
    private int customerId;
    private int count;

}
