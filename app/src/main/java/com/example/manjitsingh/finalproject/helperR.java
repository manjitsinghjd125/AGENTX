package com.example.manjitsingh.finalproject;

import android.widget.EditText;

/**
 * Created by manjitsingh on 2018-11-25.
 */

public class helperR {
    private final EditText name;
    private final EditText password;
    private resgister beans;
    public helperR(adduser register)
    {
        name=(EditText)register.findViewById(R.id.userid);
        password=(EditText)register.findViewById(R.id.password);
        beans=new resgister();
    }
    public resgister helpregister(){
        beans.setUserid(name.getText().toString());
        beans.setPassword(password.getText().toString());
        return beans;
    }
}
