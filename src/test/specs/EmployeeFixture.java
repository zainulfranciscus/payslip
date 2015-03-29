import domain.PayslipEntity;
import domain.PayslipEntityBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.concordion.api.extension.Extensions;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.persistence.mapping.impl.PayslipHeader;
import org.myob.persistence.reader.FileReaderType;
import org.myob.service.EmployeeService;
import org.myob.service.PayslipService;
import org.myob.service.builder.AbstractPayslipServiceBuilder;
import org.myob.service.impl.EmployeeServiceImpl;
import service.PayslipServiceBuilderImpl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
@RunWith(ConcordionRunner.class)
public class EmployeeFixture extends AbstractFixture {

    private AbstractPayslipServiceBuilder builder;

    public EmployeeFixture() throws IOException {

        builder = new PayslipServiceBuilderImpl()
                .withReaderForEmployeeRepository(employeeReader)
                .withReaderForTaxRepository(taxReader)
                .withPayslipFileName("target/test-classes/payslip.csv");

    }

    public void writePayslip() throws Exception {
        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.setPayslipService(builder.build());

        employeeService.writePayslips();
    }
    public List<PayslipEntity> readPayslips() throws Exception {



        Reader payslipCsvReader = FileReaderType.CLASSLOADER.getReader("payslip.csv");

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(payslipCsvReader);
        Iterator<CSVRecord> recordIterator = records.iterator();

        List<PayslipEntity> payslipRecords = new ArrayList<>();

        while(recordIterator.hasNext()) {
          CSVRecord record = recordIterator.next();
            PayslipEntity  entity = new PayslipEntityBuilder()
                    .withGrossIncome(record.get(PayslipHeader.GROSS_INCOME.getLabel()))
                    .withIncomeTax(record.get(PayslipHeader.INCOME_TAX.getLabel()))
                    .withName(record.get(PayslipHeader.NAME.getLabel()))
                    .withNetIncome(record.get(PayslipHeader.NET_INCOME.getLabel()))
                    .withPayPeriod(record.get(PayslipHeader.PAY_PERIOD.getLabel()))
                    .withSuper(record.get(PayslipHeader.SUPER.getLabel()))
                    .build();
            payslipRecords.add(entity);

        }

        return payslipRecords;
    }

    private String loadFromClassPath(String fileName) {
        return this.getClass().getClassLoader().getResource(fileName).getPath();
    }

//    class HTMLRowMaker {
//        StringBuilder builder = new StringBuilder();
//
//        HTMLRowMaker append(Object value) {
//            builder.append("<td>").append(value).append("</td>");
//            return this;
//        }
//
//        String toHTML() {
//            return builder.toString();
//        }
//    }

    public void shouldProducePayslipCSV() throws Exception {

        PayslipService payslipService = new PayslipServiceBuilderImpl()
                .withEmployeeFileName(loadFromClassPath("employee.csv"))
                .withPayslipFileName("target/specs/payslip.csv")
                .withTaxFileName(loadFromClassPath("tax.csv"))
                .withFileReaderType(FileReaderType.FILEREADER).build();

        EmployeeService employeeService = new EmployeeServiceImpl();
        employeeService.setPayslipService(payslipService);

        employeeService.writePayslips();
    }


}
