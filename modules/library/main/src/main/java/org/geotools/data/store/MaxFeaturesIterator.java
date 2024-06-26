/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2008, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.store;

import java.util.Iterator;
import org.geotools.api.feature.Feature;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.collection.DelegateFeatureIterator;

/**
 * Iterator wrapper which caps the number of returned features.
 *
 * @author Justin Deoliveira, The Open Planning Project
 */
public class MaxFeaturesIterator<F extends Feature> implements FeatureIterator<F> {

    FeatureIterator<F> delegate;

    long start;
    long end;
    long counter;

    public MaxFeaturesIterator(Iterator<F> iterator, long max) {
        this(new DelegateFeatureIterator<>(iterator), 0, max);
    }

    public MaxFeaturesIterator(Iterator<F> iterator, long start, long max) {
        this(new DelegateFeatureIterator<>(iterator), start, max);
    }

    public MaxFeaturesIterator(FeatureIterator<F> delegate, long max) {
        this(delegate, 0, max);
    }

    public MaxFeaturesIterator(FeatureIterator<F> delegate, long start, long max) {
        this.delegate = delegate;
        this.start = start;
        this.end = start + max;
        counter = 0;
    }

    public FeatureIterator<F> getDelegate() {
        return delegate;
    }

    @Override
    public boolean hasNext() {
        if (counter < start) {
            // skip to just before start if needed
            skip();
        }
        return delegate.hasNext() && counter < end;
    }

    @Override
    public F next() {
        if (counter < start) {
            // skip to just before start if needed
            skip();
        }
        if (counter <= end) {
            counter++;
            F next = delegate.next();
            return next;
        }
        return null;
    }

    private void skip() {
        if (counter < start) {
            while (delegate.hasNext() && counter < start) {
                counter++;
                delegate.next(); // skip!
            }
        }
    }

    @Override
    public void close() {
        delegate.close();
    }
}
