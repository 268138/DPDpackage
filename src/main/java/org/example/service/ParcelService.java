package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.Parcel;
import org.example.entity.ParcelStatus;
import org.example.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelService {
    private final ParcelRepository parcelRepository;

    public Parcel getParcelServiceByIdOrThrow(Long id) {
        return parcelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcel with id: " + id + " not found"));
    }

    public Parcel addParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }

    public Parcel getParcelByIdOrThrow(Long id) {
        return parcelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found with id: " + id));
    }

    public void deleteParcelById(Long id) {
        Parcel parcel = parcelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Parcel not found with id: " + id));
        parcelRepository.delete(parcel);
    }

    public Parcel updateParcelStatus(Parcel parcel, ParcelStatus status) {
        parcel.setStatus(status);
        return parcelRepository.save(parcel);
    }

}

