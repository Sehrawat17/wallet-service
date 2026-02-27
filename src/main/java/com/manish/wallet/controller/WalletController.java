package com.manish.wallet.controller;

import com.manish.wallet.dto.WalletRequest;
import com.manish.wallet.dto.WalletResponse;
import com.manish.wallet.service.WalletService;
//import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
//@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/wallet")
    public ResponseEntity<Void> process(@Valid @RequestBody WalletRequest request) {
        walletService.process(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<WalletResponse> getBalance(@PathVariable UUID walletId) {
        return ResponseEntity.ok(walletService.getBalance(walletId));
    }
}
