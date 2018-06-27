package club.isource.platform.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.util.Base64Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import club.isource.platform.service.inf.PdfReaderService;

public class PdfReaderServiceImpl implements PdfReaderService {

	@Override
	public String reader(String fileName, int page, int pageSize) {
		Document document = null;
		PdfCopy copy = null;
		FileInputStream file = null;
		try {
			PdfReader reader = new PdfReader(fileName);
			int n = reader.getNumberOfPages();
			int end = page + pageSize - 1;
			if (end <= 0 || end > n) {
				end = n;
			}

			String folder = System.getProperty("java.io.tmpdir");
			String savepath = folder + "Temp.pdf";
			System.out.println("生成文件：" + savepath);

			document = new Document(reader.getPageSize(1));
			copy = new PdfCopy(document, new FileOutputStream(savepath));
			document.open();
			for (int j = page; j <= end; j++) {
				document.newPage();
				PdfImportedPage curpage = copy.getImportedPage(reader, j);
				copy.addPage(curpage);
			}
			document.close();
			
			// 返回文件流
			file = new FileInputStream(savepath);
			byte[] tmp = new byte[file.available()];
			while (file.read(tmp) != -1) {
				
			}
			
			return Base64Utils.encodeToString(tmp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (document!=null && document.isOpen()) {
				document.close();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		new PdfReaderServiceImpl().reader(
				"https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/gradle-plugin/reference/pdf/spring-boot-gradle-plugin-reference.pdf",
				11, 10);
	}
}
