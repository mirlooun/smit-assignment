package com.smit.api.service;

import com.smit.api.entity.Procedure;
import com.smit.api.exception.ProcedureNotFoundException;
import com.smit.api.repository.IProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedureServiceImpl implements ProcedureService {
    @Autowired
    private IProcedureRepository procedureRepository;

    @Autowired
    private ProcedureMessagingService procedureMessagingService;

    @Override
    public Procedure saveProcedure(Procedure procedure) {
        Procedure savedProcedure = procedureRepository.save(procedure);
        // Send email
        sendProcedureStartEmail(savedProcedure);

        return savedProcedure;
    }

    @Override
    public List<Procedure> fetchProcedureList() {
        return procedureRepository.findAll();
    }

    @Override
    public Page<Procedure> fetchProcedureListPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
        return procedureRepository.findAll(pageable);
    }

    @Override
    public Optional<Procedure> fetchProcedureById(Long procedureId) {
        return procedureRepository.findById(procedureId);
    }

    @Override
    public Procedure updateProcedure(Procedure procedure, Long procedureId) throws ProcedureNotFoundException {
        Procedure existingProcedure = procedureRepository.findById(procedureId).orElseThrow(ProcedureNotFoundException::new);

        existingProcedure.setName(procedure.getName());
        existingProcedure.setEmail(procedure.getEmail());
        existingProcedure.setReason(procedure.getReason());

        return procedureRepository.save(existingProcedure);
    }

    @Override
    public void deleteProcedureById(Long procedureId) {
        procedureRepository.deleteById(procedureId);
    }

    private void sendProcedureStartEmail(Procedure procedure) {
        procedureMessagingService.sendConfirmationEmail(procedure);
    }
}
