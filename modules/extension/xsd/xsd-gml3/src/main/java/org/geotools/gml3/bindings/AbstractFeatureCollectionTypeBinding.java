/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2016, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.gml3.bindings;

import java.util.Collection;
import java.util.List;
import javax.xml.namespace.QName;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.gml3.GML;
import org.geotools.xsd.AbstractComplexBinding;
import org.geotools.xsd.ElementInstance;
import org.geotools.xsd.Node;

/**
 * Binding object for the type http://www.opengis.net/gml:AbstractFeatureCollectionType.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;complexType abstract="true" name="AbstractFeatureCollectionType"&gt;
 *      &lt;annotation&gt;
 *          &lt;documentation&gt;A feature collection contains zero or more features.&lt;/documentation&gt;
 *      &lt;/annotation&gt;
 *      &lt;complexContent&gt;
 *          &lt;extension base="gml:AbstractFeatureType"&gt;
 *              &lt;sequence&gt;
 *                  &lt;element maxOccurs="unbounded" minOccurs="0" ref="gml:featureMember"/&gt;
 *                  &lt;element minOccurs="0" ref="gml:featureMembers"/&gt;
 *              &lt;/sequence&gt;
 *          &lt;/extension&gt;
 *      &lt;/complexContent&gt;
 *  &lt;/complexType&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class AbstractFeatureCollectionTypeBinding extends AbstractComplexBinding {
    /** @generated */
    @Override
    public QName getTarget() {
        return GML.AbstractFeatureCollectionType;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Class getType() {
        return FeatureCollection.class;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        SimpleFeatureCollection featureCollection =
                (SimpleFeatureCollection) node.getChildValue(FeatureCollection.class);
        if (featureCollection == null) {
            featureCollection = new DelayedSchemaFeatureCollection();
        }

        // &lt;element maxOccurs="unbounded" minOccurs="0" ref="gml:featureMember"/&gt;
        List<SimpleFeature> childValues = node.getChildValues(SimpleFeature.class);

        // example ListFeatureCollection
        Collection<SimpleFeature> collection = DataUtilities.collectionCast(featureCollection);
        collection.addAll(childValues);

        // &lt;element minOccurs="0" ref="gml:featureMembers"/&gt;
        SimpleFeature[] featureMembers = node.getChildValue(SimpleFeature[].class);

        if (featureMembers != null) {
            for (SimpleFeature featureMember : featureMembers) {
                collection.add(featureMember);
            }
        }

        return featureCollection;
    }

    @Override
    public Object getProperty(Object object, QName name) {
        // just return the features themselves
        if (GML.featureMembers.equals(name)) {
            SimpleFeatureCollection fc = (SimpleFeatureCollection) object;

            return fc;

            // return fc.toArray(new Feature[fc.size()]);
        }

        return null;
    }
}
