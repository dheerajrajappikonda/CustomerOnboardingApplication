package com.customeronboarding.service;

import com.customeronboarding.entity.Account;
import com.customeronboarding.entity.Customer;
import com.customeronboarding.repository.AccountRepository;
import com.customeronboarding.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    // Create account with existing customer reference
    public Account createAccount(Account account) {
        if (account.getCustomer() == null || account.getCustomer().getCustomerId() == null) {
            throw new IllegalArgumentException("Customer information is required for account creation.");
        }

        // Fetch and set managed customer entity
        Customer customer = customerRepository.findById(account.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + account.getCustomer().getCustomerId()));

        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public Account updateAccount(Long id, Account updatedAccount) {
        return accountRepository.findById(id)
                .map(account -> {
                    account.setAccountType(updatedAccount.getAccountType());
                    account.setAccountStatus(updatedAccount.getAccountStatus());
                    account.setCreatedBy(updatedAccount.getCreatedBy());

                    // Optional: Update associated customer if sent
                    if (updatedAccount.getCustomer() != null && updatedAccount.getCustomer().getCustomerId() != null) {
                        Customer customer = customerRepository.findById(updatedAccount.getCustomer().getCustomerId())
                                .orElseThrow(() -> new RuntimeException("Customer not found with id " + updatedAccount.getCustomer().getCustomerId()));
                        account.setCustomer(customer);
                    }

                    return accountRepository.save(account);
                })
                .orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with id " + id);
        }
        accountRepository.deleteById(id);
    }
}
