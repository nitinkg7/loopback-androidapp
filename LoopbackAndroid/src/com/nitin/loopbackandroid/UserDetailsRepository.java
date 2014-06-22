package com.nitin.loopbackandroid;

import com.strongloop.android.loopback.ModelRepository;

public class UserDetailsRepository extends ModelRepository<UserDetailModel> {
    public UserDetailsRepository() {
        super("userdetails","userdetails", UserDetailModel.class);
    }
}
