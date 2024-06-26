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

import org.geotools.api.style.Fill;
import org.geotools.api.style.Stroke;
import org.geotools.api.style.Style;

/**
 * A style object is quite hard to set up, involving fills, strokes, symbolizers and rules.
 *
 * @author James Macgill, CCG
 * @version $Id$
 */
public class BasicPolygonStyle extends StyleImpl implements Style {
    /** Creates a new instance of BasicPolygonStyle */
    public BasicPolygonStyle() {
        this(new FillImpl(), new StrokeImpl());
    }

    public BasicPolygonStyle(Fill fill, Stroke stroke) {
        PolygonSymbolizerImpl polysym = new PolygonSymbolizerImpl();
        polysym.setFill(fill);
        polysym.setStroke(stroke);

        RuleImpl rule = new RuleImpl();
        rule.symbolizers().add(polysym);

        FeatureTypeStyleImpl fts = new FeatureTypeStyleImpl();
        fts.rules().add(rule);
        this.featureTypeStyles().add(fts);
    }

    public String getAbstract() {
        return "A simple polygon style";
    }

    @Override
    public String getName() {
        return "default polygon style";
    }

    public String getTitle() {
        return "default polygon style";
    }
}
