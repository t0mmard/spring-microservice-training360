package employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    public EmployeesService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    EmployeeMapper employeeMapper;

    AtomicLong idGenerator = new AtomicLong(0);

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
            new Employee(idGenerator.incrementAndGet(),"Géza"),
            new Employee(idGenerator.incrementAndGet(), "Mari"),
            new Employee(idGenerator.incrementAndGet(), "Góliát")
    )));

    public List<EmployeeDTO> listEmployees() {
        return employeeMapper.toDto(employees);
    }

    public List<EmployeeDTO> listEmployees(Optional<String> prefix) {
        return employeeMapper.toDto(employees
                .stream()
                .filter(x -> x.getName().startsWith(prefix.orElse("")))
                .collect(Collectors.toList()));
    }

    public EmployeeDTO getEmpById(Long id) {
        return employeeMapper.toDto(employees.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(()->
                new IllegalArgumentException("Employee not found: " + id)));
    }

    public EmployeeDTO createEmployee(CreateEmployeeCommand command) {
        var employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDTO updateEmployee(Long id, UpdateEmployeeCommand command) {
        var employee = employees.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow( ()->
                new IllegalArgumentException("Employee not found: " + id));
        employee.setName(command.getName());
        return employeeMapper.toDto(employee);
    }

    public void deleteEmployee(Long id) {
        var employee = employees.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow( ()->
            new IllegalArgumentException("Employee not found: " + id));
        employees.remove(employee);
    }
}
