package com.carcar.telemedicine;


public class MedicalStore {
    private String name;
    private String address;
    private String phone;

    public MedicalStore(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
}
