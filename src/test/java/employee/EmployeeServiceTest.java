package employee;

import builder.TaxBuilder;
import builder.TaxCriteriaBuilder;
import payslip.MONTH;
import domain.Tax;
import employee.service.EmployeeService;
import employee.service.impl.EmployeeServiceImpl;
import payslip.EmployeePayslipFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import repository.Criteria;
import repository.TaxRepository;
import repository.impl.TaxCriteria;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Zainul Franciscus on 26/03/2015.
 */
public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private TaxRepository mockTaxRepository;
    private EmployeeBuilder employeeBuilder = new EmployeeBuilder();
    private Employee employee;

    @Before
    public void setup(){

        mockTaxRepository = mock(TaxRepository.class);
        employeeBuilder = new EmployeeBuilder();

        employeeService = new EmployeeServiceImpl();
        employeeService.setTaxRepository(mockTaxRepository);

        employee = employeeBuilder.withFirstName("Joe").withLastName("Blogg").withSalary(12000).build();

    }

    @Test
    public void shouldReturnAPayslipWithTheExpectedFullName() throws IOException {

        EmployeePayslip payslip = employeeService.payslip(MONTH.APRIL, employee);
        assertEquals(employeeBuilder.build().getFullName(), payslip.getEmployeeName());

    }

    @Test
    public void payslipShouldHaveGrossIncomeEqualToAnnualSalaryDividedBy12Months() throws IOException {

        Tax expectedTaxForThisEmployee = new TaxBuilder().withMaxIncome(20000).withMinIncome(10).withBaseTax(2500).build();
        TaxCriteria expectedCriteriaUsedByRepository = new TaxCriteriaBuilder().withEmployee(employee).build();

        EmployeePayslipFactory payslipFactory = new EmployeePayslipFactoryImpl();
        EmployeePayslip employeePayslip = payslipFactory.createWith(MONTH.APRIL,employee,expectedTaxForThisEmployee);

        when(mockTaxRepository.find(expectedCriteriaUsedByRepository)).thenReturn(expectedTaxForThisEmployee);

        EmployeePayslip payslip = employeeService.payslip(MONTH.APRIL,employee);

        assertEquals(employeePayslip.getGrossIncome(), payslip.getGrossIncome());
        verify(mockTaxRepository, times(1)).find(Mockito.any(Criteria.class));

    }



}
