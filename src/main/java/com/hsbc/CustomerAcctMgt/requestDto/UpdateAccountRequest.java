package com.hsbc.CustomerAcctMgt.requestDto;

import com.hsbc.CustomerAcctMgt.enums.AccountStatus;

public record UpdateAccountRequest(
        AccountStatus status,
        String nickname
) {}
