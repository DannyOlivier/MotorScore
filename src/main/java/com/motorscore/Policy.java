package com.motorscore;


public class Policy {
    private double xAcceleration;
    private double yAcceleration;
    private double speed;
    private String policyNumber;

    public double getxAcceleration() {
        return xAcceleration;
    }

    public void setxAcceleration(double xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public double getyAcceleration() {
        return yAcceleration;
    }

    public void setyAcceleration(double yAcceleration) {
        this.yAcceleration = yAcceleration;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Policy{");
        sb.append("xAcceleration=").append(xAcceleration);
        sb.append(",yAcceleration=").append(yAcceleration);
        sb.append(",speed=").append(speed);
        sb.append(", policyNumber='").append(policyNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
