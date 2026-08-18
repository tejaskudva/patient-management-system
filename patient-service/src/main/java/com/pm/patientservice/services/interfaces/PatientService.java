package com.pm.patientservice.services.interfaces;

import java.util.List;

import com.pm.patientservice.models.RegisterPatientRequestDTO;
import com.pm.patientservice.models.UpdatePatientRequestDTO;
import com.pm.patientservice.models.PatientResponseDTO;

public interface PatientService {

    List<PatientResponseDTO> getPatients();

    PatientResponseDTO registerPatient(RegisterPatientRequestDTO patientDto);

    PatientResponseDTO updatePatient(String id, UpdatePatientRequestDTO patientDto);

    void deletePatient(String id);

}