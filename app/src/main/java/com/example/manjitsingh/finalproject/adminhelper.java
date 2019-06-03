package com.example.manjitsingh.finalproject;

import android.widget.EditText;

/**
 * Created by manjitsingh on 2017-12-11.
 */

public class adminhelper {
    private final EditText name;
    private final EditText password;
    private adminbeans beans;
    public adminhelper(RegisterUser register)
    {
        name=(EditText)register.findViewById(R.id.userid);
        password=(EditText)register.findViewById(R.id.password);
        beans=new adminbeans();
    }
    public adminbeans helpadmin(){
        beans.setUserid(name.getText().toString());
        beans.setPassword(password.getText().toString());
        return beans;
    }

}
