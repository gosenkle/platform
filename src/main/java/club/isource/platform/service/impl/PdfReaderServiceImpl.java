package club.isource.platform.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
		long start = System.currentTimeMillis();
		Document document = null;
		PdfCopy copy = null;
		InputStream file = null;
		try {
			
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			

			String separator = "/";
			if (fileName.startsWith("http")) {
				separator = "/";
			}else {
				separator = File.separator;
			}
			String[] tempFiles = fileName.split(separator);
			String fileRealName = tempFiles[tempFiles.length - 1];

			String[] generatorFiles = fileRealName.split("\\.");
			
			String folder = System.getProperty("java.io.tmpdir");
			System.err.println(folder);
			String savepath = folder + generatorFiles[0] + page + "." + generatorFiles[1];
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
			
			File retFile = new File(savepath);
			if (!retFile.exists()) {
				PdfReader reader = new PdfReader(fileName);
				
				int n = reader.getNumberOfPages();
				int end = page + pageSize - 1;
				if (end <= 0 || end > n) {
					end = n;
				}
				document = new Document(reader.getPageSize(1));
				copy = new PdfCopy(document, new FileOutputStream(savepath));
				document.open();
				for (int j = page; j <= end; j++) {
					document.newPage();
					PdfImportedPage curpage = copy.getImportedPage(reader, j);
					copy.addPage(curpage);
				}
				document.close();
				System.out.println("耗时：" + (System.currentTimeMillis() - start));
			}

			// 返回文件流
			file = new BufferedInputStream(new FileInputStream(savepath));
			byte[] tmp = new byte[file.available()];
			while (file.read(tmp) != -1) {
				
			}
			System.out.println("耗时：" + (System.currentTimeMillis() - start));
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
				1, 10);
	}
}
