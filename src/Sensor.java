import java.util.ArrayList;

//Creator (SensorCreator)

abstract class SensorCreator {

	abstract public Sensor createSensor();
//	public ArrayList<Sensor> sensors;

}

//Concrete Creators

class PollutionCreator extends SensorCreator {
	
	public Sensor createSensor() {
		PollutionSensor sensor = new PollutionSensor("Pollution Sensor", false, 0);
//		sensors.add(sensor);
		return sensor;
	}
	
	public static PollutionCreator getInstance() {
		if (uniqueInstance == null) {

			uniqueInstance = new PollutionCreator();

		}
		return uniqueInstance;

	}

	private static PollutionCreator uniqueInstance = null;
	

}

class TemperatureCreator extends SensorCreator {
	
	public Sensor createSensor() {
		TemperatureSensor sensor = new TemperatureSensor("Temperature Sensor", false, 0);
//		sensors.add(sensor);
		return sensor;
	}
	
	public static TemperatureCreator getInstance() {
		if (uniqueInstance == null) {

			uniqueInstance = new TemperatureCreator();

		}
		return uniqueInstance;

	}

	private static TemperatureCreator uniqueInstance = null;
	

}

class CongestionCreator extends SensorCreator {
	
	public Sensor createSensor() {
		CongestionSensor sensor = new CongestionSensor("Congestion Sensor", false, 0);
//		sensors.add(sensor);
		return sensor;
	}
	
	public static CongestionCreator getInstance() {
		if (uniqueInstance == null) {

			uniqueInstance = new CongestionCreator();

		}
		return uniqueInstance;

	}

	private static CongestionCreator uniqueInstance = null;
	

}

class NoiseCreator extends SensorCreator {
	
	public Sensor createSensor() {
		
		NoiseSensor sensor = new NoiseSensor("Noise Sensor", false, 0);
//		sensors.add(sensor);
		return sensor;
	}
	
	public static NoiseCreator getInstance() {
		if (uniqueInstance == null) {

			uniqueInstance = new NoiseCreator();

		}
		return uniqueInstance;

	}

	private static NoiseCreator uniqueInstance = null;
	
	

}

// Concrete Creators Ends

// Concrete Products

class PollutionSensor extends Sensor {	
	
