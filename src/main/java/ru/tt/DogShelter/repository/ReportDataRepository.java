package ru.tt.DogShelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tt.DogShelter.model.ReportData;

import java.util.Set;

@Repository
public interface ReportDataRepository extends JpaRepository<ReportData, Long> {

    Set<ReportData> findListByChatId(Long id);
    ReportData findByChatId(Long id);
}