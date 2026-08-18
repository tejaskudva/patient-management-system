package com.pm.patientservice.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pm.patientservice.constants.ErrorConstants;
import com.pm.patientservice.exceptions.CustomRequestValidationException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.models.Patient;
import com.pm.patientservice.models.RegisterPatientRequestDTO;
import com.pm.patientservice.models.UpdatePatientRequestDTO;
import com.pm.patientservice.models.PatientResponseDTO;
import com.pm.patientservice.repository.PatientRepository;
import com.pm.patientservice.services.interfaces.PatientService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepo;

    @Override
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patients.stream().map(PatientMapper::toDto).toList();
    }

    @Override
    public PatientResponseDTO registerPatient(RegisterPatientRequestDTO patientDto) {

        if (patientRepo.existsByEmail(patientDto.getEmail())) {
            throw new CustomRequestValidationException(
                    ErrorConstants.DUPLICATE_EMAIL);
        }

        return PatientMapper.toDto(patientRepo.save(PatientMapper.toEntity(patientDto)));
    }

    @Override
    public PatientResponseDTO updatePatient(String id, UpdatePatientRequestDTO patientDto) {

        Patient entity = patientRepo.findById(UUID.fromString(id))
                .orElseThrow(() -> new CustomRequestValidationException(
                        ErrorConstants.PATIENT_NOT_FOUND));

        if (patientRepo.existsByEmailAndIdNot(patientDto.getEmail(), UUID.fromString(id))) {
            throw new CustomRequestValidationException(
                    ErrorConstants.DUPLICATE_EMAIL);
        }

        return PatientMapper.toDto(patientRepo.save(PatientMapper.toEntity(patientDto, entity)));
    }

    @Override
    public void deletePatient(String id) {

        if (!patientRepo.existsById(UUID.fromString(id))) {
            throw new CustomRequestValidationException(
                    ErrorConstants.PATIENT_NOT_FOUND);
        }

        patientRepo.deleteById(UUID.fromString(id));
    }

}