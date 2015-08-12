package np2015;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



// verwaltet alle Spalten
public class Matrix extends Thread{
	
	public ArrayList<Spalten> alleSpalten = new ArrayList<Spalten>();
	public static GraphInfo ginfo;
	public static int height;
	public static int width;

	public static int iterationen;
	
	//Konstruktor
	public Matrix(GraphInfo ginfo, int iterationen){	
		Matrix.ginfo = ginfo;
		Matrix.height = ginfo.height;
		Matrix.width = ginfo.width;
		Matrix.iterationen = iterationen;
	}
	/**
	 * spalten starten,
	 * solange es nicht konvergiert weitermachen
	 * austauschen aufuren
	 * */
	
	public void initializeMatrix(Spalten initial){
//		while(true){
			alleSpalten.add(initial);
			startSpalten();

		//	organisiereaustauschAkkus();

	}
	
	
	public void organisiereAustauschAkkus(){
	/*	Iterator<Spalten> spaltenIt = alleSpalten.iterator();
		int i = 0;
		while (spaltenIt.hasNext()) {
			Spalten currentSpalte = spaltenIt.next();

			*/
			Iterator<Knoten> knotenIt = NPOsmose.allNodes.iterator();
			while(knotenIt.hasNext()){
				Knoten currentKnoten = knotenIt.next();				
				austauschAkkus(currentKnoten);
			}
		}
		
	public void austauschAkkus(Knoten k){
		
		//links
		Knoten n = k.get_neighbor_links(NPOsmose.allNodes);
		if (n == null){
			create_new_node(k.getX()-1, k.getY());
		}
		
		//TODO: info an mich: aufpassen das hier niemals versucht wird einen wert an einen knoten zugeben, der auserhalb des feldes liegt
		double rate = k.getAkkuL();
		n.setAkku(rate);
		k.setOutflow(k.getOutflow()+rate);
		n.setInflow(n.getInflow()+rate);
		//rechts
		n = k.get_neighbor_rechts(NPOsmose.allNodes);
		if (n == null){
			create_new_node(n.getX()+1, n.getY());
		}
		rate = k.getAkkuL();
		n.setAkku(rate);
		k.setOutflow(k.getOutflow()+rate);
		n.setInflow(n.getInflow()+rate);
	}
	
	// erzeugt wenn möglich neuen Knoten und trägt ihn passend überall ein
	public Knoten create_new_node(int x, int y){
			if ( x <= Matrix.height && x >= 0 && y <= Matrix.width && y >= 0){
					Knoten new_node = new Knoten(x, y, 0.0, NPOsmose.allNodes);
					// wird geschaut, ob passende spalte existiert, wenn ja wird Wert eingetragen
					for ( Spalten s : alleSpalten){
							if (s.getSpalte() == x){
								s.setKnoten(new_node);
								return new_node;
							}
					}
					// wenn nicht wird spalte erzeugt und der wert dann eingetragen
					Spalten new_column = new Spalten (x); 
					new_column.setKnoten(new_node);
						return new_node;
				}
			return null;
			}
	
		//TODO akku vom link und rechts ändern	
	
	static Runnable runSpalten = new Runnable(){
		public void run(){
			System.out.println("noch passiert hier gar nichts");
			
			for (int i = 0; i< Matrix.iterationen; i++){
				 
				
				for ( Spalten s : alleSpalten){
					
				}
				
				
			}
		}	
	};
	
	// wo ist die run-funktion?
	public void startSpalten(){


		System.out.println("alle S " +alleSpalten);
		//List<Spalten> spaltenList = new ArrayList<Spalten>(); // diese liste ist immer leer!?

		Iterator<Spalten> spaltenIt = alleSpalten.iterator();
		while (spaltenIt.hasNext()) {
			Thread threadSpalten = spaltenIt.next();
			threadSpalten.start(); 
		}	
	}
	
	
	public GraphInfo getGinfo() {
		return ginfo;
	}
	public void setGinfo(GraphInfo ginfo) {
		this.ginfo = ginfo;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Matrix.height = height;
	}
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Matrix.width = width;
	}
	public int getIterationen() {
		return iterationen;
	}
	public void setIterationen(int iterationen) {
		this.iterationen = iterationen;
	}
	public void addSpalte(Spalten p){
		ArrayList<Spalten> alleSpalten = getAlleSpalten();
		alleSpalten.add(p);
	}


	public ArrayList<Spalten> getAlleSpalten() {
		return alleSpalten;
	}


	public void setAlleSpalten(ArrayList<Spalten> alleSpalten) {
		this.alleSpalten = alleSpalten;
	}
	
	
	public String toString(){
		return("(" + getAlleSpalten()+ ")" );
	}

	
	
	
	
}
