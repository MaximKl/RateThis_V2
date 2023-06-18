package com.ratethis.reviewservice.dto.mapper;

import com.ratethis.reviewservice.dto.ReportDTO;
import com.ratethis.reviewservice.model.Report;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReportDTOMapper implements Function<Report, ReportDTO> {
    @Override
    public ReportDTO apply(Report report) {
        return new ReportDTO(report.getReviewId(),report.getReportBody());
    }
}
