import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archive {
    public void pack(String archName, String filesList) throws IOException {
        FileOutputStream fos = new FileOutputStream(archName+".zip");
        ZipOutputStream zos = new ZipOutputStream(fos);
        if(filesList.contains(","))
        {    // dla wielu plików do kompesji
            String[] filesArr = filesList.split(",");
            List<String> files = Arrays.asList(filesArr);
            for (String srcFile : files) {   // wiele plików do kompresji
                File toZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(toZip);
                ZipEntry entry = new ZipEntry(toZip.getName());
                zos.putNextEntry(entry);

                byte bytes[] = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
                fis.close();
            }
            zos.close();
            fos.close();
        } else {
            // dla pojedynczego pliku do kompresji
            File filesToArch = new File(filesList);
            FileInputStream fis = new FileInputStream(filesToArch);
            ZipEntry entry = new ZipEntry(filesToArch.getName());
            zos.putNextEntry(entry);
            byte bytes[] = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            zos.close();
            fis.close();
            fos.close();
        }
    }
}
