package com.mrsydar.kubl.engine.service.csv;

import com.mrsydar.kubl.engine.service.exceptions.BadCSVInvoiceRowException;
import com.mrsydar.kubl.engine.structures.csv.CSVInvoice;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;

public class CSVInvoiceReader extends CSVReader {
    public CSVInvoiceReader(Reader reader) throws IOException {
        super(reader);
        this.skip(1);
    }

    public CSVInvoice readNextInvoice() throws CsvValidationException, IOException, BadCSVInvoiceRowException {
        String[] line = this.readNext();

        if (line == null) {
            return null;
        } else {
            if (line.length != 8) {
                throw new BadCSVInvoiceRowException("Bad field number in row.");
            }

            return new CSVInvoice(
                    line[0],
                    line[1],
                    line[2],
                    line[3].isEmpty() ? null : line[3],
                    line[4],
                    line[5],
                    line[6],
                    line[7]
            );
        }
    }
}
