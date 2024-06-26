/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 * 	  (C) 2014 - 2015 Open Source Geospatial Foundation (OSGeo)
 * 	  (c) 2012 - 2014 OpenPlans
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.data.csv.parse;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.data.csv.CSVFileState;

public class CSVIterator implements Iterator<SimpleFeature>, Closeable {

    private int idx;

    private SimpleFeature next;

    private final CSVReader csvReader;

    private final CSVStrategy csvStrategy;

    public CSVIterator(CSVFileState csvFileState, CSVStrategy csvStrategy) throws IOException {
        this.csvStrategy = csvStrategy;
        try {
            csvReader = csvFileState.openCSVReader();
        } catch (CsvValidationException e) {
            throw new IOException(e);
        }
        idx = 1;
        next = null;
    }

    private SimpleFeature buildFeature(String[] csvRecord) {
        String id = "fid" + idx;
        SimpleFeature feature = csvStrategy.decode(id, csvRecord);
        idx++;
        return feature;
    }

    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        }
        try {
            next = readFeature();
        } catch (IOException e) {
            next = null;
        }
        return next != null;
    }

    private SimpleFeature readFeature() throws IOException {
        String[] record;
        try {
            if ((record = csvReader.readNext()) != null) {

                return buildFeature(record);
            }
        } catch (CsvValidationException e) {
            throw new IOException(e);
        }
        return null;
    }

    @Override
    public SimpleFeature next() {
        if (next != null) {
            SimpleFeature result = next;
            next = null;
            return result;
        }
        SimpleFeature feature;
        try {
            feature = readFeature();
        } catch (IOException e) {
            feature = null;
        }
        if (feature == null) {
            throw new NoSuchElementException();
        }
        return feature;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove features from csv iteratore");
    }

    @Override
    public void close() {
        try {
            csvReader.close();
        } catch (IOException e) {
            // Who cares?
        }
    }
}
