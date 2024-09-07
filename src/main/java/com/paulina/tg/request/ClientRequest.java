package com.paulina.tg.request;

import lombok.Data;

@Data
public class ClientRequest {
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
}
