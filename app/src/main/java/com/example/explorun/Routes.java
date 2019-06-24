package com.example.explorun;

public class Routes {
    private Integer id;
    private String name;
    private Double distance;
//    private boolean light;
//    private boolean water;
//    private boolean gradient;

    public Routes(){}
    public Routes(Integer id, String name, Double distance){
        this.id = id;
        this.name=name;
        this.distance=distance;
//        this.light=light;
//        this.water=water;
//        this.gradient=gradient;
    }

    public Routes(String name, Double distance){
        this.name = name;
        this.distance = distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setLight(boolean light) {
//        this.light = light;
//    }
//
//    public void setWater(boolean water) {
//        this.water = water;
//    }
//
//    public void setGradient(boolean gradient) {
//        this.gradient = gradient;
//    }

    public Double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

//    public String isGradient() {
//        if(!gradient)
//            return "No";
//        return "Yes";
//    }
//
//    public String isWithLight() {
//        if(!light)
//            return "No";
//        return "Yes";
//    }
//
//    public String isWithWater() {
//        if(!water)
//            return "No";
//        return "Yes";
//    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString(){
        String output = id + "Route name: " + name + "|| Distance: " + distance;
//                + "|| Lighting: " + isWithLight() + "|| Near Water: " + isWithWater() + "|| Gradient: " + isGradient();
        return output;
    }
}
