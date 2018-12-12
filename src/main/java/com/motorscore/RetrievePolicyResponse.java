package com.motorscore;

import java.util.ArrayList;
import java.util.List;


public class RetrievePolicyResponse {
    private List<Policy> locations = new ArrayList<>();

    public List<Policy> getLocations() {
        return locations;
    }

    public void setLocations(final List<Policy> locations) {
        this.locations = locations;
    }
}
