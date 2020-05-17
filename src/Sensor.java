import java.util.ArrayList;

//Creator (SensorCreator)

abstract class SensorCreator {

	abstract public Sensor createSensor();
//	public ArrayList<Sensor> sensors;

}

//Concrete Creators

class PollutionCreator extends SensorCreator {

	public Sensor createSensor() {
		PollutionSensor sensor = new PollutionSensor("Pollution Sensor", false, "AQI", 105.7);
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
		TemperatureSensor sensor = new TemperatureSensor("Temperature Sensor", false, "C°", 45.3);
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
		CongestionSensor sensor = new CongestionSensor("Congestion Sensor", false, "km/h", 100);
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

		NoiseSensor sensor = new NoiseSensor("Noise Sensor", false, "dB", 75);
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

	public PollutionSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue) {
		super(sensorName, sensorStatus, unit, sensorValue);
		sensorName = new String("Pollution Sensor");
		unit = new String("AQI");
		System.out.println("Pollution Sensor is created...");

	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (sensorValue > 100) {
			NotifyCitizen();
		}
		System.out.println(displaySensorName() + "is above 100" + getUnit() + ", current value is: " + getSensorValue()
				+ getUnit());

	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (sensorStatus == false) {
			NotifyEngineer();
			System.out.println(displaySensorName() + " is down!!!!");
		}
	}
}

class TemperatureSensor extends Sensor {
	public TemperatureSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue) {
		super(sensorName, sensorStatus, unit, sensorValue);
		sensorName = new String("Temperature Sensor");
		unit = new String("C°");
		System.out.println("Temperature Sensor is created...");

	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (sensorValue < 0) {
			NotifyCitizen();
			System.out.println(displaySensorName() + "is below 0" + getUnit() + ", current value is: "
					+ getSensorValue() + getUnit());
		}

	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (sensorStatus == false) {
			NotifyEngineer();
			System.out.println(displaySensorName() + " is down!!!!");
		}
	}
}

class CongestionSensor extends Sensor {
	public CongestionSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue) {
		super(sensorName, sensorStatus, unit, sensorValue);
		sensorName = new String("Congestion Sensor");
		unit = new String("km/h");
		System.out.println("Congestion Sensor is created...");

	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (sensorValue < 10) {
			NotifyCitizen();
			System.out.println(displaySensorName() + "is below 10" + getUnit() + ", current value is: "
					+ getSensorValue() + getUnit());

		}

	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (sensorStatus == false) {
			NotifyEngineer();
			System.out.println(displaySensorName() + " is down!!!!");
		}
	}
}

class NoiseSensor extends Sensor {
	public NoiseSensor(String sensorName, boolean sensorStatus, String unit, double sensorValue) {
		super(sensorName, sensorStatus, unit, sensorValue);
		sensorName = new String("Noise Sensor");
		unit = new String("dB");
		System.out.println("Noise Sensor is created...");

	}

	public void Accept(Visitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public void sensorCalculate(double sensorValue) {
		if (sensorValue > 85) {
			NotifyCitizen();
			System.out.println(displaySensorName() + "is above 85" + getUnit() + ", current value is: "
					+ getSensorValue() + getUnit());
		}

	}

	@Override
	public void checkSensorStatus(boolean sensorStatus) {
		if (sensorStatus == false) {
			NotifyEngineer();
			System.out.println(displaySensorName() + " is down!!!!");
		}
	}
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

	public String getName() {
		return name;
	}

	public Pole(String name) {
		this.name = name;
	}

	public void Add(Neigborhood c) {
		System.out.println("Cannot add to a PrimitiveElement.");
	}

	public void Remove(Neigborhood c) {
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
		for (int i = 1; i <= indent; i++)
			System.out.print("-");
		System.out.println(" " + name);
	}

}

// Leaf 2

class Apartment implements Neigborhood {

	public String name;

	public String getName() {
		return name;
	}

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

	public void Remove(Neigborhood d) {
		System.out.println("Cannot remove from a PrimitiveElement.");
	}

