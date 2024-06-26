package org.geotools.referencing.operation.transform;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.geotools.api.referencing.FactoryException;
import org.geotools.referencing.factory.gridshift.NADCONGridShiftFactory;
import org.geotools.referencing.factory.gridshift.NADConGridShift;
import org.geotools.util.URLs;
import org.junit.Test;

public class NADCONGridShiftFactoryTest {

    @Test
    public void testReleaseGrids() throws IOException, FactoryException {
        File gridShifts = new File("src/test/resources/org/geotools/referencing/factory/gridshift");
        File las = new File(gridShifts, "stpaul.las");
        File los = new File(gridShifts, "stpaul.los");

        File tlas = new File("./target/stpaul.las");
        File tlos = new File("./target/stpaul.los");
        copyFile(las, tlas);
        copyFile(los, tlos);

        NADCONGridShiftFactory factory = new NADCONGridShiftFactory();
        NADConGridShift shift = factory.loadGridShift(URLs.fileToUrl(tlas), URLs.fileToUrl(tlos));
        // minor checks on the grid
        assertNotNull(shift);

        // now the good part, try to delete the files, on windows this will fail
        // unless the sources were properly closed
        assertTrue(tlas.delete());
        assertTrue(tlos.delete());
    }

    private void copyFile(File src, File dst) throws IOException {
        try (FileInputStream fis = new FileInputStream(src);
                FileOutputStream fos = new FileOutputStream(dst)) {
            byte[] buffer = new byte[4096];
            int read = 0;
            while ((read = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, read);
            }
        }
    }
}
