package np2015;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class Seq_Berechnung{

	/**
	 *sequenzielle Berechnung nach Vorlage aus der Projektbeschreibung!
	 * 
	 * @param allNodes
	 * @param ginfo
	 */
	public void seqBerechnung(List<Knoten> allNodes, GraphInfo ginfo){
		
		List<Knoten> copyList = new ArrayList<Knoten>();
		Iterator<Knoten> l = allNodes.iterator();
		while (l.hasNext()) {
			copyList.add(l.next());
		}	
		Iterator<Knoten> i = copyList.iterator();
		while (i.hasNext()) {
					Knoten actual = i.next();
					System.out.println("aktueller knoten in seqBerechnung: "+ actual);
					Knoten neighbor_oben = actual.get_neighbor_oben(allNodes);
					
					Knoten neighbor_unten= actual.get_neighbor_unten(allNodes);
					Knoten neighbor_rechts = actual.get_neighbor_rechts(allNodes);
					Knoten neighbor_links = actual.get_neighbor_links(allNodes);
					//System.out.println("oben "+neighbor_oben + " unten "+ neighbor_unten + " rechts " + neighbor_rechts + " links "+neighbor_links);
//					System.out.println("Rate für oben " +ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
//					System.out.println("Rate für unten " +ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
//					System.out.println("Rate für rechts " +ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
//					System.out.println("Rate für links " +ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
			
					
					// top
					double rate = actual.getCurrent_value()*ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top);
					if (neighbor_oben == null && rate > 0 ){
							// erzeugt neuen Knoten mit inizialem current_vallue 0.0
							neighbor_oben = actual.createNode(actual.getX(),actual.getY()-1, allNodes);
					}
					if (neighbor_oben != null){
						System.out.println("übergangsrate nach oben: "+ actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
						neighbor_oben.setAkku(neighbor_oben.getAkku() + actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
					
						actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Top));
					}
					// down
					rate = actual.getCurrent_value()*ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom);
					if (neighbor_unten == null && rate > 0 ){
						// erzeugt neuen Knoten mit inizialem current_vallue 0.0
						neighbor_unten = actual.createNode(actual.getX(),actual.getY()+1, allNodes);
					}
					if (neighbor_unten != null){
						System.out.println("übergangsrate nach unten: "+ actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
						neighbor_unten.setAkku(neighbor_unten.getAkku() + actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
						actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Bottom));
					}
					// right
					rate = actual.getCurrent_value()*ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right);
					if (neighbor_rechts == null && rate > 0){
						// erzeugt neuen Knoten mit inizialem current_vallue 0.0
						neighbor_rechts = actual.createNode(actual.getX()+1,actual.getY(), allNodes);
					}
					if (neighbor_rechts != null){
						System.out.println("übergangsrate nach rechts: "+ actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
						neighbor_rechts.setAkku(neighbor_rechts.getAkku() + actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
						actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Right));
					}
					//left
					rate = actual.getCurrent_value()*ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left);
					if (neighbor_links == null && rate > 0){
						// erzeugt neuen Knoten mit inizialem current_vallue 0.0
						neighbor_links = actual.createNode(actual.getX()-1,actual.getY(), allNodes);
					}
					if (neighbor_links != null){
						System.out.println("übergangsrate nach links: "+ actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
						neighbor_links.setAkku(neighbor_links.getAkku() + actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
						actual.setAkku(actual.getAkku()-actual.getCurrent_value()*
								ginfo.getRateForTarget(actual.getX(), actual.getY(), Neighbor.Left));
					}
					System.out.print("-----------------------------------------\n");
			}
		
		Iterator<Knoten> j = allNodes.iterator();
		while (j.hasNext()) {
				Knoten actual = j.next();
				actual.setCurrent_value(actual.getCurrent_value()+ actual.getAkku());
				actual.setAkku(0.0);
				
				
		}
		while(j.hasNext()){
			Knoten actual = j.next();
			if (actual.getCurrent_value() == 0){
				
			}
		}
	}
	
}
