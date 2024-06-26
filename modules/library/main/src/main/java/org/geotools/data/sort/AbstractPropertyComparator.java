/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2020, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.sort;

import java.util.Comparator;
import org.geotools.api.feature.simple.SimpleFeature;

/** Base class for comparators working on {@link SimpleFeature} properties */
abstract class AbstractPropertyComparator implements Comparator<SimpleFeature> {

    boolean ascending;

    /**
     * Builds a new comparator
     *
     * @param ascending If true the comparator will force an ascending order (descending otherwise)
     */
    public AbstractPropertyComparator(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(SimpleFeature f1, SimpleFeature f2) {
        int result = compareAscending(f1, f2);
        if (ascending) {
            return result;
        } else {
            return result * -1;
        }
    }

    /** Compares the two features in ascending order */
    protected abstract int compareAscending(SimpleFeature f1, SimpleFeature f2);

    /** Support method to implement {@link #compareAscending(SimpleFeature, SimpleFeature)} */
    @SuppressWarnings("unchecked")
    protected int compareAscending(Comparable o1, Comparable o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (o2 == null) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }
}
