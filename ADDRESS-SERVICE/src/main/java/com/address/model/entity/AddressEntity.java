package com.address.model.entity;

import com.address.model.response.AddressResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String addreessType;
    private String address;
    private Long userId;

    public AddressResponse getAsResponse(){
        AddressResponse response = new AddressResponse();
        response.setAddreessType(this.addreessType);
        response.setAddress(this.address);
        response.setUserId(this.userId);
        return response;
    }

}
