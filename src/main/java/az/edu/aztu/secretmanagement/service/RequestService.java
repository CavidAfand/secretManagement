package az.edu.aztu.secretmanagement.service;

import az.edu.aztu.secretmanagement.dto.payment.RequestDto;
import az.edu.aztu.secretmanagement.dto.payment.ResponseDto;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    public ResponseDto send(RequestDto requestDto) {
        return new ResponseDto(true);
    }

}
