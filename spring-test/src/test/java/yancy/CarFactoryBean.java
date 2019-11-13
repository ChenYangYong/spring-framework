package yancy;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;

public class CarFactoryBean implements FactoryBean<Car> {
	private String carInfo;
	@Override
	public Car getObject() throws Exception {
		Car car = new Car();
		if(StringUtils.hasText(carInfo)){
			String[] infos = carInfo.split(",",-1);
			if(infos.length>=2){
				car.setBrand(infos[0]);
				car.setMaxSpeed(Integer.valueOf(infos[1]));
				car.setPrice(Double.valueOf(infos[2]));
			}
		}
		return car;
	}

	@Override
	public Class<Car> getObjectType() {
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
}
