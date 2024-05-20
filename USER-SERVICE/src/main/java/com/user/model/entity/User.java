package com.user.model.entity;

import com.user.model.response.UserResponseObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String companyName;

    private Long empCode;

    public UserResponseObject getAResponseObject(){
        UserResponseObject userResponseObject = new UserResponseObject();
        userResponseObject.setName(this.name);
        userResponseObject.setCompanyName(this.companyName);
        userResponseObject.setEmpCode(this.empCode);
        return userResponseObject;
    }

}
