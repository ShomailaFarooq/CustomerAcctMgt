//package com.hsbc.CustomerAcctMgt.mapperManualforReference;
//
//import com.hsbc.CustomerAcctMgt.entity.Transaction;
//import com.hsbc.CustomerAcctMgt.responseDto.TransactionResponseDto;
//
////- Converts Transaction entity → TransactionDto
////- Used for deposit, withdraw, transfer responses
//public class TransactionMapperManual {
//    public static TransactionResponseDto toDto(Transaction transaction) {
//        return new TransactionResponseDto(
//                transaction.getId(),
//                transaction.getAmount(),
//                transaction.getAccount().getId(),
//                transaction.getTransactionType(),
//                transaction.getCreatedAt()
//        );
//    }
//
//}
