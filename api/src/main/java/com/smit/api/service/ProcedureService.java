package com.smit.api.service;

import com.smit.api.entity.Procedure;
import com.smit.api.exception.ProcedureNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProcedureService {
    Procedure saveProcedure(Procedure procedure);

    List<Procedure> fetchProcedureList();

    Page<Procedure> fetchProcedureListPaginated(int pageNo, int pageSize);

    Optional<Procedure> fetchProcedureById(Long procedureId);

    Procedure updateProcedure(Procedure procedure, Long procedureId) throws ProcedureNotFoundException;

    void deleteProcedureById(Long procedureId);
}
