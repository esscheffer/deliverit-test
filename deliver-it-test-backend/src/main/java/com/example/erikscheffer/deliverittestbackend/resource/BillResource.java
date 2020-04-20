package com.example.erikscheffer.deliverittestbackend.resource;

import com.example.erikscheffer.deliverittestbackend.model.Bill;
import com.example.erikscheffer.deliverittestbackend.model.api.BillApiCreate;
import com.example.erikscheffer.deliverittestbackend.repository.BillRepository;
import com.example.erikscheffer.deliverittestbackend.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillResource {

    private final BillService billService;

    public BillResource(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> listAll() {
        return billService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bill> create(@Valid @RequestBody BillApiCreate billApiCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(billService.createBill(billApiCreate));
    }
}