	@Override
	public void Display(int indent) {
		for (int i = 1; i <= indent; i++)
			System.out.print("-");
		System.out.println(" " + name);
	}

}

// Composite

class Street implements Neigborhood {
	private String name;

	public String getName() {
		return name;
	}

	public Street(String name) {
		this.name = name;
	}

	public void Add(Neigborhood e) {
		elements.add(e);
	};

	public void Remove(Neigborhood e) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getName() == e.getName()) {
				elements.remove(i);
				return;
			}
		}
	}

	public void Display(int indent) {
		for (int i = 1; i <= indent; i++)
			System.out.print("-");
		System.out.println("+ " + getName());

		// Display each child element on this node
		for (int i = 0; i < elements.size(); i++) {
			elements.get(i).Display(indent + 2);
		}
	}

	private ArrayList<Neigborhood> elements = new ArrayList<Neigborhood>();
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
	public void Add(Sensor sensor) {
		sensors.add(sensor);
	};

	public void Accept(Visitor visitor) {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < sensors.size(); i++) {
			sensors.get(i).Accept(visitor);
		}
	}

	private ArrayList<Sensor> sensors = new ArrayList<Sensor>();
}

class Malfunction implements Visitor {
	static int malfunctionCounter;

	public void Visit(PollutionSensor element) {

		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}

	}

	public void Visit(TemperatureSensor element) {

		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}

	}

	public void Visit(CongestionSensor element) {

		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}

	}

	public void Visit(NoiseSensor element) {

		boolean sensorStatus = element.getSensorStatus();
		element.setSensorStatus(element.getSensorStatus());
		System.out.println(element.getSensorStatus());

		if (sensorStatus == false) {
			malfunctionCounter++;
		}

	}
}

//'Observer'  ==> Abstract Observer.

interface Observer {
	public void Update(Sensor sensor);
}

class Citizen implements Observer {
	private Sensor _sensor;
	private String _citizen_name;
	private boolean _sensor_status;
	private String _sensor_name;
	private double _sensor_value;
	private String _sensor_unit;

	public Citizen(String name) {
		_citizen_name = name;
	}

	public void Update(Sensor sensor) {
		_sensor = sensor;
		_sensor_name = sensor.displaySensorName();
		_sensor_status = sensor.getSensorStatus();
		_sensor_value = sensor.getSensorValue();
		_sensor_unit = sensor.getUnit();
		System.out.println("Notified " + _citizen_name + " of " + _sensor_name + "'s " + "change to " + _sensor_value
				+ " " + _sensor_unit);
	}

	public Sensor getSensor() {
		return _sensor;
	}

	public void setSensor(Sensor value) {
		_sensor = value;
	}

	public String getCitizenName() {
		return _citizen_name;
	}
}

class Engineer implements Observer {
	private Sensor _sensor;
	private String _engineer_name;
	private boolean _sensor_status;
	private String _sensor_name;
	private double _sensor_value;
	private String _sensor_unit;

	public Engineer(String name) {
		_engineer_name = name;
	}

	public void Update(Sensor sensor) {
		_sensor = sensor;
		_sensor_name = sensor.displaySensorName();
		_sensor_status = sensor.getSensorStatus();
		_sensor_value = sensor.getSensorValue();
		_sensor_unit = sensor.getUnit();
		System.out
				.println("Notified " + _engineer_name + " of " + _sensor_name + "'s " + "change to " + _sensor_status);
	}

	public boolean Reset() {
		if (_sensor_status == false) {
			_sensor_status = true;
			System.out.println("Sensor has been reset");
		} else {
			System.out.println("Sensor is already working");
		}
		return _sensor_status;
	}

	public Sensor getSensor() {
		return _sensor;
	}

	public void setSensor(Sensor value) {
		_sensor = value;
	}

	public String getEngineerName() {
		return _engineer_name;
	}
}

abstract public class Sensor implements Element {

	protected String sensorName;
	private boolean sensorStatus;
	private String unit;
	private double sensorValue;

