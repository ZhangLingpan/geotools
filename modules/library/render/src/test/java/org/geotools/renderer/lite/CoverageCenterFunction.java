package org.geotools.renderer.lite;

import static org.geotools.filter.capability.FunctionNameImpl.parameter;

import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.filter.capability.FunctionName;
import org.geotools.api.geometry.Bounds;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.DataUtilities;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.filter.FunctionExpressionImpl;
import org.geotools.filter.capability.FunctionNameImpl;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

/**
 * A test rendering transformation that returns the center of the provided coverage
 *
 * @author Andrea Aime - GeoSolutions
 */
public class CoverageCenterFunction extends FunctionExpressionImpl {

    public static FunctionName NAME =
            new FunctionNameImpl("CoverageCenter", parameter("coverage", GridCoverage2D.class));

    public CoverageCenterFunction() {
        super(NAME);
    }

    @Override
    public Object evaluate(Object gc) {
        GridCoverage2D coverage = (GridCoverage2D) gc;

        Bounds env = coverage.getEnvelope();
        GeometryFactory gf = new GeometryFactory();
        Point center = gf.createPoint(new Coordinate(env.getMedian(0), env.getMedian(1)));

        SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
        tb.setName("center");
        tb.add("geom", Point.class, coverage.getCoordinateReferenceSystem2D());
        SimpleFeatureType ft = tb.buildFeatureType();

        SimpleFeatureBuilder fb = new SimpleFeatureBuilder(ft);
        fb.add(center);
        SimpleFeature f = fb.buildFeature(null);

        return DataUtilities.collection(f);
    }
}
