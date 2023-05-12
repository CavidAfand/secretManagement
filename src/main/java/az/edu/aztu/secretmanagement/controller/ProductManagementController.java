package az.edu.aztu.secretmanagement.controller;

import az.edu.aztu.secretmanagement.dto.PayRequest;
import az.edu.aztu.secretmanagement.model.Product;
import az.edu.aztu.secretmanagement.service.PaymentService;
import az.edu.aztu.secretmanagement.service.ProductManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductManagementController {

    private ProductManagementService productManagementService;
    private PaymentService paymentService;

    public ProductManagementController(ProductManagementService productManagementService,
                                       PaymentService paymentService) {
        this.productManagementService = productManagementService;
        this.paymentService = paymentService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> productList = productManagementService.getProductList();
        return ResponseEntity.ok(productList);
    }

    @PostMapping("/pay")
    public ResponseEntity pay(@RequestBody PayRequest payRequest) {
        Product product = productManagementService.getProduct(payRequest.getProductId());
        BigDecimal amount = product.getPrice().multiply(BigDecimal.valueOf(payRequest.getCount()));
        boolean result = paymentService.pay(amount);
        if (result) {
            productManagementService.saveProductToCustomer(payRequest.getProductId(), payRequest.getCustomerId(), payRequest.getCount());
            return ResponseEntity.ok(true);
        }
        else {
            return ResponseEntity.ok(false);
        }
    }
}
