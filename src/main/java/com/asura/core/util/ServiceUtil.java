import com.asura.fui.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import javax.servlet.http.HttpServletResponse;

public class ServiceUtil {

	public static long stream(InputStream input, OutputStream output) throws IOException {
		ReadableByteChannel inputChannel = null;
		WritableByteChannel outputChannel = null;
		try {
			inputChannel = Channels.newChannel(input);
			outputChannel = Channels.newChannel(output);
			ByteBuffer buffer = ByteBuffer.allocate(10240);
			long size = 0L;

			while (inputChannel.read(buffer) != -1) {
				buffer.flip();
				size += outputChannel.write(buffer);
				buffer.clear();
			}

			return size;
		} finally {
			if (outputChannel != null)
				try {
					outputChannel.close();
				} catch (IOException localIOException2) {
				}
			if (inputChannel != null)
				try {
					inputChannel.close();
				} catch (IOException localIOException3) {
				}
		}
	}

	public static void output(InputStream stream, HttpServletResponse response) throws IOException {
		stream(stream, response.getOutputStream());
	}

	public static void output(String result, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
}
