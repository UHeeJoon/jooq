/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cityId;
    private String city;
    private LocalDateTime lastUpdate;
    private Long countryId;

    public City() {}

    public City(City value) {
        this.cityId = value.cityId;
        this.city = value.city;
        this.lastUpdate = value.lastUpdate;
        this.countryId = value.countryId;
    }

    public City(
        Long cityId,
        String city,
        LocalDateTime lastUpdate,
        Long countryId
    ) {
        this.cityId = cityId;
        this.city = city;
        this.lastUpdate = lastUpdate;
        this.countryId = countryId;
    }

    /**
     * Getter for <code>CITY.CITY_ID</code>.
     */
    public Long getCityId() {
        return this.cityId;
    }

    /**
     * Setter for <code>CITY.CITY_ID</code>.
     */
    public City setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    /**
     * Getter for <code>CITY.CITY</code>.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Setter for <code>CITY.CITY</code>.
     */
    public City setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * Getter for <code>CITY.LAST_UPDATE</code>.
     */
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * Setter for <code>CITY.LAST_UPDATE</code>.
     */
    public City setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    /**
     * Getter for <code>CITY.COUNTRY_ID</code>.
     */
    public Long getCountryId() {
        return this.countryId;
    }

    /**
     * Setter for <code>CITY.COUNTRY_ID</code>.
     */
    public City setCountryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final City other = (City) obj;
        if (this.cityId == null) {
            if (other.cityId != null)
                return false;
        }
        else if (!this.cityId.equals(other.cityId))
            return false;
        if (this.city == null) {
            if (other.city != null)
                return false;
        }
        else if (!this.city.equals(other.city))
            return false;
        if (this.lastUpdate == null) {
            if (other.lastUpdate != null)
                return false;
        }
        else if (!this.lastUpdate.equals(other.lastUpdate))
            return false;
        if (this.countryId == null) {
            if (other.countryId != null)
                return false;
        }
        else if (!this.countryId.equals(other.countryId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.cityId == null) ? 0 : this.cityId.hashCode());
        result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
        result = prime * result + ((this.lastUpdate == null) ? 0 : this.lastUpdate.hashCode());
        result = prime * result + ((this.countryId == null) ? 0 : this.countryId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("City (");

        sb.append(cityId);
        sb.append(", ").append(city);
        sb.append(", ").append(lastUpdate);
        sb.append(", ").append(countryId);

        sb.append(")");
        return sb.toString();
    }
}
