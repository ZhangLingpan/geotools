/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2015, Open Source Geospatial Foundation (OSGeo)
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
package org.geotools.api.data;

/**
 * Extends {@link ServiceInfo} with information about the underlying file structure
 *
 * @author Andrea Aime - GeoSolutions
 * @author Daniele Romagnoli - GeoSolutions
 */
public interface FileServiceInfo extends ServiceInfo, FileGroupProvider {

    /**
     * {@link FileGroupProvider} providing info content.
     *
     * @return A {@link FileGroupProvider} instance providing info content.
     */
    @Override
    CloseableIterator<FileGroup> getFiles(Query query);
}
