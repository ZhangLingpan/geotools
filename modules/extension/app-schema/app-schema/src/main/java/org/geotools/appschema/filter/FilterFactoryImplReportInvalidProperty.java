/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2010-2011, Open Source Geospatial Foundation (OSGeo)
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

package org.geotools.appschema.filter;

import org.geotools.api.filter.expression.PropertyName;
import org.geotools.filter.AttributeExpressionImpl;
import org.geotools.filter.FilterFactoryImpl;

/** @author Niels Charlier (Curtin University of Technology) */
public class FilterFactoryImplReportInvalidProperty extends FilterFactoryImpl {

    // @Override
    @Override
    public PropertyName property(String name) {
        AttributeExpressionImpl att = new AttributeExpressionImpl(name);
        att.setLenient(false);
        return att;
    }
}
