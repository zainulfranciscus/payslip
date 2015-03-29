import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.model.payslip.Payslip;
import org.myob.repository.specification.EmployeeSpecification;
import org.myob.service.PayslipService;
import org.myob.service.builder.PayslipServiceBuilder;
import reader.ReaderImpl;


import java.util.List;

@RunWith(ConcordionRunner.class)
public class TestForPayslipServiceFixture extends AbstractFixture{

    public List<Payslip> payslip() throws Exception {

        PayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilder();
        payslipServiceBuilder.withReaderForEmployeeRepository(employeeReader);
        payslipServiceBuilder.withReaderForTaxRepository(taxReader);
        PayslipService payslipService = payslipServiceBuilder.build();

        return payslipService.createPayslips(payslipServiceBuilder.createEmployeeRepository().find(new EmployeeSpecification()));
    }

    public void cleanUpData() {
        taxReader = new ReaderImpl();
        cleanUpEmployeeData();
    }

    public void cleanUpEmployeeData() {
        employeeReader = new ReaderImpl();
    }


}