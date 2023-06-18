package com.ratethis.reviewservice.model.keys;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReportID implements Serializable {
   private long reviewId;
   private long profileId;
}
