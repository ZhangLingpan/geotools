/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011, Open Source Geospatial Foundation (OSGeo)
 *    (C) 2008, Open Geospatial Consortium Inc.
 *
 *    All Rights Reserved. http://www.opengis.org/legal/
 */
package org.geotools.api.style;

import org.geotools.api.filter.expression.Function;

/**
 * The ColorMap element defines the mapping of palette-type raster colors or fixed- numeric pixel
 * values to colors using an Interpolate or Categorize SE function
 *
 * <p>For example, a DEM raster giving elevations in meters above sea level can be translated to a
 * colored image with a ColorMap. The quantity attributes of a color-map are used for translating
 * between numeric matrixes and color rasters and the ColorMap entries should be in order of
 * increasing numeric quantity so that intermediate numeric values can be matched to a color (or be
 * interpolated between two colors). Labels may be used for legends or may be used in the future to
 * match character values. Not all systems can support opacity in colormaps. The default opacity is
 * 1.0 (fully opaque). Defaults for quantity and label are system-dependent.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding
 *     Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface ColorMap {

    /** @return Interpolate or Categorize function */
    Function getFunction();

    ColorMapEntry[] getColorMapEntries();

    ColorMapEntry getColorMapEntry(int i);

    /**
     * Type of color map; matchinges the function returned by getFunction().getName()
     *
     * @return One of TYPE_RAMP, TYPE_INTERVALS, or TYPE_VALUE
     */
    int getType();

    void accept(StyleVisitor visitor);

    boolean getExtendedColors();
}
