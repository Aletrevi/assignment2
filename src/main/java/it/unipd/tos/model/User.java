////////////////////////////////////////////////////////////////////
// [Alessio] [Trevisan] [1187399]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class User {
    private String username;
    private String name;
    private String surname;
    private int age;
    public User(String u, String n, String s, int a){
        this.username = u;
        this.name = n;
        this.surname = s;
        this.age = a;
    }
    public String getUsername(){
        return username;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public int getAge(){
        return age;
    }
}