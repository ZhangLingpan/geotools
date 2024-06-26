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
package org.geotools.sld.bindings;

import javax.xml.namespace.QName;
import org.geotools.api.filter.FilterFactory;
import org.geotools.api.filter.expression.Expression;
import org.geotools.api.style.ColorMapEntry;
import org.geotools.api.style.StyleFactory;
import org.geotools.xsd.AbstractComplexBinding;
import org.geotools.xsd.ElementInstance;
import org.geotools.xsd.Node;
import org.picocontainer.MutablePicoContainer;

/**
 * Binding object for the element http://www.opengis.net/sld:ColorMapEntry.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xsd:element name="ColorMapEntry"&gt;
 *      &lt;xsd:complexType&gt;
 *          &lt;xsd:attribute name="color" type="xsd:string" use="required"/&gt;
 *          &lt;xsd:attribute name="opacity" type="xsd:double"/&gt;
 *          &lt;xsd:attribute name="quantity" type="xsd:double"/&gt;
 *          &lt;xsd:attribute name="label" type="xsd:string"/&gt;
 *      &lt;/xsd:complexType&gt;
 *  &lt;/xsd:element&gt;
 *
 *          </code>
 *         </pre>
 *
 * @generated
 */
public class SLDColorMapEntryBinding extends AbstractComplexBinding {
    StyleFactory styleFactory;
    FilterFactory filterFactory;

    public SLDColorMapEntryBinding(StyleFactory styleFactory, FilterFactory filterFactory) {
        this.styleFactory = styleFactory;
        this.filterFactory = filterFactory;
    }

    /** @generated */
    @Override
    public QName getTarget() {
        return SLD.COLORMAPENTRY;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public int getExecutionMode() {
        return AFTER;
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
        return ColorMapEntry.class;
    }

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public void initialize(ElementInstance instance, Node node, MutablePicoContainer context) {}

    /**
     *
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated modifiable
     */
    @Override
    public Object parse(ElementInstance instance, Node node, Object value) throws Exception {
        ColorMapEntry entry = styleFactory.createColorMapEntry();

        Expression color = filterFactory.literal(node.getAttributeValue("color"));
        entry.setColor(color);

        if (node.getAttributeValue("opacity") != null) {
            Double opacity = (Double) node.getAttributeValue("opacity");
            entry.setOpacity(filterFactory.literal(opacity.doubleValue()));
        }

        if (node.getAttributeValue("quantity") != null) {
            Double quantity = (Double) node.getAttributeValue("quantity");
            entry.setQuantity(filterFactory.literal(quantity.doubleValue()));
        }

        if (node.getAttributeValue("label") != null) {
            String label = (String) node.getAttributeValue("label");
            entry.setLabel(label);
        }

        return entry;
    }
}
