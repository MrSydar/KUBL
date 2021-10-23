package com.mrsydar.kubl.engine.service.csv;

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

    public CSVInvoice readNextInvoice() throws CsvValidationException, IOException {
        String[] line = this.readNext();

        if (line == null) {
            return null;
        } else {
            return new CSVInvoice(
                    line[0],
                    line[1],
                    line[2],
                    Float.parseFloat(line[3]),
                    Float.parseFloat(line[4]),
                    Float.parseFloat(line[5]),
                    Float.parseFloat(line[6])
            );
        }
    }
}
