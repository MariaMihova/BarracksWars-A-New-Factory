package barracksWars.core.factories;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import barracksWars.models.units.AbstractUnit;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		try {
			Class<?> unit = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<?> constructor = unit.getDeclaredConstructor();
			Object object = constructor.newInstance();

			if(object instanceof AbstractUnit){
				return (Unit) object;
			}

		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
				| InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("Provided type is not a Unit");

	}

}
