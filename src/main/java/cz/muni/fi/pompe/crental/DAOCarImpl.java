package cz.muni.fi.pompe.crental;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Roman Stehlik
 */
public class DAOCarImpl implements DAOCar {

    private EntityManagerFactory emf;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    //Creates new car
    public void createCar(Car car) {
        EntityManager em = emf.createEntityManager();
        checkCar(car);
        try {
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteCar(Car car) {
        checkEntityManagerFactory();
        checkCar(car);
        EntityManager em = emf.createEntityManager();

        try {
            Car newCar = em.find(Car.class, car.getId());

            em.getTransaction().begin();
            em.remove(newCar);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateCar(Car car) {
        checkEntityManagerFactory();
        checkCar(car);
        EntityManager em = emf.createEntityManager();

        try {
            Car newCar = em.find(Car.class, car.getId());

            em.getTransaction().begin();
            newCar.setCarType(car.getCarType());
            newCar.setEvidencePlate(car.getEvidencePlate());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void checkEntityManagerFactory() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory emf was not set");
        }
    }

    @Override
    public List<Car> getAllCars() {
        checkEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Car.SelectAllCars", Car.class);
            List<Car> result = q.getResultList();
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public Car getCarById(Long id) {
        checkEntityManagerFactory();
        
        if(id == null){
            throw new NullPointerException("given id was null");
        }        
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createNamedQuery("Car.SelectCarById", Car.class);
            q.setParameter("id", id);
            return (Car) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    private void checkCar(Car car){
        if(car == null){
            throw new NullPointerException("car was null");
        }
        
        if(car.getCarType() == null || car.getCarType().isEmpty()){
            throw new IllegalArgumentException("carType cannt be null or empty");
        }
        
        if(car.getEvidencePlate()== null || car.getEvidencePlate().isEmpty()){
            throw new IllegalArgumentException("evidencePlate cannt be null or empty");
        }
    }

//    public Car getCarByLicencePlate(String licencePlate) {
//        checkEntityManagerFactory();
//        EntityManager em = emf.createEntityManager();
//        try {
//            Query q = em.createNamedQuery("Car.SelectCarByLicencePlate", Car.class);
//
//            return (Car) q.getSingleResult();
//        } finally {
//            em.close();
//        }
//    }
}
