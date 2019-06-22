package com.hr.hrsystem.model;

import java.util.Arrays;


public enum JobType {
    JAVA_SOFTWARE_DEVELOPER(10101010),
    HR(11111111);

    private Integer jobId;

    JobType(int jobId) {
        this.jobId = jobId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public static JobType findByJobId(int jobId) {
        return Arrays.stream(JobType.values())
                .filter(e -> e.getJobId().equals(jobId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", jobId)));
    }
}
