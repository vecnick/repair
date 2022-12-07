package mirea.bd.services;

import mirea.bd.models.Employee;
import mirea.bd.models.Status;
import mirea.bd.repositories.EmployeesRepository;
import mirea.bd.repositories.StatusesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeesService {
    private final EmployeesRepository employeesRepository;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> findAll(){
        return employeesRepository.findAll();
    }

    public Employee findOne(int id){
        Optional<Employee> foundEmployee =  employeesRepository.findById(id);
        return foundEmployee.orElse(null);
    }

    @Transactional
    public void save(Employee employee){
        employeesRepository.save(employee);
    }

    @Transactional
    public void update(int id, Employee updatedEmployee){
        updatedEmployee.setId(id);
        employeesRepository.save(updatedEmployee);
    }

    @Transactional
    public void delete(int id){
        employeesRepository.deleteById(id);
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }
}
