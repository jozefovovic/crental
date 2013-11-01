/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pompe.crental.service;

import cz.muni.fi.pompe.crental.dao.DAOCar;
import cz.muni.fi.pompe.crental.dao.DAOEmployee;
import cz.muni.fi.pompe.crental.dao.DAORent;
import cz.muni.fi.pompe.crental.dao.DAORequest;
import cz.muni.fi.pompe.crental.dto.DTORent;
import cz.muni.fi.pompe.crental.entity.AccessRight;
import cz.muni.fi.pompe.crental.entity.Car;
import cz.muni.fi.pompe.crental.entity.Employee;
import cz.muni.fi.pompe.crental.entity.Rent;
import cz.muni.fi.pompe.crental.entity.Request;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author jozef
 */
public class RentServiceTest extends AbstractIntegrationTest{
    @Mock
    private DAORent daorent;
    @Mock
    private DAOEmployee daoemployee;
    @Mock
    private DAORequest daorequest;
    @Mock
    private DAOCar daocar;
    
    @InjectMocks
    @Autowired
    private RentService rentservice;
    
    private Car car;
    private Employee employee;
    private Request request;
    private Rent rent;
    private DTORent dtorent;
    
    public RentServiceTest(){
        car = new Car();
        car.setCarType("type");
        car.setEvidencePlate("plate");
        car.setId(1l);
        
        employee = new Employee();
        employee.setAccessRight(AccessRight.Admin);
        employee.setId(1l);
        employee.setName("name");
        employee.setPassword("password");
        
        request = new Request();
        request.setDateFrom(new Date(0));
        request.setDateTo(new Date(1));
        request.setDescription("description");
        request.setEmployee(employee);
        request.setId(1l);
       
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(car).when(daocar).getCarById(anyLong());
        doReturn(employee).when(daoemployee).getEmployeeById(anyLong());
        doReturn(request).when(daorequest).getRequestById(anyLong());
        
        rent = new Rent();
        rent.setConfirmedAt(new Date(2));
        rent.setConfirmedBy(employee);
        rent.setRentedCar(car);
        rent.setRequest(request);
        
        dtorent = new DTORent();
        dtorent.setConfirmedAt(new Date(2));
        dtorent.setConfirmedById(1l);
        dtorent.setRentedCarId(1l);
        dtorent.setRequestId(1l);
    }
    
    @Test
    public void createRentTest() {
        rentservice.createRent(dtorent);
        verify(daorent).createRent(rent);
        
        dtorent.setId(Long.MIN_VALUE);
        rent.setId(Long.MIN_VALUE);
        doThrow(new DataIntegrityViolationException("fail")).when(daorent).createRent(rent);
        
        try{
            rentservice.createRent(dtorent);
        }catch(DataAccessException ex){
            //ok
        }
    }
    
    @Test
    public void updateRentTest() {
        dtorent.setId(1l);
        rent.setId(1l);
        rentservice.updateRent(dtorent);
        verify(daorent).updateRent(rent);
        
        dtorent.setId(null);
        rent.setId(null);
        doThrow(new DataIntegrityViolationException("fail")).when(daorent).updateRent(rent);
        
        try{
            rentservice.updateRent(dtorent);
        }catch(DataAccessException ex){
            //ok
        }
    }
    
    @Test
    public void deleteRentTest(){
        dtorent.setId(1l);
        rentservice.deleteRent(dtorent);
        verify(daorent).deleteRent(1l);
        
        dtorent.setId(null);
        doThrow(new DataIntegrityViolationException("fail")).when(daorent).deleteRent(1l);
        
        try{
            rentservice.deleteRent(dtorent);
        }catch(DataAccessException ex){
            //ok
        }
    }
    
    @Test
    public void getRentByIdTest() {
        rentservice.getRentById(1l);
        verify(daorent).getRentById(1l);
        
        rent.setId(Long.MIN_VALUE);
        dtorent.setId(Long.MIN_VALUE);
        doReturn(rent).when(daorent).getRentById(anyLong());
        assertEquals(dtorent, rentservice.getRentById(Long.MIN_VALUE));
        
        doReturn(null).when(daorent).getRentById(1l);
        assertNull(rentservice.getRentById(1l));
    }
    
    @Test
    public void getAllRentsTest() {
        rentservice.getAllRents();
        verify(daorent).getAllRents();
        
        List<Rent> result = new ArrayList<>();
        result.add(rent);
        
        List<DTORent> serviceResult = new ArrayList<>();
        serviceResult.add(dtorent);
        
        doReturn(result).when(daorent).getAllRents();
        
        assertEquals(serviceResult.size(), rentservice.getAllRents().size());
        assertEquals(serviceResult.get(0), rentservice.getAllRents().get(0));
    }
}