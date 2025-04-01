package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Parcel;
import org.example.repository.ParcelRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParcelService {
    private final ParcelRepository parcelRepository;

    public Parcel getParcelServicetByIdOrThrow(Long id) {
        return parcelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcel with id: " + id + " not found"));
    }
}

