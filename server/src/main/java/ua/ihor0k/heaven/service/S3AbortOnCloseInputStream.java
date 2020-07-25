package ua.ihor0k.heaven.service;

import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.IOException;
import java.io.InputStream;

class S3AbortOnCloseInputStream extends InputStream {

    private final S3ObjectInputStream s3ObjectInputStream;

    public S3AbortOnCloseInputStream(S3ObjectInputStream s3ObjectInputStream) {
        this.s3ObjectInputStream = s3ObjectInputStream;
    }

    @Override
    public int read() throws IOException {
        return s3ObjectInputStream.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return s3ObjectInputStream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return s3ObjectInputStream.skip(n);
    }

    @Override
    public void mark(int readlimit) {
        s3ObjectInputStream.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        s3ObjectInputStream.reset();
    }

    @Override
    public boolean markSupported() {
        return s3ObjectInputStream.markSupported();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return s3ObjectInputStream.read(b);
    }

    @Override
    public int available() throws IOException {
        return s3ObjectInputStream.available();
    }

    @Override
    public void close() throws IOException {
        s3ObjectInputStream.abort();
        s3ObjectInputStream.close();
    }

}