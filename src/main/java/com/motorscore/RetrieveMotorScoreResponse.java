package com.motorscore;

public class RetrieveMotorScoreResponse {

    private String policyNumber;
    private Double motorScore;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Double getMotorScore() {
        return motorScore;
    }

    public void setMotorScore(Double motorScore) {
        this.motorScore = motorScore;
    }
}
