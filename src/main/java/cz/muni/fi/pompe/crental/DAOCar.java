package cz.muni.fi.pompe.crental;

import java.util.List;

/**
 *
 * @author jozef
 */
public interface DAOCar {
    void createCar(Car car);
   
    void deleteCar(Car car);
    
    void updateCar(Car car);
    
    List<Car> getAllCars();
    
    Car getCarById(Long id);
}
