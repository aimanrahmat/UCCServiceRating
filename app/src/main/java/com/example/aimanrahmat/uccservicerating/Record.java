package com.example.aimanrahmat.uccservicerating;

public class Record {
    private int id;
    private int counter;
    private float star;
    private String comment;
    private String date;

    public Record(){

    }

    public Record(int id,int counter, float star, String comment, String date){
        this.id=id;
        this.counter=counter;
        this.star=star;
        this.comment=comment;
        this.date=date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public void setComment(String comment){ this.comment = comment;}

    public void setDate(String date){ this.date = date;}

    public int getId() {
        return id;
    }
    public int getCounter() {
        return counter;
    }
    public float getStar() {
        return star;
    }
    public String getComment() {
        return comment;
    }
    public String getDate(){ return date; }
}
