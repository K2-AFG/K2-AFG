package com.example.k2_afg;

public class CalculatingLocation {
	double lat1;
	double long1;
	double lat2;
	double long2;
	
	public CalculatingLocation(double lat1, double long1, double lat2, double long2) {
		this.lat1 = lat1;
		this.long1 = long1;
		this.lat2 = lat2;
		this.long2 = long2;
		
	}
	
	public double calcDistance() {
		int r = 6371;
		double deg1 = lat1*Math.PI/180;
		double deg2 = lat2*Math.PI/180;
		double cdeg1 = (lat2-lat1)*Math.PI/180;
		double cdeg2 = (long2-long1)*Math.PI/180;
		
		double haversine = Math.sin(cdeg1/2)*Math.asin(cdeg1/2)+Math.cos(deg1)*Math.cos(deg2)*Math.sin(cdeg2/2)*Math.sin(cdeg2/2);
		double x = 2*Math.atan2(Math.sqrt(haversine), Math.sqrt(1-haversine));

		double distance = r*x*0.621371;
        distance = (int)(distance*100+0.5)/100.0;
        return distance;
		
	}
	
	

}
