package com.web4enterprise.pdf.core.document;

import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * Test coverage with Jacoco is wrong because of try-with(resource implementation that creates 8 branches.
 * This class is here only to mock outptPut to throw exception and restore the "correct" test coverage.
 * 
 * @author Régis Ramillien
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Pdf.class)
public class PdfTestForJacoco {
	@Test(expected=NullPointerException.class)
	public void testDeflateWithNullBaos() throws Exception {
		whenNew(ByteArrayOutputStream.class).withAnyArguments().thenReturn(null);
		
		Pdf pdf = new Pdf();
		Whitebox.invokeMethod(pdf, "deflate", new byte[] {});
	}

	@Test(expected=NullPointerException.class)
	public void testDeflateWithNullDeflater() throws Exception {
		whenNew(DeflaterOutputStream.class).withAnyArguments().thenReturn(null);
		
		Pdf pdf = new Pdf();
		Whitebox.invokeMethod(pdf, "deflate", new byte[] {});
	}
	
	@Test(expected=IOException.class)
	public void testDeflateWithBaosException() throws Exception {
		whenNew(ByteArrayOutputStream.class).withAnyArguments().thenThrow(new IOException("Mocked baos"));
		
		Pdf pdf = new Pdf();
		Whitebox.invokeMethod(pdf, "deflate", new byte[] {});
	}
	
	@Test(expected=IOException.class)
	public void testDeflateWithDeflaterException() throws Exception {
		//be sure to use same instance of baos for both "new ByteArraOutputStream" and "new DeflaterOutputStream".
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		whenNew(ByteArrayOutputStream.class).withAnyArguments().thenReturn(baos);
		
		whenNew(DeflaterOutputStream.class).withArguments(baos).thenThrow(new IOException("Mocked deflater"));
		
		Pdf pdf = new Pdf();
		Whitebox.invokeMethod(pdf, "deflate", new byte[] {});
	}
}
