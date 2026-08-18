package com.pm.patientservice.mapper;

import java.time.LocalDate;

import com.pm.patientservice.models.Patient;
import com.pm.patientservice.models.RegisterPatientRequestDTO;
import com.pm.patientservice.models.UpdatePatientRequestDTO;
import com.pm.patientservice.models.PatientResponseDTO;

public final class PatientMapper {

    private PatientMapper() {

    }

    public static PatientResponseDTO toDto(final Patient entity) {
        PatientResponseDTO dto = new PatientResponseDTO();

        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setDateOfBirth(entity.getDateOfBirth().toString());
        dto.setAddress(entity.getAddress());

        return dto;
    }

    public static Patient toEntity(final RegisterPatientRequestDTO dto) {
        Patient entity = new Patient();

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        entity.setRegisteredDate(LocalDate.parse(dto.getRegisteredDate()));

        return entity;
    }

    public static Patient toEntity(final UpdatePatientRequestDTO dto, Patient entity) {

        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            entity.setEmail(dto.getEmail());
        }

        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            entity.setAddress(dto.getAddress());
        }

        if (dto.getDateOfBirth() != null && !dto.getDateOfBirth().isBlank()) {
            entity.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
        }

        return entity;
    }

}