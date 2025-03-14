/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.records;


import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.generated.tables.JStaff;
import org.jooq.generated.tables.pojos.Staff;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StaffRecord extends UpdatableRecordImpl<StaffRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>STAFF.STAFF_ID</code>.
     */
    public StaffRecord setStaffId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.STAFF_ID</code>.
     */
    public Long getStaffId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>STAFF.ACTIVE</code>.
     */
    public StaffRecord setActive(Boolean value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.ACTIVE</code>.
     */
    public Boolean getActive() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>STAFF.EMAIL</code>.
     */
    public StaffRecord setEmail(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.EMAIL</code>.
     */
    public String getEmail() {
        return (String) get(2);
    }

    /**
     * Setter for <code>STAFF.FIRST_NAME</code>.
     */
    public StaffRecord setFirstName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.FIRST_NAME</code>.
     */
    public String getFirstName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>STAFF.LAST_NAME</code>.
     */
    public StaffRecord setLastName(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.LAST_NAME</code>.
     */
    public String getLastName() {
        return (String) get(4);
    }

    /**
     * Setter for <code>STAFF.LAST_UPDATE</code>.
     */
    public StaffRecord setLastUpdate(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.LAST_UPDATE</code>.
     */
    public LocalDateTime getLastUpdate() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>STAFF.PASSWORD</code>.
     */
    public StaffRecord setPassword(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.PASSWORD</code>.
     */
    public String getPassword() {
        return (String) get(6);
    }

    /**
     * Setter for <code>STAFF.PICTURE</code>.
     */
    public StaffRecord setPicture(byte[] value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.PICTURE</code>.
     */
    public byte[] getPicture() {
        return (byte[]) get(7);
    }

    /**
     * Setter for <code>STAFF.USERNAME</code>.
     */
    public StaffRecord setUsername(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.USERNAME</code>.
     */
    public String getUsername() {
        return (String) get(8);
    }

    /**
     * Setter for <code>STAFF.ADDRESS_ID</code>.
     */
    public StaffRecord setAddressId(Long value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.ADDRESS_ID</code>.
     */
    public Long getAddressId() {
        return (Long) get(9);
    }

    /**
     * Setter for <code>STAFF.STORE_ID</code>.
     */
    public StaffRecord setStoreId(Long value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>STAFF.STORE_ID</code>.
     */
    public Long getStoreId() {
        return (Long) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StaffRecord
     */
    public StaffRecord() {
        super(JStaff.STAFF);
    }

    /**
     * Create a detached, initialised StaffRecord
     */
    public StaffRecord(Long staffId, Boolean active, String email, String firstName, String lastName, LocalDateTime lastUpdate, String password, byte[] picture, String username, Long addressId, Long storeId) {
        super(JStaff.STAFF);

        setStaffId(staffId);
        setActive(active);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setLastUpdate(lastUpdate);
        setPassword(password);
        setPicture(picture);
        setUsername(username);
        setAddressId(addressId);
        setStoreId(storeId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised StaffRecord
     */
    public StaffRecord(Staff value) {
        super(JStaff.STAFF);

        if (value != null) {
            setStaffId(value.getStaffId());
            setActive(value.getActive());
            setEmail(value.getEmail());
            setFirstName(value.getFirstName());
            setLastName(value.getLastName());
            setLastUpdate(value.getLastUpdate());
            setPassword(value.getPassword());
            setPicture(value.getPicture());
            setUsername(value.getUsername());
            setAddressId(value.getAddressId());
            setStoreId(value.getStoreId());
            resetChangedOnNotNull();
        }
    }
}
