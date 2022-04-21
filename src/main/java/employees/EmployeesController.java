package employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> listEmployees(@RequestParam("prefix") Optional<String> prefix){
        return ResponseEntity.ok(employeesService.listEmployees(prefix));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> listEmployeesWithId(@PathVariable("id") Long id){
        return ResponseEntity.ok(employeesService.getEmpById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmploye(@RequestBody CreateEmployeeCommand command){
        return new ResponseEntity<>(employeesService.createEmployee(command), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long id, @RequestBody UpdateEmployeeCommand command){
        return new ResponseEntity<>(employeesService.updateEmployee(id, command), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {
        employeesService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
