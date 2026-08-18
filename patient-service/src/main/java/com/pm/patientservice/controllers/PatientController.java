package com.pm.patientservice.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patientservice.models.RegisterPatientRequestDTO;
import com.pm.patientservice.models.UpdatePatientRequestDTO;
import com.pm.patientservice.models.PatientResponseDTO;
import com.pm.patientservice.services.interfaces.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "Patient", description = "API Interface for managing patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Returns a list of all patients registered")
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping
    @Operation(summary = "Used to register a new patient")
    public ResponseEntity<PatientResponseDTO> registerPatient(@RequestBody @Valid RegisterPatientRequestDTO dto) {
        return ResponseEntity.ok(patientService.registerPatient(dto));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Used to update a registered patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable String id,
            @RequestBody @Valid UpdatePatientRequestDTO dto) {
        return ResponseEntity.ok(patientService.updatePatient(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Used to delete a registered patient")
    public ResponseEntity<String> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient Entry Deleted");
    }
}