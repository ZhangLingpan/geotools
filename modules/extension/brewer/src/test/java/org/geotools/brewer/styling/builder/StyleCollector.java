package org.geotools.brewer.styling.builder;

import java.util.ArrayList;
import java.util.List;
import org.geotools.api.style.*;
import org.geotools.styling.FeatureTypeStyle;
import org.geotools.styling.Rule;
import org.geotools.styling.Style;
import org.geotools.styling.Symbolizer;

public class StyleCollector extends AbstractStyleVisitor implements StyleVisitor {

    List<FeatureTypeStyle> featureTypeStyles = new ArrayList<>();

    List<Rule> rules = new ArrayList<>();

    List<Symbolizer> symbolizers = new ArrayList<>();

    List<Style> styles = new ArrayList<>();

    List<StyledLayer> layers = new ArrayList<>();

    @Override
    public void visit(NamedLayer layer) {
        layers.add(layer);
        for (org.geotools.api.style.Style style : layer.getStyles()) {
            style.accept(this);
        }
    }

    @Override
    public void visit(UserLayer layer) {
        layers.add(layer);
        for (org.geotools.api.style.Style style : layer.getUserStyles()) {
            style.accept(this);
        }
    }

    @Override
    public void visit(FeatureTypeConstraint ftc) {}

    @Override
    public void visit(org.geotools.api.style.Style style) {
        styles.add((Style) style);
        for (org.geotools.api.style.FeatureTypeStyle fts : style.featureTypeStyles()) {
            featureTypeStyles.add((FeatureTypeStyle) fts);
            fts.accept(this);
        }
    }

    @Override
    public void visit(org.geotools.api.style.Rule rule) {
        for (org.geotools.api.style.Symbolizer symbolizer : rule.symbolizers()) {
            symbolizers.add((Symbolizer) symbolizer);
        }
    }

    @Override
    public void visit(org.geotools.api.style.FeatureTypeStyle fts) {
        for (org.geotools.api.style.Rule rule : fts.rules()) {
            rules.add((Rule) rule);
            rule.accept(this);
        }
    }

    @Override
    public void visit(org.geotools.api.style.Fill fill) {}

    @Override
    public void visit(org.geotools.api.style.Stroke stroke) {}

    @Override
    public void visit(org.geotools.api.style.Symbolizer sym) {}

    @Override
    public void visit(org.geotools.api.style.PointSymbolizer ps) {}

    @Override
    public void visit(org.geotools.api.style.LineSymbolizer line) {}

    @Override
    public void visit(org.geotools.api.style.PolygonSymbolizer poly) {}

    @Override
    public void visit(org.geotools.api.style.TextSymbolizer text) {}

    @Override
    public void visit(org.geotools.api.style.RasterSymbolizer raster) {}

    @Override
    public void visit(org.geotools.api.style.Graphic gr) {}

    @Override
    public void visit(org.geotools.api.style.Mark mark) {}

    @Override
    public void visit(org.geotools.api.style.PointPlacement pp) {}

    @Override
    public void visit(org.geotools.api.style.Halo halo) {}

    @Override
    public void visit(org.geotools.api.style.ColorMap colorMap) {}

    @Override
    public void visit(org.geotools.api.style.ImageOutline outline) {}

    @Override
    public void visit(org.geotools.api.style.ChannelSelection cs) {}

    @Override
    public void visit(org.geotools.api.style.SelectedChannelType sct) {}
}
