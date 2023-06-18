package com.ratethis.reviewservice.repository;

import com.ratethis.reviewservice.model.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Long> {

    List<Report> findAllByProfileId(long profileId);

}
