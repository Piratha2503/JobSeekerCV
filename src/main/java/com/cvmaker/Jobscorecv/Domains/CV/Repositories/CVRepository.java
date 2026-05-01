package com.cvmaker.Jobscorecv.Domains.CV.Repositories;

import com.cvmaker.Jobscorecv.Domains.CV.Entities.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CVRepository extends JpaRepository<CV, Long> {

    @Query("""
        SELECT DISTINCT cv FROM CV cv
        LEFT JOIN FETCH cv.selectedExperiences
        LEFT JOIN FETCH cv.selectedEducations
        LEFT JOIN FETCH cv.selectedCertifications
        LEFT JOIN FETCH cv.selectedSkills
        LEFT JOIN FETCH cv.selectedProjects
        WHERE cv.id = :id
    """)
    Optional<CV> findByIdWithAll(@Param("id") Long id);

}
