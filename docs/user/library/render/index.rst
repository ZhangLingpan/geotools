Render
======

Supports the rendering of geospatial information using the Java2D API.

.. figure:: /images/gt-render.svg
   
   gt-render module

This is most likely the reason you are interested in the GeoTools library - this module finally
lets you draw a map using all that data you set up.

**Reference**

* :doc:`/library/api/se`
* :doc:`/library/main/sld`
* http://www.opengeospatial.org/standards/sld
* http://www.opengeospatial.org/standards/se

**Tutorial**

* :doc:`style </tutorial/map/style>` (tutorial)

**Maven**::
   
    <dependency>
      <groupId>org.geotools</groupId>
      <artifactId>gt-render</artifactId>
      <version>${geotools.version}</version>
    </dependency>

**Contents**

.. sidebar:: Details
   
   .. toctree::
      :maxdepth: 1
      
      faq

.. toctree::
   :maxdepth: 1
   
   gtrenderer
   map
   style
   icon
   wkt

Graphic plugins:

.. toctree::
   :maxdepth: 1
   
   chart
   svg

See also:

* :doc:`/extension/mbstyle/index` (Extension)
* :doc:`/unsupported/css` (Unsupported)
