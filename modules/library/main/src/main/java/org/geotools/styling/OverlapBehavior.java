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
package org.geotools.styling;

import org.geotools.api.style.StyleVisitor;
import org.geotools.filter.ConstantExpression;

/**
 * OverlapBehavior tells a system how to behave when multiple raster images in a layer overlap each
 * other, for example with satellite-image scenes.
 *
 * <p>
 *
 * <pre>
 *         <code>
 *  &lt;xsd:element name="OverlapBehavior"&gt;
 *      &lt;xsd:annotation&gt;
 *          &lt;xsd:documentation&gt;         &quot;OverlapBehavior&quot; tells a
 *              system how to behave when multiple         raster images in
 *              a layer overlap each other, for example with
 *              satellite-image scenes.       &lt;/xsd:documentation&gt;
 *      &lt;/xsd:annotation&gt;
 *      &lt;xsd:complexType&gt;
 *          &lt;xsd:choice&gt;
 *              &lt;xsd:element ref="sld:LATEST_ON_TOP"/&gt;
 *              &lt;xsd:element ref="sld:EARLIEST_ON_TOP"/&gt;
 *              &lt;xsd:element ref="sld:AVERAGE"/&gt;
 *              &lt;xsd:element ref="sld:RANDOM"/&gt;
 *          &lt;/xsd:choice&gt;
 *      &lt;/xsd:complexType&gt;
 *  &lt;/xsd:element&gt;
 *
 *          </code>
 *         </pre>
 *
 * @author Justin Deoliveira, The Open Planning Project
 */
public class OverlapBehavior extends ConstantExpression {
    public static final String AVERAGE_RESCTRICTION = "AVERAGE";
    public static final String RANDOM_RESCTRICTION = "RANDOM";
    public static final String LATEST_ON_TOP_RESCTRICTION = "LATEST_ON_TOP";
    public static final String EARLIEST_ON_TOP_RESCTRICTION = "EARLIEST_ON_TOP";
    public static final String UNSPECIFIED_RESCTRICTION = "UNSPECIFIED";

    public static final OverlapBehavior LATEST_ON_TOP =
            new OverlapBehavior(OverlapBehavior.LATEST_ON_TOP_RESCTRICTION);
    public static final OverlapBehavior EARLIEST_ON_TOP =
            new OverlapBehavior(OverlapBehavior.EARLIEST_ON_TOP_RESCTRICTION);
    public static final OverlapBehavior AVERAGE =
            new OverlapBehavior(OverlapBehavior.AVERAGE_RESCTRICTION);
    public static final OverlapBehavior RANDOM =
            new OverlapBehavior(OverlapBehavior.RANDOM_RESCTRICTION);

    private OverlapBehavior(String value) {
        super(value);
    }

    public static final OverlapBehavior cast(org.geotools.api.style.OverlapBehavior ob) {
        if (ob == null) return null;
        OverlapBehavior copy;
        if (ob.name().equalsIgnoreCase(OverlapBehavior.AVERAGE_RESCTRICTION)) {
            copy = OverlapBehavior.AVERAGE;
        }
        if (ob.name().equalsIgnoreCase(OverlapBehavior.LATEST_ON_TOP_RESCTRICTION)) {
            copy = OverlapBehavior.LATEST_ON_TOP;
        }
        if (ob.name().equalsIgnoreCase(OverlapBehavior.EARLIEST_ON_TOP_RESCTRICTION)) {
            copy = OverlapBehavior.EARLIEST_ON_TOP;
        }
        if (ob.name().equalsIgnoreCase(OverlapBehavior.RANDOM_RESCTRICTION)) {
            copy = OverlapBehavior.RANDOM;
        } else {
            throw new RuntimeException(
                    "Unable to copy unexpected OverlapBehavior (" + ob.name() + ")");
        }
        return copy;
    }

    public void accept(StyleVisitor visitor) {
        // visitor.visit(this);
    }
}
