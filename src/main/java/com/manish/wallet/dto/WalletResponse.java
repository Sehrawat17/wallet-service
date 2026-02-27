package com.manish.wallet.dto;

//import lombok.Builder;
//import lombok.Getter;

import java.util.UUID;

//@Getter
//@Builder
public class WalletResponse {

    private UUID walletId;
    private Long balance;

    public WalletResponse() {
    }

    public WalletResponse(UUID walletId, Long balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
