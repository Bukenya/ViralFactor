package com.viralfactor.gamedata;

public class Score {
    
    //private variables
    int _id;
    String _scoreValue;
    //String _score_date;
     
    // Empty constructor
    public Score(){
         
    }
    // constructor
    public Score(int id, String value){
        this._id = id;
        this._scoreValue = value;
        //this._score_date = _score_date;
    }
     
    // constructor
    public Score(String value){
        this._scoreValue = value;
        //this._score_date = _date;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getValue(){
        return this._scoreValue;
    }
     
    // setting score value
    public void setValue(String value){
        this._scoreValue = value;
    }

}