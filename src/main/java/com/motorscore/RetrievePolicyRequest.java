package com.motorscore;

/**
 * Created by jamiecraane on 12/12/2016.
 */
public class RetrievePolicyRequest {
    private String policyNumber;

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(final String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RetrievePolicyRequest{");
        sb.append("policyNumber='").append(policyNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
