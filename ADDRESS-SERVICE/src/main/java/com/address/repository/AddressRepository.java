package com.address.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.address.model.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

    Optional<AddressEntity> findAddressByAddressId(Long addressId);

    List<AddressEntity> findAddressesByUserId(Long userId);

}
