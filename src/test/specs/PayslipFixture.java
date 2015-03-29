import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.service.builder.AbstractPayslipServiceBuilder;
import org.myob.repository.specification.EmployeeSpecificationBuilder;
import org.myob.model.payslip.Payslip;
import org.myob.service.PayslipService;
import reader.ReaderImpl;
import service.PayslipServiceBuilderImpl;

import java.util.List;

@RunWith(ConcordionRunner.class)
public class PayslipFixture extends AbstractFixture{

    public List<Payslip> payslip() throws Exception {

        AbstractPayslipServiceBuilder payslipServiceBuilder = new PayslipServiceBuilderImpl();
        payslipServiceBuilder.withReaderForEmployeeRepository(employeeReader);
        payslipServiceBuilder.withReaderForTaxRepository(taxReader);
        PayslipService payslipService = payslipServiceBuilder.build();

        return payslipService.createPayslips(payslipServiceBuilder.createEmployeeRepository().find(new EmployeeSpecificationBuilder().build()));
    }

    public void cleanUpData() {
        taxReader = new ReaderImpl();
        cleanUpEmployeeData();
    }

    public void cleanUpEmployeeData() {
        employeeReader = new ReaderImpl();
    }


}