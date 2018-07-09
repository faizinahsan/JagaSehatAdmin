package com.example.pbo.jagasehatadmin;

/**
 * Created by M FaizinAhsan on 6/18/2018.
 */

public class User {
    public String key;
    public String nama;
    public String usia;
    public String jk;
    public String pendidikan;
    public String pekerjaan;
    public String email;
    public String date;

    public String getDate() {
        return date;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getKey() {
        return key;
    }

    public String getNama() {
        return nama;
    }

    public String getUsia() {
        return usia;
    }

    public String getJk() {
        return jk;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public String getEmail() {
        return email;
    }

    public User(String key, String username, String usia, String jk, String pendidikan, String
            pekerjaan, String
                        email,String date) {
        this.key = key;
        this.nama = username;
        this.usia = usia;
        this.jk = jk;
        this.pendidikan = pendidikan;
        this.pekerjaan = pekerjaan;
        this.email = email;
        this.date = date;
    }
}
