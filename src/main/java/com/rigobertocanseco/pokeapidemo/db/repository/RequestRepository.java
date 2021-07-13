package com.rigobertocanseco.pokeapidemo.db.repository;

import com.rigobertocanseco.pokeapidemo.db.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

}
