import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.accounting.model.payslip.Payslip;
import org.accounting.repository.specification.SpecificationForReadingEmployeeData;
import org.accounting.service.PayslipService;
import org.accounting.service.PayslipServiceBuilder;
import reader.InMemoryReader;


import java.util.List;

@RunWith(ConcordionRunner.class)
public class TestForPayslipServiceFixture extends AbstractFixture{

    public List<Payslip> payslip() throws Exception {

        PayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilder();
        payslipServiceBuilder.withReaderForEmployeeRepository(employeeReader);
        payslipServiceBuilder.withReaderForTaxRepository(taxReader);
        PayslipService payslipService = payslipServiceBuilder.build();

        return payslipService.createPayslips(payslipServiceBuilder.createEmployeeRepository().find(new SpecificationForReadingEmployeeData()));
    }

    public void cleanUpData() {
        taxReader = new InMemoryReader();
        cleanUpEmployeeData();
    }

    public void cleanUpEmployeeData() {
        employeeReader = new InMemoryReader();
    }


}