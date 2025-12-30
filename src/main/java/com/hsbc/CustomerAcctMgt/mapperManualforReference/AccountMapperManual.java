//package com.hsbc.CustomerAcctMgt.mapperManualforReference;
//
//import com.hsbc.CustomerAcctMgt.entity.Account;
//import com.hsbc.CustomerAcctMgt.entity.Customer;
//import com.hsbc.CustomerAcctMgt.requestDto.CreateAccountRequest;
//import com.hsbc.CustomerAcctMgt.responseDto.AccountResponseDto;
//
////- Converts CreateAccountRequest + Customer entity → Account entity
////- Converts Account entity → AccountDto
////- Used for account creation and retrieval
//public class AccountMapperManual {
//
//    public static Account toEntity(CreateAccountRequest request, Customer customer){
//        Account account= new Account();
//        account.setCustomer(customer);
//        account.setAccountType(request.accountType());
//        account.setAccountBalance(request.initialBalance());
//        return account;
//    }
//
//    public static AccountResponseDto toDto(Account account) {
//        return new AccountResponseDto(
//                account.getId(),
//                account.getCustomer().getId(),
//                account.getAccountType(),
//                account.getAccountBalance(),
//                account.getCreatedAt()
//        );
//    }
//}
