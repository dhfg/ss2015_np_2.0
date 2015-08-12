package np2015;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// verwaltet alle Knoten einer Spalte
public class Spalten extends Thread {

	private int spalte;
	private ArrayList<Knoten> knoten = new ArrayList<Knoten>();

	//Konstruktor
	public Spalten(int spalte){
		this.spalte = spalte;
	}
	
	// startet Berechnungen
	public void run(){
		for (int i = 0; i < 10; i++) {
			pBerechnung();
		}
	//TODO AUSTAUSCH!!
		//save the outflow
		for (int i = 0; i< 10; i++){
			Iterator<Knoten> nodes = knoten.iterator();
			while (nodes.hasNext()) {
				Knoten current = nodes.next();
				current.setOutflow(current.getAkkuL()+current.getAkkuR());
				current.setAkkuL(0.0);
				current.setAkkuR(0.0);
			}
		}
		
		for (int i = 0; i< 10; i++){
			Iterator<Knoten> nodes = knoten.iterator();
			while (nodes.hasNext()) {
				Knoten current = nodes.next();
				current.setCurrent_value(current.getAkku());
				current.setAkkuL(0.0);
				current.setAkkuR(0.0);
				current.setAkku(0.0);
			}
		}
		
		//while(true) {

		//}		
	//	System.out.println(knoten);
	}

	/**
	 * organize the calculation
	 * */
	public synchronized void pBerechnung(){
		//helpListe zum Zwischenspeichern der Nachbar Knoten
		ArrayList<Knoten> helpListeTopBottom = new ArrayList<Knoten>();
//		ArrayList<Double> helpListLeft = new ArrayList<Double>();
//		ArrayList<Double> helpListRight = new ArrayList<Double>();
	
		//iteriere über die Knoten in der Knotenliste und berechne die Rate zu NachbarKnotenOben und NachbarKnotenUnten
		Iterator<Knoten> nodes = knoten.iterator();
		while (nodes.hasNext()) {
			Knoten actual = nodes.next();
			Knoten neighbor_oben = actual.get_neighbor_oben(knoten);
			Knoten neighbor_unten = actual.get_neighbor_unten(knoten);

			//neighbor Oben
			calculateNeighborTopBottom(actual, neighbor_oben, helpListeTopBottom, true);
			//neighbor unten
			calculateNeighborTopBottom(actual, neighbor_unten, helpListeTopBottom, false);
			//neighbor Links
			actual.setAkkuL( actual.getAkkuL() + calculateNeighborLeftRight(actual,true));
			//neighbor rechts
			actual.setAkkuR( actual.getAkkuR() + calculateNeighborLeftRight(actual, false));

		}
		//Iteriere über helpListe um die Knoten von oben und unten in knotenListe einzufügen
		knoten.addAll(helpListeTopBottom);	
		Iterator<Knoten> j = knoten.iterator();
		while (j.hasNext()) {
				Knoten actual = j.next();
				actual.setCurrent_value(actual.getCurrent_value() + actual.getAkkuR() + actual.getAkkuL()+ actual.getAkku());
				actual.setAkku(0.0);
		}
	}
	
	/**
	 * calculate neighbor left and right
	 * */
	public double calculateNeighborLeftRight(Knoten actual, boolean left){
		double rate = 0.0;
		Neighbor neighNeigh = Neighbor.Right;
		if (left){
			neighNeigh = Neighbor.Left;
			//rate für nachbar Links

			rate = actual.getCurrent_value()*Matrix.ginfo.getRateForTarget(actual.getX(), actual.getY(), neighNeigh);
			System.out.println("rateL "+ rate);
		}else{
			//rate für nachbar rechts
			rate = actual.getCurrent_value()*Matrix.ginfo.getRateForTarget(actual.getX(), actual.getY(), neighNeigh);
			System.out.println("rateR "+ rate);
		}
		return rate;
	}
	
	/**
	 * calculate neighbor top and bottom
	 * */
	public void calculateNeighborTopBottom(Knoten actual, Knoten neighbor, ArrayList<Knoten> helpListe, boolean top){
		Neighbor neighNeigh  = Neighbor.Bottom;
		if (top){neighNeigh  = Neighbor.Top;}
		double rate = actual.getCurrent_value()*Matrix.ginfo.getRateForTarget(actual.getX(), actual.getY(), neighNeigh );
		//wenn NachbarOben einen Wert empfangen soll, speichere ihn
		if (neighbor == null && rate > 0 ){
				// erzeugt neuen Knoten mit inizialem current_vallue 0.0
				//speichere in HelpListe!
			
			// TODO: createNode gibtes nicht mehr, das muss iwie über die martixklasse laufen
				neighbor = actual.createNode(actual.getX(),actual.getY()-1, helpListe);
		}
		//wenn nachbarOben existiert, speichere die rate in den Akku von currentNode und NachbarOben 
		if (neighbor != null){
			//System.out.println("übergangsrate nach oben: "+ actual.getCurrent_value()*
				//	ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
			neighbor.setAkku(neighbor.getAkku() + actual.getCurrent_value()*
					Matrix.ginfo.getRateForTarget(actual.getX(), actual.getY(), neighNeigh ));
		
			actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
					Matrix.ginfo.getRateForTarget(actual.getX(), actual.getY(), neighNeigh ));
		}
	}
	
	public String toString(){
		return ("( number: "+ spalte + " knoten: " + knoten + /*" akku_R: "+ akku_R + " akku_L: " + akku_L + */") \n");
	}

	public int getSpalte() {
		return spalte;
	}

	public void setSpalte(int spalte) {
		this.spalte = spalte;
	}

	public ArrayList<Knoten> getKnoten() {
		return knoten;
	}

	public void setKnoten(Knoten k) {
		knoten.add(k);
	}

}
