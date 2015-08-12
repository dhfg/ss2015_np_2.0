package np2015;

import java.util.ArrayList;
import java.util.List;

public class Knoten{
	
	private int x = 0;
	private int y = 0;
	private double outflow = 0;
	private double inflow = 0;
	private double akkuR = 0;
	private double akkuL = 0;

	private double akku = 0;
	private double current_value = 0;
	
	// TODO: konstruktor anpassen?
	
	/**
	 * @param x
	 * @param y
	 * @param current_value
	 *
	 * Konstruktor
	 */
	public Knoten(int x, int y, double current_value, List<Knoten> allNodes){
		this.x = x; // ---- weite
		this.y = y; // | höhe
		this.current_value = current_value;
		this.akku = 0;
		allNodes.add(this);
	//	System.out.println(allNodes);
	}
	
	
	/**
	 * TODO: vielleicht in aller oberste klasse ziehen, dann spart man sich diese allNodes liste und kann vond dort 
	 * aus mit hilfe der internen Knotenlisten von 
	 * den spalten suchen
	 * @param actual_node
	 * @return Knoten der über dem aktuellen liegt
	 */
	public Knoten get_neighbor_oben(List<Knoten> allNodes){
		
		int pos_neighbor_o = this.getY()-1;
		
		int spalte = this.getX();
		for (Knoten k : allNodes){
			if (k.getX() == spalte && k.getY() == pos_neighbor_o){
				return k;
			}
		}
			return null;
			
	}
		
	
	/**
	 *  
	 * @param allNodes
	 * @return Knoten der unter dem aktuellen Knoten liegt
	 */
	public  Knoten get_neighbor_unten(List<Knoten> allNodes){
		
		int pos_neighbor_u = this.getY()+1;
		
		int spalte = this.getX();
		
		for (Knoten k : allNodes){
			if (k.getX() == spalte && k.getY() == pos_neighbor_u){
				return k;
			}
		}
		return null;
		}
	
	
	/**
	 * 
	 * @param actual_node
	 * @return Knoten der rechts neben dem aktuellen liegt
	 */
	public Knoten get_neighbor_rechts(List<Knoten> allNodes){
	
		int zeile = this.getY();
		int pos_neighbor_r = this.getX()+1;
		
		for ( Knoten k : allNodes ){
			if (k.getY() == zeile && k.getX() == pos_neighbor_r ){
				return k;
			}
		}
		return null;
		}
	
	/**
	 * 
	 * @param allNodes
	 * @return Knoten der links neben dem aktuellen liegt 
	 */
	public Knoten get_neighbor_links(List<Knoten> allNodes){
		
		int zeile = this.getY();
		int pos_neighbor_l = this.getX()-1;
	
		for ( Knoten k : allNodes){
			if (k.getY() == zeile && k.getX() == pos_neighbor_l){
				return k;
			}
		}
		return null;
	}

		
	
	/**
	 * getter und setter für alle Felder von Knoten
	 */
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(double current_value) {
		this.current_value = current_value;
	}
	public double getAkku() {
		return akku;
	}

	public void setAkku(double d) {
		this.akku = d;
	}
	
	public double getOutflow() {
		return outflow;
	}


	public void setOutflow(double outflow) {
		this.outflow = outflow;
	}


	public double getInflow() {
		return inflow;
	}


	public void setInflow(double inflow) {
		this.inflow = inflow;
	}
	

	public double getAkkuR() {
		return akkuR;
	}


	public void setAkkuR(double akkuR) {
		this.akkuR = akkuR;
	}


	public double getAkkuL() {
		return akkuL;
	}


	public void setAkkuL(double akkuL) {
		this.akkuL = akkuL;
	}
	
	
	//TODO: == operator überschreiben
	public boolean equals(Knoten other){
		return true;
	}
	
	/**
	 *to string funktion um Knoten ausgeben zu können
	 */
	public String toString(){
		return( "( x: " + x + " y: " + y + " value: " + current_value + " )");
		
	}


	
}