	public PollutionSensor(String sensorName, boolean sensorStatus, int malfunctionCount) {
		super(sensorName, sensorStatus, malfunctionCount);
		sensorName = new String("Pollution Sensor");
		System.out.println("Pollution Sensor is created..."); 
	}
	public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class TemperatureSensor extends Sensor {	
	public TemperatureSensor(String sensorName, boolean sensorStatus, int malfunctionCount) {
		super(sensorName, sensorStatus, malfunctionCount);
		sensorName = new String("Temperature Sensor");
		System.out.println("Temperature Sensor is created..."); 
	}
	public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class CongestionSensor extends Sensor {	
	public CongestionSensor(String sensorName, boolean sensorStatus, int malfunctionCount) {
		super(sensorName, sensorStatus, malfunctionCount);
		sensorName = new String("Congestion Sensor");
		System.out.println("Congestion Sensor is created..."); 
	}
	public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class NoiseSensor extends Sensor {	
	public NoiseSensor(String sensorName, boolean sensorStatus, int malfunctionCount) {
		super(sensorName, sensorStatus, malfunctionCount);
		sensorName = new String("Noise Sensor");
		System.out.println("Noise Sensor is created..."); 
	}
	public void Accept(Visitor visitor) {visitor.Visit(this);}
}

// Concrete Products Ends

// Composite
// Component

interface Neigborhood {
	void Add(Neigborhood n);
	void Remove(Neigborhood n);
	void Display(int indent);
	public String getName();
}

// Leaf 1

class Pole implements Neigborhood {
	
	private String name;
	public String getName() { return name;}
	public Pole(String name) {
		this.name = name;
		}
	public void Add(Neigborhood c) {
		System.out.println("Cannot add to a PrimitiveElement.");
	}
	public  void Remove(Neigborhood c) {
		System.out.println("Cannot remove from a PrimitiveElement.");
	}
	public void InstallSensor() {
		SensorCreator creator = new PollutionCreator();
		SensorCreator creator2 = new TemperatureCreator();
		SensorCreator creator3 = new CongestionCreator();
		SensorCreator creator4 = new NoiseCreator();
		creator.createSensor();
		creator2.createSensor();
		creator3.createSensor();
		creator4.createSensor();
	}
	@Override
	public void Display(int indent) {
		for(int i = 1;i <= indent;i++) 	System.out.print("-");
		System.out.println(" "  + name);
	}
	
	
	
}

// Leaf 2

class Apartment implements Neigborhood {
	
	public String name;
	public String getName() { return name;}
	public Apartment(String name) {
		this.name = name;
		}
	
	public void InstallSensor() {
		SensorCreator creator = new PollutionCreator();
		SensorCreator creator2 = new TemperatureCreator();
		SensorCreator creator3 = new CongestionCreator();
		SensorCreator creator4 = new NoiseCreator();
		creator.createSensor();
		creator2.createSensor();
		creator3.createSensor();
		creator4.createSensor();
	}
	
	public void Add(Neigborhood d) {
		System.out.println("Cannot add to a PrimitiveElement.");
	}
	public  void Remove(Neigborhood d) {
		System.out.println("Cannot remove from a PrimitiveElement.");
	}
	@Override
	public void Display(int indent) {
		for(int i = 1;i <= indent;i++) 	System.out.print("-");
		System.out.println(" "  + name);
	}
	
}

// Composite

class Street implements Neigborhood {
	private String name;
	public String getName() { return name;}
	public Street(String name) {this.name = name;}
	public void Add(Neigborhood e) {elements.add(e);};
	public void Remove(Neigborhood e) {
		for (int i = 0; i< elements.size(); i++) {
			if (elements.get(i).getName() == e.getName()) {
				elements.remove(i);
				return;
			}
		}
	}
	public void Display(int indent) {
		for(int i = 1;i <= indent;i++) System.out.print("-");
		System.out.println( "+ " + getName());

		// Display each child element on this node
		for (int i= 0; i< elements.size(); i++) {
			elements.get(i).Display(indent+2);
		}
	}
	private	ArrayList<Neigborhood> elements = new ArrayList<Neigborhood>();
}

// Composite Pattern Ends

// Visitor Pattern

interface Element {
	public void Accept(Visitor visitor);
}

interface Visitor {
	public void Visit(TemperatureSensor element);
	public void Visit(PollutionSensor element);
	public void Visit(NoiseSensor element);
	public void Visit(CongestionSensor element);
}

class DataMonitoringDivision {
	public void Add(Sensor sensor){ sensors.add(sensor);};
	public void Accept(Visitor visitor) {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < sensors.size(); i++) {
			sensors.get(i).Accept(visitor);		}
	}
	private ArrayList<Sensor> sensors = new ArrayList<Sensor>();
}

class Malfunction implements Visitor {
	public void Visit(PollutionSensor element){
		
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());
		
	}
	public void Visit(TemperatureSensor element){
		
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());
		
	}
	public void Visit(CongestionSensor element){
		
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());
		
	}
	public void Visit(NoiseSensor element){
		
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());
		
	}
}

abstract public class Sensor implements Element {
	
	protected String sensorName; 
	private boolean sensorStatus; 
	private int malfunctionCount; 
	public String displaySensorName() { return sensorName;	} 
	
	public Sensor(String sensorName, boolean sensorStatus, int malfunctionCount) {
		this.sensorName = sensorName;
		this.sensorStatus = sensorStatus;
		this.malfunctionCount = malfunctionCount;
	}
	public boolean getSensorStatus() {return sensorStatus;};
	public void setSensorStatus(boolean value) {sensorStatus = value;};
	
	public int getMalfunctionCount() {return malfunctionCount;};
	public void setMalfunctionCount(int value) {malfunctionCount = value;};
	
	public static void main(String[] args) {
		
		Neigborhood root = new Street("Prof. Dr. Yusuf Vardar Sk.");
		root.Add(new Pole("Best Pole ever Made"));
		root.Add(new Apartment("Folkart Apt."));
		
		DataMonitoringDivision e = new DataMonitoringDivision();
		e.Add(new NoiseSensor("NoiseSensor1", false, 3));
		e.Add(new CongestionSensor("CongestionSensor1", false, 2));
		e.Add(new PollutionSensor("PollutionSensor1", true, 1));
		e.Add(new TemperatureSensor("TemperatureSensor1", true, 4));

		Neigborhood comp = new Street("Gul Sk.");
		Apartment apt = new Apartment("Firuz Apt.");
		comp.Add(apt);
		Pole pole = new Pole("Worst Pole Ever Made");
		comp.Add(pole);
		root.Add(comp);
		apt.InstallSensor();
		pole.InstallSensor();

		// Add and remove a PrimitiveElement
		Neigborhood pe = new Apartment("TESLA Apt.");
		pe.Add(new Pole("Only Pole"));
		root.Add(pe);
		root.Remove(pe);

		// Recursively display nodes
		root.Display(1);
		e.Accept(new Malfunction());


	}

}
