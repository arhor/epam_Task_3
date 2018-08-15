package by.epam.training.runner;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.training.model.Bus;
import by.epam.training.model.Station;

public class Runner {
	
	private static final Logger LOG = LogManager.getLogger(Runner.class);

	public static void main(String[] args) {
		//only for test purpose
		
		List<Station> route = new ArrayList<>();
		
		route.add(new Station("Yanki Luchiny"));
		route.add(new Station("School N40"));
		route.add(new Station("vulica Hashkevicha"));
		route.add(new Station("zavulak Chyrzeuskih"));
		route.add(new Station("Igumenski trakt"));
		
		List<Bus> busDepot = new ArrayList<>();
		
		for (int i = 0; i < 5; i ++) {
			busDepot.add(new Bus(i + 1));
			busDepot.get(i).setRoute(route);
		}
		
		int before = 0;
		for (Station st : route) {
			before += st.getWaiting();
		}
		
		LOG.info("Total before: " + before + "\n");
		
		for (Bus bus : busDepot) {
			bus.start();
		}
		
		try {
			for (Bus bus : busDepot) {
				bus.join();
			}
		} catch (InterruptedException e) {
			LOG.error("Interrupted exception occured", e);
		}
		int total = 0;
		for (Station st : route) {
			total += st.getWaiting();
		}
		for (Bus bus : busDepot) {
			total += bus.getPassengers();
		}
		LOG.info("Total after: " + total);
	}
}
