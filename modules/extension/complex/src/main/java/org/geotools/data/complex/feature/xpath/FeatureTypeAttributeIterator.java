/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.data.complex.feature.xpath;

import java.util.List;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.geotools.api.feature.type.ComplexType;
import org.geotools.api.feature.type.PropertyDescriptor;
import org.geotools.feature.type.Types;

/**
 * A special iterator for iterating over the attributes of a feature type.
 *
 * @author Niels Charlier (Curtin University of Technology)
 */
public class FeatureTypeAttributeIterator implements NodeIterator {
    /** The feature type node pointer */
    protected NodePointer pointer;

    /** The feature type */
    protected ComplexType featureType;

    protected List<PropertyDescriptor> children;

    /** current position */
    protected int position;

    public FeatureTypeAttributeIterator(NodePointer pointer, ComplexType featureType) {
        this.pointer = pointer;
        this.featureType = featureType;

        // get list of descriptors from types and all supertypes
        children = Types.descriptors(featureType);

        position = 1;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public boolean setPosition(int position) {
        this.position = position;

        return position <= children.size();
    }

    @Override
    public NodePointer getNodePointer() {
        return new FeatureTypeAttributePointer(
                pointer, featureType, children.get(position).getName());
    }
}