	abstract public void sensorCalculate(double sensorValue);

	abstract public void checkSensorStatus(boolean sensorStatus);

	public String displaySensorName() {
		return sensorName;
	}

	public Sensor(String sensorName, boolean sensorStatus, String unit, double sensorValue) {
		this.sensorName = sensorName;
		this.sensorStatus = sensorStatus;
		this.unit = unit;
		this.sensorValue = sensorValue;
	}

	public boolean getSensorStatus() {
		return sensorStatus;
	};

	public void setSensorStatus(boolean value) {
		sensorStatus = value;
		NotifyEngineer();
	};

	public double getSensorValue() {
		return sensorValue;
	};

	public void setSensorValue(double value) {
		sensorValue = value;
	};

	public String getUnit() {
		return unit;
	};

	public void setUnit(String value) {
		unit = value;
	};

	// Register the Observers
	public void AttachCitizen(Citizen citizen) {
		citizens.add(citizen);
	}

	// Unregister from the list of Observers.
	public void DetachCitizen(Citizen citizen) {
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.get(i).getCitizenName() == citizen.getCitizenName()) {
				citizens.remove(i);
				return;
			}
		}
	}

	// Notify the Observers.
	public void NotifyCitizen() {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < citizens.size(); i++) {
			citizens.get(i).Update(this);
		}
	}

	// Register the Observers
	public void AttachEngineer(Engineer engineer) {
		engineers.add(engineer);
	}

	// Unregister from the list of Observers.
	public void DetachCitizen(Engineer engineer) {
		for (int i = 0; i < engineers.size(); i++) {
			if (engineers.get(i).getEngineerName() == engineer.getEngineerName()) {
				engineers.remove(i);
				return;
			}
		}
	}

	// Notify the Observers.
	public void NotifyEngineer() {
		// set argument to something that helps
		// tell the Observers what happened
		for (int i = 0; i < engineers.size(); i++) {
			engineers.get(i).Update(this);
		}
	}

	protected ArrayList<Engineer> engineers = new ArrayList<Engineer>();
	protected ArrayList<Citizen> citizens = new ArrayList<Citizen>();

	public static void main(String[] args) {

		Neigborhood root = new Street("Prof. Dr. Yusuf Vardar Sk.");
		root.Add(new Pole("Best Pole ever Made"));
		root.Add(new Apartment("Folkart Apt."));

		DataMonitoringDivision e = new DataMonitoringDivision();
		e.Add(new NoiseSensor("NoiseSensor1", false, "dB", 89));
		e.Add(new CongestionSensor("CongestionSensor1", false, "km/h", 4));
		e.Add(new PollutionSensor("PollutionSensor1", true, "AQI", 80));
		e.Add(new TemperatureSensor("TemperatureSensor1", true, "C", 32.1));

		Citizen ctz = new Citizen("Naz");
		Engineer eng = new Engineer("Erdem");

		PollutionSensor sensor1 = new PollutionSensor("PollutionSensor1", true, "AQI", 120);
		NoiseSensor sensor2 = new NoiseSensor("NoiseSensor1", false, "dB", 95);
		CongestionSensor sensor3 = new CongestionSensor("CongestionSensor1", false, "km/h", 1);
		TemperatureSensor sensor4 = new TemperatureSensor("TemperatureSensor1", true, "C", -7);

		sensor1.AttachCitizen(ctz);
		sensor2.AttachEngineer(eng);
		sensor3.AttachEngineer(eng);
		sensor4.AttachCitizen(ctz);

		eng.Reset();
		sensor1.setSensorStatus(false);
		sensor2.setSensorStatus(true);
		sensor3.setSensorStatus(true);
		sensor4.setSensorStatus(false);
		eng.Reset();

		sensor1.checkSensorStatus(true);
		sensor1.sensorCalculate(100);
		sensor2.sensorCalculate(90);
		sensor3.sensorCalculate(8);
		sensor4.sensorCalculate(-40);

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
		System.out.println("Malfunction number is " + Malfunction.malfunctionCounter);

	}

}
