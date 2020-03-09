package com.mapfap.spez.repository;

import com.mapfap.spez.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepositry extends JpaRepository<Address, Long> {
}
