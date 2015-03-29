import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.myob.model.payslip.Payslip;
import org.myob.model.payslip.PayslipBuilder;
import org.myob.persistence.mapping.impl.PayslipHeader;

import org.myob.repository.specification.EmployeeSpecification;
import org.myob.service.PayslipService;
import org.myob.service.builder.AbstractPayslipServiceBuilder;

import service.PayslipServiceBuilderImpl;

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
public class EmployeeFixture extends AbstractFixture {

    private AbstractPayslipServiceBuilder builder;

    public EmployeeFixture() throws IOException {

        builder = new PayslipServiceBuilderImpl()
                .withReaderForEmployeeRepository(employeeReader)
                .withReaderForTaxRepository(taxReader)
                .withPayslipFileName("target/test-classes/payslip.csv");

    }

    public void writePayslip() throws Exception {

        PayslipService payslipService = builder.build();
        payslipService.writePayslips(new EmployeeSpecification());
        payslipService.close();

    }

    public List<Payslip> readPayslips() throws Exception {

        Reader payslipCsvReader =  new InputStreamReader(getClass().getClassLoader().getResourceAsStream("payslip.csv"));

        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withSkipHeaderRecord().parse(payslipCsvReader);
        Iterator<CSVRecord> recordIterator = records.iterator();

        List<Payslip> payslipRecords = new ArrayList<>();

        while (recordIterator.hasNext()) {
            CSVRecord record = recordIterator.next();
            Payslip entity = new PayslipBuilder()
                    .withGrossIncome(NumberUtils.toInt(record.get(PayslipHeader.GROSS_INCOME.getLabel())))
                    .withIncomeTax(NumberUtils.toInt(record.get(PayslipHeader.INCOME_TAX.getLabel())))
                    .withName(record.get(PayslipHeader.NAME.getLabel()))
                    .withNetIncome(NumberUtils.toInt(record.get(PayslipHeader.NET_INCOME.getLabel())))
                    .withPayPeriod(record.get(PayslipHeader.PAY_PERIOD.getLabel()))
                    .withSuper(NumberUtils.toInt(record.get(PayslipHeader.SUPER.getLabel())))
                    .build();
            payslipRecords.add(entity);

        }

        return payslipRecords;
    }

}
