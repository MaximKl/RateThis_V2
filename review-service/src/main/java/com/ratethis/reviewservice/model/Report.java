package com.ratethis.reviewservice.model;

import com.ratethis.reviewservice.model.keys.ReportID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Entity
@Table(name = "user_report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(ReportID.class)
@DynamicUpdate(true)
public class Report {

    @Id
    @Column(name = "report_content")
    private long reviewId;
    @Id
    @Column(name = "report_by")
    private long profileId;

    @Basic
    @Column(name = "report_message")
    @NotBlank(message = "Report can't be blank")
    @Length(max = 5_000, min = 10, message = "Minimum size of report is 10 symbols, maximum size - 5000 symbols")
    private String reportBody;

    @Basic
    @Column(name = "report_time")
    private Timestamp reportTime;

}
