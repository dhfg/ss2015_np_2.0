package np2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;

public class NPOsmose {
	
	public static List<Knoten > allNodes = new ArrayList<Knoten>(); // muss gelockt werden bei allen aufrufen!! 
																	//oder werden knoten nur sequenziell erstellT?
	

	public static void main(String[] args) throws IOException, InterruptedException {
		Gson gson = new Gson();
		String json = "";
		// read data in
		if (args.length != 0) {
			Path path = Paths.get(args[0]);
			try {
				json = new String(Files.readAllBytes(path)); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("You must provide the serialized file as the first argument!");
		}
		GraphInfo ginfo = gson.fromJson(json, GraphInfo.class);
		
		Matrix m = new Matrix(ginfo, 0);
		Set<Entry<Integer, HashMap<Integer, Double>>> e = ginfo.column2row2initialValue.entrySet();
		Iterator<Entry<Integer, HashMap<Integer, Double>>> it1 = e.iterator();
			Entry<Integer, HashMap<Integer, Double>> entry = it1.next();
			System.out.println(entry);
			System.out.println("key " +entry.getKey());
			System.out.println("value " +entry.getValue());
			int x = entry.getKey();
			
		HashMap<Integer, HashMap<Integer, Double>> hashMap = ginfo.column2row2initialValue;
		Set<Integer> set2 = entry.getValue().keySet();
		Iterator<Integer> it2 = set2.iterator();
			int y = it2.next();
			double d = hashMap.get(x).get(y);
			System.out.println("x "+ x + " y "+ y + " double "+ d);
			
//		ginfo.addInitialEntry(x, y, d);
/*		Spalten initial = new Spalten(x);
		Knoten k = new Knoten(x, y, d, allNodes);
		allNodes.add(k);
		initial.setKnoten(k);
		m.initializeMatrix(initial);
		

		Spalten s = new Spalten(0);
		Spalten s1 = new Spalten(1);
		Spalten s2 = new Spalten(2);

		Knoten mitte = new Knoten(2,2,1.0, allNodes);
		Knoten k1 = new Knoten(0,0,0.0, allNodes);
		Knoten k2 = new Knoten(0,1,0.0, allNodes);
		Knoten k3 = new Knoten(0,2,0.0, allNodes);
		Knoten k4 = new Knoten(1,0,0.0, allNodes);
		Knoten k5 = new Knoten(2,0,0.0, allNodes);
		s.setKnoten(k2);
		s.setKnoten(k3);		
		s.setKnoten(k1);
		s1.setKnoten(k4);
		s2.setKnoten(k5);
		s2.setKnoten(mitte);


	
		Matrix m = new Matrix(ginfo, 100); // initiale anzahl der iterationen hier gesetzt
		m.addSpalte(s);
		m.addSpalte(s1);
		m.addSpalte(s2);
		m.initializeMatrix();
/*
		// Your implementation can now access ginfo to read out all important values
		
		/**
		 * test für Knoten
		 */
		
		// muss auskommentiert sein für test der seq_berechnung!
		
	//	Knoten mitte = new Knoten(1,1,1.0, allNodes);
	//	Knoten oben = new Knoten(1,0,0.0, allNodes);
	//	Knoten unten = new Knoten(1,2,0.0, allNodes);
	//	Knoten links = new Knoten(0,1,0.0, allNodes);
	//	Knoten rechts = new Knoten(2,1,0.0, allNodes);
		
		//System.out.println(mitte.toString());
	//	System.out.println(oben.toString());
	//	System.out.println(unten.toString());
	//	System.out.println(links.toString());
	//	System.out.println(rechts.toString());	
		
		// muss auskommentiert sein für test der seq_berechnung!!
		
	//	Knoten result_oben = mitte.get_neighbor_oben(allNodes);
	//	Knoten result_unten =  mitte.get_neighbor_unten(allNodes);
	//	Knoten result_rechts = mitte.get_neighbor_rechts(allNodes);
	//	Knoten result_links =  mitte.get_neighbor_links(allNodes);
	//	System.out.println("oben " + result_oben) ;
	//	System.out.println("unten: " +result_unten);
	//	System.out.println("rechts " + result_rechts) ;
	//	System.out.println("links: " +result_links);
		

	//	Spalten s = new Spalten(0, 10);

	//	System.out.print(s.toString());
		
	//	s.set_knoten(mitte);
//		s.set_akku_L(1.0);
//		s.set_akku_R(1.0);
//		System.out.print(s.toString());
//		Seq_Berechnung b = new Seq_Berechnung();
		

		
		//TODO: nachvollziehen ob es auch wriklich das richtige tut!
		//	b.seqBerechnung(allNodes, ginfo);
		//	System.out.println("allNodes:  "+allNodes);

//		b.seqBerechnung(allNodes, ginfo);	
//		b.seqBerechnung(allNodes, ginfo);	

//		System.out.println("allNodes:  "+allNodes);

		//TODO: nachvollziehen ob es auch wriklich das richtige tut!
		//b.seqBerechnung(allNodes, ginfo);
		//System.out.println("allNodes:  "+allNodes);
		
	
		
		
		/**
		 * original ende der datei
		 */
	//	ImageConvertible graph = null; // <--- you should implement ImageConvertible to write the graph out
	//	ginfo.write2File("./result.txt", graph);
	}

}
