import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.model.payslip.Payslip;
import org.myob.model.payslip.PayslipBuilder;
import org.myob.persistence.mapping.impl.PayslipHeader;
import org.myob.repository.specification.SpecificationForReadingEmployeeData;
import org.myob.service.PayslipService;
import org.myob.service.PayslipServiceBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Zainul Franciscus on 29/03/2015.
 */
@RunWith(ConcordionRunner.class)
public class TestTheResultingPayslipCsvFixture extends AbstractFixture {

    private PayslipServiceBuilder builder;

    public TestTheResultingPayslipCsvFixture() throws IOException {

        builder = new PayslipServiceBuilder()
                .withReaderForEmployeeRepository(employeeReader)
                .withReaderForTaxRepository(taxReader)
                .withPayslipFileName("target/test-classes/payslip.csv");

    }

    public void writePayslip() throws Exception {

        PayslipService payslipService = builder.build();
        payslipService.writePayslips(new SpecificationForReadingEmployeeData());
        payslipService.close();

    }

    public List<Payslip> readPayslips() throws Exception {

        Reader payslipCsvReader =  new InputStreamReader(getClass().getClassLoader().getResourceAsStream("payslip.csv"));

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(payslipCsvReader);
        Iterator<CSVRecord> recordIterator = records.iterator();

        List<Payslip> payslipRecords = new ArrayList<>();

        while (recordIterator.hasNext()) {
            CSVRecord record = recordIterator.next();
            Payslip payslip = new PayslipBuilder()
                    .withGrossIncome(NumberUtils.toInt(record.get(PayslipHeader.GROSS_INCOME.toString())))
                    .withIncomeTax(NumberUtils.toInt(record.get(PayslipHeader.INCOME_TAX.toString())))
                    .withName(record.get(PayslipHeader.NAME.toString()))
                    .withNetIncome(NumberUtils.toInt(record.get(PayslipHeader.NET_INCOME.toString())))
                    .withPayPeriod(record.get(PayslipHeader.PAY_PERIOD.toString()))
                    .withSuper(NumberUtils.toInt(record.get(PayslipHeader.SUPER.toString())))
                    .build();
            payslipRecords.add(payslip);

        }

        return payslipRecords;
    }

}
