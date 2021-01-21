package com.example.eltextest.login.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UserInfoResponse implements Parcelable {
    private final List<String> permissions;
    private final String email;
    private final String roleId;
    private final String username;

    protected UserInfoResponse(Parcel in) {
        permissions = in.createStringArrayList();
        email = in.readString();
        roleId = in.readString();
        username = in.readString();
    }

    public static final Creator<UserInfoResponse> CREATOR = new Creator<UserInfoResponse>() {
        @Override
        public UserInfoResponse createFromParcel(Parcel in) {
            return new UserInfoResponse(in);
        }

        @Override
        public UserInfoResponse[] newArray(int size) {
            return new UserInfoResponse[size];
        }
    };

    public List<String> getPermissions() {
        return permissions;
    }

    public String getEmail() {
        return email;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(roleId);
        dest.writeString(username);
        dest.writeStringList(permissions);
    }

}
