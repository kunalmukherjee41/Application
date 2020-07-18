package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.Semaphore;

public class Client implements Parcelable {

    String name;
    String phone;
    String emil, note;

    public Client() {
        name = "A";
        phone = "A";
        emil = "A";
        note = "A";
    }
    public Client(String name, String phone, String emil, String note) {
        this.name = name;
        this.phone = phone;
        this.emil = emil;
        this.note = note;
    }

    protected Client(Parcel in) {
        name = in.readString();
        phone = in.readString();
        emil = in.readString();
        note = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel in) {
            return new Client(in);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmil() {
        return emil;
    }

    public void setEmil(String emil) {
        this.emil = emil;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(note);
        dest.writeString(phone);
        dest.writeString(emil);

    }
}
