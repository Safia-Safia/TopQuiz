package com.safia.topquiz.Model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<User> mUserList;

    public UserList (){
        mUserList = new ArrayList<User>();
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public void addUser(User user) {
        mUserList.add(user);
    }
}
