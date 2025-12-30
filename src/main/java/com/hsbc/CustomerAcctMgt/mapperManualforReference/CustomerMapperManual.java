//package com.hsbc.CustomerAcctMgt.mapperManualforReference;
//
//import com.hsbc.CustomerAcctMgt.entity.Customer;
//import com.hsbc.CustomerAcctMgt.requestDto.CreateCustomerRequest;
//import com.hsbc.CustomerAcctMgt.responseDto.CustomerResponseDto;
//
////- Converts CreateCustomerRequest → Customer entity
////- Converts Customer entity → CustomerDto
////- Used for customer creation and retrieval
//public class CustomerMapperManual {
//
//    //- Takes the request DTO
//    //- Creates a new Customer entity
//    //- Copies fields from request → entity
//    //- Does NOT set id or createdAt (database will generate them)
//    public static Customer toEntity(CreateCustomerRequest request) {
//        Customer customer = new Customer();
//        customer.setName(request.name());
//        customer.setEmail(request.email());
//        customer.setPhone(request.phone());
//        return customer;
//    }
//
//    //- Takes the entity
//    //- Converts it into a response DTO
//    //- This DTO is safe to expose to the client
//    public static CustomerResponseDto toDto(Customer customer) {
//        return new CustomerResponseDto(
//                customer.getId(),
//                customer.getName(),
//                customer.getEmail(),
//                customer.getPhone(),
//                customer.getCreatedAt()
//        );
//    }
//}
