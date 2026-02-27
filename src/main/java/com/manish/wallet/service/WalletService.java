package com.manish.wallet.service;

import com.manish.wallet.dto.OperationType;
import com.manish.wallet.dto.WalletRequest;
import com.manish.wallet.dto.WalletResponse;
import com.manish.wallet.entity.Wallet;
import com.manish.wallet.exception.InsufficientFundsException;
import com.manish.wallet.exception.WalletNotFoundException;
import com.manish.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void process(WalletRequest request) {

        if (request.getOperationType() == OperationType.DEPOSIT) {

            int updated = walletRepository.deposit(
                    request.getWalletId(),
                    request.getAmount()
            );

            if (updated == 0) {
                throw new WalletNotFoundException("Wallet not found");
            }

        } else {

            int updated = walletRepository.withdraw(
                    request.getWalletId(),
                    request.getAmount()
            );

            if (updated == 0) {
                throw new InsufficientFundsException("Insufficient funds or wallet not found");
            }
        }
    }

    @Transactional(readOnly = true)
    public WalletResponse getBalance(UUID walletId) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

        return new WalletResponse(
                wallet.getId(),
                wallet.getBalance()
        );
    }
}