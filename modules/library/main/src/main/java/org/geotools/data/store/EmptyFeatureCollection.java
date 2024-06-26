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

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.locationtech.jts.geom.Envelope;

public class EmptyFeatureCollection extends DataFeatureCollection {

    /** null bounds */
    static ReferencedEnvelope bounds = new ReferencedEnvelope(new Envelope(), null);

    static {
        bounds.setToNull();
    }

    public EmptyFeatureCollection(SimpleFeatureType schema) {
        super(null, schema);
    }

    @Override
    public ReferencedEnvelope getBounds() {
        return bounds;
    }

    @Override
    public int getCount() throws IOException {
        return 0;
    }

    @Override
    protected Iterator<SimpleFeature> openIterator() {
        return new EmptyIterator<>();
    }

    @Override
    protected void closeIterator(Iterator close) {
        // do nothing
    }

    // read only access
    @Override
    public boolean add(SimpleFeature object) {
        return false;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public boolean removeAll(Collection collection) {
        return false;
    }

    public boolean isValid() {
        return true;
    }
}
