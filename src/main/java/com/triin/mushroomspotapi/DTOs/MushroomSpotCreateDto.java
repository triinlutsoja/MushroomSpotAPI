package com.triin.mushroomspotapi.DTOs;

import java.util.Objects;

public class MushroomSpotCreateDto {
    private String description;
    private Double longitude;
    private Double latitude;

    public MushroomSpotCreateDto() {}

    public MushroomSpotCreateDto(String description, Double longitude, Double latitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "MushroomSpotCreateDto{" +
                "description='" + description + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof MushroomSpotCreateDto that)) return false;

        return Objects.equals(description, that.description) &&  Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(longitude);
        result = 31 * result + Objects.hashCode(latitude);
        return result;
    }
}
