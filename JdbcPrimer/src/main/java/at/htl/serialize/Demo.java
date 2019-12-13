package at.htl.serialize;

import java.io.Serializable;

// Serializable is a marker interface
// marker interface ... no methods has to be implemented
class Demo implements Serializable {

    private static final long serialVersionUID = 2L;

    public int a;
    public String b;

    public Demo(int a, String b)
    {
        this.a = a;
        this.b = b;
    }

}