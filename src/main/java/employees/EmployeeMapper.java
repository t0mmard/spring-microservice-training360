package employees;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDto(Employee emp);

    List<EmployeeDTO> toDto(List<Employee> emp);
}
