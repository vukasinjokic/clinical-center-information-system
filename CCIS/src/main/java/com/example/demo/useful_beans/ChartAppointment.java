package com.example.demo.useful_beans;

public class ChartAppointment implements Comparable<ChartAppointment>{
    public String x;
    public int y;

    @Override
    public int compareTo(ChartAppointment ch){
        Integer a = Integer.parseInt(this.x.substring(0,this.x.length()-1));
        Integer b = Integer.parseInt(ch.x.substring(0,ch.x.length()-1));
        return a.compareTo(b);
    }
}
