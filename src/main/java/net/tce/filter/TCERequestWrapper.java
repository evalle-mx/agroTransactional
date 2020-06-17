package net.tce.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;


public class TCERequestWrapper extends HttpServletRequestWrapper {
	 private byte[] body;
	 Logger log4j = Logger.getLogger( this.getClass());
	 
	 
	public TCERequestWrapper(HttpServletRequest httpRequest) throws IOException  {
		super(httpRequest);
		 // Read the request body and save it as a byte array
        InputStream is = super.getInputStream();
        body = IOUtils.toByteArray(is);
	}

	
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStreamImpl(new ByteArrayInputStream(body));
    }
	 
	
    public BufferedReader getReader() throws IOException {
        String enc = getCharacterEncoding();
        if(enc == null) enc = "UTF-8";
        return new BufferedReader(new InputStreamReader(getInputStream(), enc));
    }
	 private class ServletInputStreamImpl extends ServletInputStream {

	        private InputStream is;

	        public ServletInputStreamImpl(InputStream is) {
	            this.is = is;
	        }

	        public int read() throws IOException {
	            return is.read();
	        }

	        public boolean markSupported() {
	            return false;
	        }

	        public synchronized void mark(int i) {
	            throw new RuntimeException(new IOException("mark/reset not supported"));
	        }

	        public synchronized void reset() throws IOException {
	            throw new IOException("mark/reset not supported");
	        }

			
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}
	    }
}
