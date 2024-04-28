package com.smit.api.controller;

import com.smit.api.entity.Procedure;
import com.smit.api.service.ProcedureService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/procedures")
public class ProcedureController {

    Logger logger = LoggerFactory.getLogger(ProcedureController.class);

    @Autowired
    private ProcedureService procedureService;

    @GetMapping
    public ResponseEntity<Page<Procedure>> getProcedures(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int perPage
    ) {
        return ResponseEntity.ok(procedureService.fetchProcedureListPaginated(page, perPage));
    }

    @PostMapping
    public ResponseEntity<Procedure> saveProcedure(@Valid @RequestBody Procedure procedure) {
        Procedure savedProcedure = procedureService.saveProcedure(procedure);
        return ResponseEntity.ok(savedProcedure);
    }
}
