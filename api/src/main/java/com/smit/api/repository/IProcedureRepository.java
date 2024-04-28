package com.smit.api.repository;

import com.smit.api.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProcedureRepository extends JpaRepository<Procedure, Long> {}
