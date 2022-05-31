//package com.romanjandas.calculatorwithsteps;

//import android.util.Log;
//import android.widget.TextView;

public class StringFormation{
    public static String input_string="";
    public static String result="";
    private static final char PL='+';
    private static final char MI='-';
    private static final char DI='/';
    private static final char MU='x';
    private static final char LB='(';
    private static final char RB=')';
    private static final char EQ='=';
    private static final char DE='.';
    public static char p;
    private static char c;
    public static boolean no_key_pressed=true;
    public static boolean allow=false;
    public static int rbn=0,lbn=0;
    public static boolean equal_is_pressed=false;

    //TextView textView;

    public static void verify_input(char x){
        if(equal_is_pressed && cn(x)){
            no_key_pressed=true; allow=false; input_string=""; EvaluateString.steps=""; equal_is_pressed=false;
        }
        else {
            equal_is_pressed=false;
        }
        c=x;
        //Log.d("mytag",Character.toString(x));
                if(no_key_pressed){ // run this -> if no char is entered previously, only allow (,- and numbers
                    if(c==LB||c==MI||cn(c)){
                        p=c;
                        no_key_pressed=false;
                        allow=true;
                        rbn=0;lbn=0;
                        if(c==LB){lbn=1;}
                    }
                }
                else{   // run this -> if a char is entered previously, checks elligibility of char c wrt to p,previous char
                    if(c==LB){allow=alb();}
                    if(c==RB){allow=arb();}
                    if(c==DI){allow=adi();}
                    if(c==MU){allow=amu();}
                    if(c==PL){allow=apl();}
                    if(c==MI){allow=ami();}
                    if(c==DE){allow=ade();}
                    if(cn(c)){allow=anu();}
                    if(c==EQ){allow=aeq();}
                } 
                if(c!=EQ && allow){ // run this -> if elligibility of c wrt p is checked
                    p=c;
                    input_string=input_string+c;
                    //Log.d("mytag",input_string);
                }
                else if(c==EQ && rbn==lbn && allow){ // displays the input_string
                    //Log.d("mytag",rbn+","+lbn);
                    try{
                        EvaluateString.steps=input_string;
                        result=EvaluateString.evaluate_string(input_string);
                    }
                    catch(Exception e){
                        input_string="Some error occured";
                    }
                    //input_string="";
                    //no_key_pressed=true;
                    //allow=false;
                    input_string=result;
                    p=input_string.charAt(input_string.length()-1);
                }
    }

    private static boolean cn(char x){ // check if it is a number
        if(x=='0'||x=='1'||x=='2'||x=='3'||x=='4'||x=='5'||x=='6'||x=='7'||x=='8'||x=='9'){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean alb(){  // allow left brackt..
        if(p==RB||p==DE||cn(p)){
            return false;
        }
        else{
            lbn++;
            return true;
        }
    }

    private static boolean arb(){  // allow right bracket
        if(p==LB||p==DI||p==MU||p==PL||p==MI||p==DE|| rbn==lbn){
            return false;
        }
        else{
            rbn++;
            return true;
        }
    }

    private static boolean adi(){ // allow division
        if(p==LB||p==DI||p==MU||p==PL||p==MI||p==DE){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean amu(){ // allow multi
        if(p==LB||p==DI||p==PL||p==MI||p==DE||p==MU){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean apl(){ // allow plus
        if(p==LB||p==DI||p==MU||p==MI||p==PL||p==DE){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean ami(){ // allow minus
        if(p==PL||p==MI||p==DE){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean ade(){ // allow decimal
        if(p==LB||p==RB||p==MU||p==DI||p==PL||p==MI||p==DE){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean anu(){ // allow numbers
        if(p==RB){
            return false;
        }
        else{
            return true;
        }
    }

    private static boolean aeq(){ //allow equal
        if(p==LB||p==MU||p==DI||p==PL||p==MI||p==DE || rbn!=lbn){
            c='0'; // removing the eq char
            return false;
        }
        else{
            return true;
        }
    }
}