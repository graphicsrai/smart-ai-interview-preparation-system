package interview_prep_system.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class PdfTextExtractor {

    public static String extractText(
            String filePath)
            throws Exception {

        File file = new File(filePath);

        try (PDDocument document =
                     Loader.loadPDF(file)) {

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(document);
        }
    }
}